package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
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
        System.out.println("Removing " + clickedItem + " from the Database");
        repo.removeFromWatchlist(new WatchlistEntity((Movie) clickedItem));
        observableMovies.remove((Movie) clickedItem);
    };
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Genre> g = new ArrayList<>();
        g.add(Genre.ACTION);
        String[] a = {"jo"};
        String[] d = {"jo"};
        String[] w = {"jo"};
        Movie m = new Movie("123", "456", g, 1990, "789", "1011", 120, d, w, a, 8.9);

        // Fill the watchlist with movies from db
        watchlistMovies.addAll(repo.getAll().stream().map(we -> new Movie(we)).collect(Collectors.toList()));
        observableMovies.addAll(watchlistMovies);

        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell(onRemoveFromWatchlistClicked, "Remove"));

        // back to home view
        backBtn.setOnAction(actionEvent -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow() ;
            stage.getScene().setRoot(root);
            stage.show();
        });
    }
}
