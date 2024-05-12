
import java.util.Comparator;

public class PublishedYearComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        return Integer.compare(book1.getPublishedYear(), book2.getPublishedYear());
    }
}
