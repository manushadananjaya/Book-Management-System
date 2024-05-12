

import javafx.stage.Stage;
import javafx.collections.ObservableList;
import java.util.Comparator;


public class DashboardController {
    private final Stage primaryStage;
    private final ObservableList<Book> books;

    public DashboardController(Stage primaryStage, ObservableList<Book> books) {
        this.primaryStage = primaryStage;
        this.books = books;
    }

    public void addBook() {
        AddEditBookView addEditBookView = new AddEditBookView(primaryStage, books, null);
        addEditBookView.show();
    }

    public void editBook(Book bookToEdit) {
        AddEditBookView addEditBookView = new AddEditBookView(primaryStage, books, bookToEdit);
        addEditBookView.show();
    }

    public void removeBook(Book bookToRemove) {
        books.remove(bookToRemove);
    }

    public void searchBooks() {
        SearchView searchView = new SearchView(new Stage(), books);
        searchView.show();
    }

    public void sortBooks(Comparator<Book> comparator) {
        books.sort(comparator);
    }

    public ObservableList<Book> getBooks() {
        return books;
    }
}
