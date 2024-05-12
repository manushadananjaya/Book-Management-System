package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty name;
    private final StringProperty genre;
    private final StringProperty language;
    private final IntegerProperty publishedYear;
    private final StringProperty isbn;
    private final IntegerProperty pageCount;

    public Book(String name, Genre genre, Language language, int publishedYear, String isbn, int pageCount) {
        this.name = new SimpleStringProperty(name);
        this.genre = new SimpleStringProperty(genre.toString());
        this.language = new SimpleStringProperty(language.toString());
        this.publishedYear = new SimpleIntegerProperty(publishedYear);
        this.isbn = new SimpleStringProperty(isbn);
        this.pageCount = new SimpleIntegerProperty(pageCount);
    }

    // Getters for properties
    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public StringProperty languageProperty() {
        return language;
    }

    public IntegerProperty publishedYearProperty() {
        return publishedYear;
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public IntegerProperty pageCountProperty() {
        return pageCount;
    }

    // Getters and setters for non-property attributes
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getLanguage() {
        return language.get();
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }

    public int getPublishedYear() {
        return publishedYear.get();
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear.set(publishedYear);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public int getPageCount() {
        return pageCount.get();
    }

    public void setPageCount(int pageCount) {
        this.pageCount.set(pageCount);
    }
}
