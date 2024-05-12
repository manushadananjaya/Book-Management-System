package views;

import controllers.DashboardController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
        addButton.setOnAction(event -> controller.addBook());

        Button editButton = new Button("Edit Book Details");
        editButton.setOnAction(event -> {
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                controller.editBook(selectedBook);
            } else {
                // Display an error message or handle the case when no book is selected
            }
        });

        Button removeButton = new Button("Remove Book");
        removeButton.setOnAction(event -> controller.removeBook(tableView.getSelectionModel().getSelectedItem()));

        Button searchButton = new Button("Search Books");
        searchButton.setOnAction(event -> controller.searchBooks());

        // Arrange buttons in an HBox
        HBox buttonBox = new HBox(10, addButton, editButton, removeButton, searchButton, genreSortButton, nameSortButton, yearSortButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add the TableView and button box to the center and bottom of the BorderPane
        root.setCenter(tableView);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library Management System - Dashboard");
        primaryStage.show();
    }
}
