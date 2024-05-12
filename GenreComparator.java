
import java.util.Comparator;

public class GenreComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getGenre().compareToIgnoreCase(book2.getGenre());
    }
}
