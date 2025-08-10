import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("books")
@RequestScoped
public class BookEndpoint {

    @Inject
    private BookManager bookManager;

    @GET
    @Path("{id}")
    @jakarta.ws.rs.Produces("application/json")
    public Response getBook(@PathParam("id") String id) {
        return Response.ok(bookManager.get(id)).build();
    }

    @GET
    @jakarta.ws.rs.Produces("application/json")
    public Response getAllBooks() {
        return Response.ok(bookManager.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Book book) {
        String bookId = bookManager.add(book);
        return Response.created(
                        UriBuilder.fromResource(this.getClass())
                                .path(bookId).build())
                .build();
    }
}
