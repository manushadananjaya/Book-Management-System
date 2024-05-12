package views;

import controllers.HomeController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import models.Book;

public class HomeView {
    private final Stage primaryStage;
    private final HomeController controller;
    private final ObservableList<Book> books;

    public HomeView(Stage primaryStage, ObservableList<Book> books) {
        this.primaryStage = primaryStage;
        this.controller = new HomeController(primaryStage, books);
        this.books = books;
    }

    public void show() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER); // Center align the contents

        Label welcomeLabel = new Label("Welcome to Library Management System");

        Button enterLibraryButton = new Button("Enter Library");

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
