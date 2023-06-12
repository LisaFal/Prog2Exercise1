package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.database.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.*;
import at.ac.fhcampuswien.fhmdb.patterns.Sorter;
import at.ac.fhcampuswien.fhmdb.patterns.StateNotSorted;
import at.ac.fhcampuswien.fhmdb.patterns.StateSortedAsc;
import at.ac.fhcampuswien.fhmdb.patterns.StateSortedDesc;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import exceptions.DataBaseException;
import exceptions.MovieAPIException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;
    public TextField searchField;
    public JFXListView movieListView;
    public JFXComboBox genreComboBox;
    public JFXComboBox releaseYearComboBox;
    public JFXComboBox ratingComboBox;
    public JFXButton sortBtn;
    public JFXButton watchlistBtn;


    private String query = null;
    private Genre genre = null;
    private int releaseYear = -1;
    private double ratingFrom = -1;
    public List<Movie> allMovies;

    private static final Double[] RATING_VALUES = {6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5};

    private final ClickEventHandler onAddToWatchlistClicked = (clickedItem) -> {

        /*
        try {
            WatchlistRepository  repo = WatchlistRepository.getInstance();
            repo.addToWatchlist(new WatchlistEntity((Movie) clickedItem));
        } catch(DataBaseException dbe) {
            displayErrorPopup(dbe);
        }

         */

        try {
            WatchlistRepository  repo = WatchlistRepository.getInstance();
            if(repo.isInWatchlist(new WatchlistEntity((Movie) clickedItem))) {
                showSuccess("Movie already on watchlist!");
            } else {
                repo.addToWatchlist(new WatchlistEntity((Movie) clickedItem));
                showSuccess("Movie successfully added to watchlist!");
            }
        } catch(DataBaseException dbe) {
            displayErrorPopup(dbe);
        }
    };
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private Sorter moviesorter = new Sorter();
    private ObservableList<Movie> filteredMovies;
    private ObservableList<Movie> sortedMovies;
    //private static HomeController instance;

    public HomeController() {

    };

    public List<Movie> getMoviesFromAPI() {
        moviesorter.setState(new StateNotSorted());
        return moviesorter.sort(observableMovies);
    }

    public List<Movie> sortMoviesAscending(List<Movie> movieList) {
        moviesorter.setState(new StateSortedAsc());
        return moviesorter.sort(new ArrayList<>(movieList));
    }

    public List <Movie> sortMoviesDescending(List<Movie> movieList) {
        moviesorter.setState(new StateSortedDesc());
        return moviesorter.sort(new ArrayList<>(movieList));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        { //initializing the movielist
            try {
                // add movie data to observable list
                allMovies = MovieAPI.fetchMovies(query, genre, releaseYear, ratingFrom);
                observableMovies.clear();
                observableMovies.addAll(allMovies);
            } catch (MovieAPIException e) {
              displayErrorPopup(e);
            }
        }


        // initialize UI stuff
        movieListView.setItems((ObservableList) getMoviesFromAPI());   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked, "Watchlist")); // use custom cell factory to display data

        // adding genre filter items
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().add("-- NO FILTER --");
        genreComboBox.getItems().addAll(Genre.values()); //comboBox filled with Genre values (enum)

        //adding release year filter items
        List<Integer> years = new ArrayList<>();
        if(allMovies != null) {
            for(Movie m : allMovies) {
                if(!years.contains(m.getReleaseYear()))
                    years.add(m.getReleaseYear());
            }
        }

        Collections.sort(years);
        releaseYearComboBox.setPromptText("Filter by Release Year");
        releaseYearComboBox.getItems().add("-- NO FILTER --");
        releaseYearComboBox.getItems().addAll(years);

        //adding rating filter items
        List<Double> ratings = new ArrayList<>(Arrays.asList(RATING_VALUES));
        ratingComboBox.setPromptText("Filter by Rating");
        ratingComboBox.getItems().add("-- NO FILTER --");
        ratingComboBox.getItems().addAll(ratings);


        filteredMovies = FXCollections.observableArrayList();
        sortedMovies = FXCollections.observableArrayList();

        // adding event handler to search button
        searchBtn.setText("SEARCH");
        searchBtn.setOnAction(e -> {
            if (searchField.getText() != null) { query = searchField.getText();
                } else { query = null; }
            if (genreComboBox.getSelectionModel().getSelectedItem() != null &&
                    genreComboBox.getSelectionModel().getSelectedItem() != "-- NO FILTER --") {
                genre = (Genre) genreComboBox.getSelectionModel().getSelectedItem();
                } else { genre = null; }
            if (releaseYearComboBox.getSelectionModel().getSelectedItem() != null &&
                    releaseYearComboBox.getSelectionModel().getSelectedItem() != "-- NO FILTER --") {
                releaseYear = (int) releaseYearComboBox.getSelectionModel().getSelectedItem();
                } else { releaseYear = -1; }
            if (ratingComboBox.getSelectionModel().getSelectedItem() != null &&
                    ratingComboBox.getSelectionModel().getSelectedItem() != "-- NO FILTER --") {
                ratingFrom = (double) ratingComboBox.getSelectionModel().getSelectedItem();
                } else { ratingFrom = -1; }
            try {
                allMovies = MovieAPI.fetchMovies(query, genre, releaseYear, ratingFrom);
                filteredMovies.setAll(allMovies);
                if(sortBtn.getText().equals("Sort (desc)")) {
                    sortedMovies.setAll(sortMoviesAscending(new ArrayList<>(filteredMovies)));
                    observableMovies.setAll(sortedMovies);
                } else {
                    sortedMovies.setAll(sortMoviesDescending(new ArrayList<>(filteredMovies)));
                    observableMovies.setAll(sortedMovies);
                }
            } catch (MovieAPIException exception) {
               displayErrorPopup(exception);
            }

        });

        // Sort button
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                filteredMovies = observableMovies;
                sortedMovies.setAll(sortMoviesAscending(new ArrayList<>(filteredMovies)));
                observableMovies.setAll(sortedMovies);
                sortBtn.setText("Sort (desc)");
            } else {
                filteredMovies = observableMovies;
                sortedMovies.setAll(sortMoviesDescending(new ArrayList<>(filteredMovies)));
                observableMovies.setAll(sortedMovies);
                sortBtn.setText("Sort (asc)");
            }
        });

        watchlistBtn.setOnAction(actionEvent -> {
            Parent root = null;
            try {
                ControllerFactory cf = new ControllerFactory();
                FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("watchlist-view.fxml"));
                fxmlLoader.setControllerFactory(cf);
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow() ;
            stage.getScene().setRoot(root);
            stage.show();
        });


    }
    //NOT NEEDED AT THE MOMENT - still here for the tests
    public List<Movie> filterMovies(Object genre, String searchString, List<Movie> movies) {
        Genre selectedGenre = null;
        if(genre != null && (genre instanceof Genre))
            selectedGenre = (Genre) genre;
        List<Movie> filteredMovies = Movie.filter(movies, selectedGenre);
        if (searchString != "") {
            filteredMovies = Movie.search(searchString, filteredMovies); //search within filteredMovies
        }
        return  filteredMovies;
    }


    public int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .mapToInt(movie -> movie.getTitle().length())
                .max()
                .orElse(0);
    }

    public String getMostPopularActor(List<Movie> movies) {
        Map<String, Long> actorFrequency = movies.stream()
                .flatMap(movie -> Arrays.stream(movie.getMainCast()))
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));

        return actorFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> Arrays.asList(movie.getDirectors()).contains(director))
                .count();
    }

    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }
    private static void displayErrorPopup(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong!");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    private static void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setContentText(message);
        alert.showAndWait();
    }

}