

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Add a Book");

        if (bookToEdit != null) {
            titleLabel.setText("Edit Book");
        }

        TextField nameField = new TextField();
        nameField.setPrefWidth(200);

        ComboBox<Genre> genreComboBox = new ComboBox<>();
        genreComboBox.setPrefWidth(200);
        genreComboBox.getItems().addAll(Genre.values());

        ComboBox<Language> languageComboBox = new ComboBox<>();
        languageComboBox.setPrefWidth(200);
        languageComboBox.getItems().addAll(Language.values());

        TextField publishedYearField = new TextField();
        publishedYearField.setPrefWidth(200);

        TextField isbnField = new TextField();
        isbnField.setPrefWidth(200);

        TextField pageCountField = new TextField();
        pageCountField.setPrefWidth(200);

        // Set values for fields if editing
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

            // Add or edit the book
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

        // Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
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
                addButton,
                cancelButton 
        );

        Scene scene = new Scene(root, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Book");
        primaryStage.show();
    }
}
