import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.io.InputStream;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class BookMapper {

    public static JsonObject map(Book book) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", book.getId());
        builder.add("name", book.getName());
        builder.add("author", book.getAuthor());
        return builder.build();
    }

    public static Book map(InputStream is) {
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.fromJson(is, Book.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize Book", e);
        }
    }
}