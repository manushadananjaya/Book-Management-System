package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Book;

public class SearchController {
    private final ObservableList<Book> allBooks;
    private final ObservableList<Book> searchResults;

    public SearchController(ObservableList<Book> allBooks) {
        this.allBooks = allBooks;
        this.searchResults = FXCollections.observableArrayList();
    }

    public void searchBooks(String searchTerm) {
        searchResults.clear();
        for (Book book : allBooks) {
            if (book.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.add(book);
            }
        }
    }

    public ObservableList<Book> getSearchResults() {
        return searchResults;
    }
}
