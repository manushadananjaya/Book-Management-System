package views;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Book;
import models.Genre;
import models.Language;

public class AddEditBookView {
    private final Stage primaryStage;
    private final ObservableList<Book> books;
    private final Book bookToEdit;

    public AddEditBookView(Stage primaryStage, ObservableList<Book> books, Book bookToEdit) {
        this.primaryStage = primaryStage;
        this.books = books;
        this.bookToEdit = bookToEdit;
    }

    public void show() {
        VBox root = new VBox(10);
        root.setPrefSize(400, 300);

        Label titleLabel = new Label("Add a Book");
        if (bookToEdit != null) {
            titleLabel.setText("Edit Book");
        }

        TextField nameField = new TextField();
        ComboBox<Genre> genreComboBox = new ComboBox<>();
        genreComboBox.getItems().addAll(Genre.values());
        ComboBox<Language> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll(Language.values());
        TextField publishedYearField = new TextField();
        TextField isbnField = new TextField();
        TextField pageCountField = new TextField();

        if (bookToEdit != null) {
            nameField.setText(bookToEdit.getName());
            genreComboBox.setValue(Genre.valueOf(bookToEdit.getGenre()));
            languageComboBox.setValue(Language.valueOf(bookToEdit.getLanguage()));
            publishedYearField.setText(String.valueOf(bookToEdit.getPublishedYear()));
            isbnField.setText(bookToEdit.getIsbn());
            pageCountField.setText(String.valueOf(bookToEdit.getPageCount()));
        }

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            // Create a new book object
            Book newBook = new Book(
                    nameField.getText(),
                    genreComboBox.getValue(),
                    languageComboBox.getValue(),
                    Integer.parseInt(publishedYearField.getText()),
                    isbnField.getText(),
                    Integer.parseInt(pageCountField.getText())
            );

            // Add the newBook to the books list
            if (bookToEdit != null) {
                int index = books.indexOf(bookToEdit);
                books.set(index, newBook);
            } else {
                books.add(newBook);
            }

            // Close the window
            primaryStage.close();

            // Show dashboard
            new DashboardView(primaryStage, books).show();
        });

        root.getChildren().addAll(
                titleLabel,
                new Label("Name: "), nameField,
                new Label("Genre: "), genreComboBox,
                new Label("Language: "), languageComboBox,
                new Label("Published Year: "), publishedYearField,
                new Label("ISBN: "), isbnField,
                new Label("Page Count: "), pageCountField,
                addButton
        );

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Book");
        primaryStage.show();
    }
}
