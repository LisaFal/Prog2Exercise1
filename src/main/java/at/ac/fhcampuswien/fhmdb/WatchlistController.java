package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.patterns.Observer;
import exceptions.DataBaseException;



import at.ac.fhcampuswien.fhmdb.models.ClickEventHandler;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class WatchlistController implements Initializable, Observer {
    @FXML
    public JFXButton backBtn;
    public JFXListView movieListView;
    public List<Movie> watchlistMovies = new ArrayList<>();
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

    private final ClickEventHandler onRemoveFromWatchlistClicked = (clickedItem) -> {



        try {
            WatchlistRepository repo = WatchlistRepository.getInstance();
            repo.removeFromWatchlist(new WatchlistEntity((Movie) clickedItem));
            observableMovies.remove((Movie) clickedItem);
        } catch (DataBaseException e) {
            showError(e);
        }

    };

    // private static WatchlistController instance;

    public WatchlistController() {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        WatchlistRepository.getInstance().addObserver(this);

        // Fill the watchlist with movies from db
        try {
            WatchlistRepository repo = WatchlistRepository.getInstance();
            watchlistMovies.clear();
            observableMovies.clear();
            watchlistMovies.addAll(repo.getAll().stream().map(we -> new Movie(we)).collect(Collectors.toList()));
            observableMovies.addAll(watchlistMovies);
        } catch (DataBaseException e) {
            showError(e);
        }


        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell(onRemoveFromWatchlistClicked, "Remove"));

        // back to home view
        backBtn.setOnAction(actionEvent -> {

            Parent root = null;
            try {
                ControllerFactory cf = new ControllerFactory();
                FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
                fxmlLoader.setControllerFactory(cf);
                root = fxmlLoader.load();
            } catch (Exception e) {
                showError(e);
                return;
            }
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow() ;
            stage.getScene().setRoot(root);
            stage.show();
        });
    }

    @Override
    public void update() {
        // Refresh the movie list when a movie is added or removed
        watchlistMovies.clear();
        observableMovies.clear();

        try {
            WatchlistRepository repo = WatchlistRepository.getInstance();
            watchlistMovies.addAll(repo.getAll().stream().map(we -> new Movie(we)).collect(Collectors.toList()));
            observableMovies.addAll(watchlistMovies);
        } catch (DataBaseException e) {
            showError(e);
        }
    }

    private static void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong!");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

}
