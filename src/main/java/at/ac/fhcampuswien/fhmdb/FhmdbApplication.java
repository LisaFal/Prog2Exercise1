package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.Database;
import at.ac.fhcampuswien.fhmdb.database.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;


public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 620);
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("FHMDb");
        stage.setScene(scene);
        stage.show();


        //Testeinträge für die Datenbank //ACHTUNG: wenn table MOVIE offen, kann das nicht ausgeführt werden!
        //WatchlistEntity movie = new WatchlistEntity("Was ist das?", "Titanic", "A ship movie", "COMEDY", 1999, "movie.com", 134, 8.7);
        //WatchlistEntity movie2 = new WatchlistEntity("Was ist das?", "Titanic Teil 2", "A ship movie", "COMEDY", 1999, "movie.com", 134, 8.7);
        //WatchlistRepository repo = new WatchlistRepository();
        //repo.addToWatchlist(movie);
        //repo.addToWatchlist(movie2);

        /*System.out.println(repo.getAll().get(0).getTitle());*/
        //repo.removeFromWatchlist(movie);
    }

    public static void main(String[] args) {
        launch();
    }
}