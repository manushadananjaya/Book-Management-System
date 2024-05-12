package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Book;

public class SearchView {
    private final Stage primaryStage;

    public SearchView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        VBox root = new VBox(10);
        root.setPrefSize(600, 400);

        Label searchLabel = new Label("Search Books:");
        TextField searchField = new TextField();
        TableView<Book> tableView = new TableView<>();
        TableColumn<Book, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        TableColumn<Book, String> languageColumn = new TableColumn<>("Language");
        TableColumn<Book, Integer> publishedYearColumn = new TableColumn<>("Published Year");
        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        TableColumn<Book, Integer> pageCountColumn = new TableColumn<>("Page Count");

        tableView.getColumns().addAll(nameColumn, genreColumn, languageColumn, publishedYearColumn, isbnColumn, pageCountColumn);

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            // Perform search operation based on searchField text
            // For now, let's just display a dummy list of books
            ObservableList<Book> books = FXCollections.observableArrayList(
                    new Book("Book1", null, null, 2022, "1234567890", 200),
                    new Book("Book2", null, null, 2021, "0987654321", 300)
            );
            tableView.setItems(books);
        });

        root.getChildren().addAll(searchLabel, searchField, searchButton, tableView);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search Books");
        primaryStage.show();
    }
}
