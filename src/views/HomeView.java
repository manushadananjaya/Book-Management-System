package views;

import controllers.HomeController;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.ObservableList; // Import ObservableList
import models.Book;

public class HomeView {
    private final Stage primaryStage;
    private final HomeController controller;
    private final ObservableList<Book> books; // Add ObservableList<Book> field

    public HomeView(Stage primaryStage, ObservableList<Book> books) { // Modify constructor to accept ObservableList<Book>
        this.primaryStage = primaryStage;
        this.controller = new HomeController(primaryStage, books); // Pass books to HomeController
        this.books = books; // Initialize books field
    }

    public void show() {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 50;");

        Label welcomeLabel = new Label("Welcome to Library Management System");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #333333;");

        Button enterLibraryButton = new Button("Enter Library");
        enterLibraryButton.setStyle("-fx-font-size: 16px; -fx-padding: 10 20; -fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Apply hover and pressed effects inline
        enterLibraryButton.setOnMouseEntered(e -> enterLibraryButton.setStyle("-fx-font-size: 16px; -fx-padding: 10 20; -fx-background-color: #45a049; -fx-text-fill: white;"));
        enterLibraryButton.setOnMouseExited(e -> enterLibraryButton.setStyle("-fx-font-size: 16px; -fx-padding: 10 20; -fx-background-color: #4CAF50; -fx-text-fill: white;"));
        enterLibraryButton.setOnMousePressed(e -> enterLibraryButton.setStyle("-fx-font-size: 16px; -fx-padding: 10 20; -fx-background-color: #0d8c3a; -fx-text-fill: white;"));
        enterLibraryButton.setOnMouseReleased(e -> enterLibraryButton.setStyle("-fx-font-size: 16px; -fx-padding: 10 20; -fx-background-color: #45a049; -fx-text-fill: white;"));

        enterLibraryButton.setOnAction(this::enterLibrary);

        root.getChildren().addAll(welcomeLabel, enterLibraryButton);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library Management System - Home");
        primaryStage.show();
    }

    private void enterLibrary(ActionEvent actionEvent) {
        controller.enterLibrary(actionEvent);
    }
}
