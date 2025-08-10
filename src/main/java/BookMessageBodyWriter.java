import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonWriter;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
public class BookMessageBodyWriter implements MessageBodyWriter<Book> {

    @Override
    public boolean isWriteable(
            Class<?> type, Type genericType,
            Annotation[] annotations,
            MediaType mediaType) {
        return type.equals(Book.class);
    }

    @Override
    public void writeTo(
            Book book, Class<?> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException {

        try (JsonWriter jsonWriter = Json.createWriter(entityStream)) {
            JsonObject jsonObject = BookMapper.map(book);
            jsonWriter.writeObject(jsonObject);
        }
    }
}