package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import exceptions.DataBaseException;



import at.ac.fhcampuswien.fhmdb.models.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class WatchlistController implements Initializable {
    @FXML
    public JFXButton backBtn;
    public JFXListView movieListView;
    public List<Movie> watchlistMovies = new ArrayList<>();
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    WatchlistRepository repo = new WatchlistRepository();
    private final ClickEventHandler onRemoveFromWatchlistClicked = (clickedItem) -> {
        /*
        System.out.println("Removing " + clickedItem + " from the Database");
        repo.removeFromWatchlist(new WatchlistEntity((Movie) clickedItem));
        observableMovies.remove((Movie) clickedItem);

         */

        System.out.println("Removing " + clickedItem + " from the Database");
        try {
            repo.removeFromWatchlist(new WatchlistEntity((Movie) clickedItem));
            observableMovies.remove((Movie) clickedItem);
        } catch (DataBaseException e) {
            //showError("Fehler beim Entfernen des Films von der Watchlist: " + e.getMessage());
            throw new DataBaseException("Fehler beim Entfernen des Films von der Watchlist: " + e);
        }

    };
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*
        //test pop up alert
        Alert testAlert = new Alert(AlertType.INFORMATION);
        testAlert.setTitle("Test");
        testAlert.setHeaderText("Test Popup");
        testAlert.setContentText("This is a test popup.");
        testAlert.showAndWait();

         */


        List<Genre> g = new ArrayList<>();
        g.add(Genre.ACTION);
        String[] a = {"jo"};
        String[] d = {"jo"};
        String[] w = {"jo"};
        Movie m = new Movie("123", "456", g, 1990, "789", "1011", 120, d, w, a, 8.9);

        // Fill the watchlist with movies from db

        /*
        watchlistMovies.addAll(repo.getAll().stream().map(we -> new Movie(we)).collect(Collectors.toList()));
        observableMovies.addAll(watchlistMovies);
         */

        try {
            watchlistMovies.addAll(repo.getAll().stream().map(we -> new Movie(we)).collect(Collectors.toList()));
            observableMovies.addAll(watchlistMovies);
        } catch (DataBaseException e) {
            showError("Fehler beim Laden der Watchlist: " + e.getMessage());
        }


        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell(onRemoveFromWatchlistClicked, "Remove"));

        // back to home view
        backBtn.setOnAction(actionEvent -> {
            /*
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow() ;
            stage.getScene().setRoot(root);
            stage.show();

             */
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
                Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow() ;
                stage.getScene().setRoot(root);
                stage.show();
            } catch (IOException e) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error loading Home View");
                alert.setContentText("There was an error loading the Home View. Please try again or contact support.");
                alert.showAndWait();
            }
        });
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
