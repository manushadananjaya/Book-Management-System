package controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import models.Book;
import views.DashboardView;

public class HomeController {

    private final Stage primaryStage;
    private final ObservableList<Book> books;

    public HomeController(Stage primaryStage, ObservableList<Book> books) {
        this.primaryStage = primaryStage;
        this.books = books;
    }

    public void enterLibrary(ActionEvent actionEvent) {
        DashboardView dashboardView = new DashboardView(primaryStage, books);
        dashboardView.show();
    }

    

}
