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
        root.setPrefSize(500, 600);
        root.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 20px;");

        Label titleLabel = new Label("Add a Book");
        if (bookToEdit != null) {
            titleLabel.setText("Edit Book");
            titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        } else {
            titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        }

        TextField nameField = new TextField();
        nameField.setStyle("-fx-pref-width: 200px;");
        ComboBox<Genre> genreComboBox = new ComboBox<>();
        genreComboBox.setStyle("-fx-pref-width: 200px;");
        genreComboBox.getItems().addAll(Genre.values());
        ComboBox<Language> languageComboBox = new ComboBox<>();
        languageComboBox.setStyle("-fx-pref-width: 200px;");
        languageComboBox.getItems().addAll(Language.values());
        TextField publishedYearField = new TextField();
        publishedYearField.setStyle("-fx-pref-width: 200px;");
        TextField isbnField = new TextField();
        isbnField.setStyle("-fx-pref-width: 200px;");
        TextField pageCountField = new TextField();
        pageCountField.setStyle("-fx-pref-width: 200px;");

        Button addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8px 16px; -fx-border-radius: 4px;");
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
