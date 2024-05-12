import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import models.Book;
import models.Genre;
import models.Language;
import views.DashboardView;
import views.HomeView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create sample books
        ObservableList<Book> books = FXCollections.observableArrayList(
                new Book("Book1", Genre.FICTION, Language.ENGLISH, 2020, "1234567890", 200),
                new Book("Book2", Genre.NONFICTION, Language.SPANISH, 2019, "0987654321", 300),
                new Book("Book3", Genre.FANTASY, Language.FRENCH, 2018, "1357924680", 250)
                
        );

        // Show HomeView
        HomeView homeView = new HomeView(primaryStage, books);
        homeView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
