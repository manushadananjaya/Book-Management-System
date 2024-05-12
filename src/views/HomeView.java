package views;

import controllers.HomeController;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
        AnchorPane root = new AnchorPane();

        Label welcomeLabel = new Label("Welcome to Library Management System");
        welcomeLabel.setLayoutX(171);
        welcomeLabel.setLayoutY(165);

        Button enterLibraryButton = new Button("Enter Library");
        enterLibraryButton.setLayoutX(261);
        enterLibraryButton.setLayoutY(215);
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
