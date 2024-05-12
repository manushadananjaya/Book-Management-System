package views;

import controllers.DashboardController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Book;

import java.util.Comparator;

public class DashboardView {
    private final Stage primaryStage;
    private final DashboardController controller;

    public DashboardView(Stage primaryStage, ObservableList<Book> books) {
        this.primaryStage = primaryStage;
        this.controller = new DashboardController(primaryStage, books); // Initialize controller with books
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

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
        tableView.setItems(controller.getBooks()); // Set items from the controller

        // Create sorting buttons
        Button genreSortButton = new Button("Sort by Genre");
        Button nameSortButton = new Button("Sort by Name");
        Button yearSortButton = new Button("Sort by Published Year");

        // Apply styles to sorting buttons
        genreSortButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        nameSortButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        yearSortButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Sorting event handlers
        genreSortButton.setOnAction(event -> {
            controller.sortBooks(Comparator.comparing(Book::getGenre));
        });

        nameSortButton.setOnAction(event -> {
            controller.sortBooks(Comparator.comparing(Book::getName));
        });

        yearSortButton.setOnAction(event -> {
            controller.sortBooks(Comparator.comparingInt(Book::getPublishedYear));
        });

        // Create buttons
        Button addButton = new Button("Add a Book");
        addButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        addButton.setOnAction(event -> controller.addBook());

        Button editButton = new Button("Edit Book Details");
        editButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        editButton.setOnAction(event -> {
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                controller.editBook(selectedBook);
            } else {
                // Display an error message or handle the case when no book is selected
                alert("No book selected", "Please select a book to edit.");
            }
        });

        Button removeButton = new Button("Remove Book");
        removeButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-color: #f44336; -fx-text-fill: white;");
        removeButton.setOnAction(event -> {
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                controller.removeBook(selectedBook);
            } else {
                // Display an error message or handle the case when no book is selected
                alert("No book selected", "Please select a book to remove.");
            }
        });

        Button searchButton = new Button("Search Books");
        searchButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        searchButton.setOnAction(event -> controller.searchBooks());

        // Arrange buttons in an HBox
        HBox buttonBox = new HBox(10, addButton, editButton, removeButton, searchButton, genreSortButton, nameSortButton, yearSortButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add the TableView and button box to the center and bottom of the BorderPane
        root.setCenter(tableView);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library Management System - Dashboard");
        primaryStage.show();
    }

    private void alert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
