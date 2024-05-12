package controllers;

import javafx.stage.Stage;
import javafx.collections.ObservableList;
import models.Book;
import views.DashboardView;
import views.AddEditBookView;

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

    public void removeBook() {
        // Implement remove book functionality
    }

    public void searchBooks() {
        // Implement search book functionality
    }

    public void sortBooks() {
        // Implement sort book functionality
    }

    public ObservableList<Book> getBooks() {
        return books;
    }
}
