package views;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Book;
import controllers.SearchController;

public class SearchView {
    private final Stage primaryStage;
    private final SearchController controller;

    public SearchView(Stage primaryStage, ObservableList<Book> books) {
        this.primaryStage = primaryStage;
        this.controller = new SearchController(books);
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f0f0f0;"); // Set background color

        // Create TableView and columns
        TableView<Book> tableView = new TableView<>();
        TableColumn<Book, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());

        TableColumn<Book, String> languageColumn = new TableColumn<>("Language");
        languageColumn.setCellValueFactory(cellData -> cellData.getValue().languageProperty());

        TableColumn<Book, Integer> publishedYearColumn = new TableColumn<>("Published Year");
        publishedYearColumn.setCellValueFactory(cellData -> cellData.getValue().publishedYearProperty().asObject());

        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());

        TableColumn<Book, Integer> pageCountColumn = new TableColumn<>("Page Count");
        pageCountColumn.setCellValueFactory(cellData -> cellData.getValue().pageCountProperty().asObject());

       

        tableView.getColumns().addAll(nameColumn, genreColumn, languageColumn, publishedYearColumn, isbnColumn, pageCountColumn);
        tableView.setItems(controller.getSearchResults());
        tableView.setStyle("-fx-background-color: white;"); // Set background color for TableView

        // Create search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Enter search term");
        searchField.setStyle("-fx-pref-width: 200px;"); // Set preferred width for TextField

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;"); // Set button style
        searchButton.setOnAction(event -> {
            String searchTerm = searchField.getText();
            controller.searchBooks(searchTerm);
        });

        HBox searchBar = new HBox(10, searchField, searchButton);
        searchBar.setPadding(new Insets(10));

        // Add components to the root
        root.setTop(searchBar);
        root.setCenter(tableView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search Books");
        primaryStage.show();
    }
}
