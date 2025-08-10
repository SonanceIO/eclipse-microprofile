import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class BookMessageBodyReader implements MessageBodyReader<Book> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
                              Annotation[] annotations, MediaType mediaType) {
        return type.equals(Book.class);
    }

    @Override
    public Book readFrom(Class<Book> type, Type genericType,
                         Annotation[] annotations, MediaType mediaType,
                         MultivaluedMap<String, String> httpHeaders,
                         InputStream entityStream)
            throws IOException, WebApplicationException {

        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.fromJson(entityStream, Book.class);
        } catch (Exception e) {
            throw new WebApplicationException("Error parsing Book JSON", e);
        }
    }
}