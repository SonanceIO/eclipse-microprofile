import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookManager {

    private final ConcurrentMap<String, Book> inMemoryStore = new ConcurrentHashMap<>();

    public String add(Book book) {
        if (book == null || book.getId() == null) {
            return "Invalid book";
        }
        inMemoryStore.put(book.getId(), book);
        return "Book Added";
    }

    public Book get(String id) {
        return inMemoryStore.get(id);
    }

    public List<Book> getAll() {
        return new ArrayList<>(inMemoryStore.values());
    }
}