package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Rating;
import at.ac.fhcampuswien.fhmdb.models.ReleaseYear;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXComboBox releaseYearComboBox;

    @FXML
    public JFXComboBox ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    private String query = null;
    private Genre genre = null;
    private ReleaseYear releaseYear = null;
    private Rating ratingFrom = null;
    public List<Movie> allMovies;

    {
        try {
            allMovies = MovieAPI.fetchMovies(query, genre, releaseYear, ratingFrom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public HomeController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // adding genre filter items
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().add("-- NO FILTER --");
        genreComboBox.getItems().addAll(Genre.values()); //comboBox filled with Genre values (enum)

        //adding release year filter items
        releaseYearComboBox.setPromptText("Filter by Release Year");
        releaseYearComboBox.getItems().add("-- NO FILTER --");
        releaseYearComboBox.getItems().addAll(ReleaseYear.values());

        //adding rating filter items
        ratingComboBox.setPromptText("Filter by Rating");
        ratingComboBox.getItems().add("-- NO FILTER --");
        ratingComboBox.getItems().addAll(Rating.values());

        // adding event handlers to search button
        searchBtn.setOnAction(e -> {
            if (searchField.getText() != null) { query = searchField.getText();
                } else { query = null; }
            if (genreComboBox.getSelectionModel().getSelectedItem() != null &&
                    genreComboBox.getSelectionModel().getSelectedItem() != "-- NO FILTER --") {
                genre = (Genre) genreComboBox.getSelectionModel().getSelectedItem();
                } else { genre = null; }
            if (releaseYearComboBox.getSelectionModel().getSelectedItem() != null &&
                    releaseYearComboBox.getSelectionModel().getSelectedItem() != "-- NO FILTER --") {
                releaseYear = (ReleaseYear) releaseYearComboBox.getSelectionModel().getSelectedItem();
                } else { releaseYear = null; }
            if (ratingComboBox.getSelectionModel().getSelectedItem() != null &&
                    ratingComboBox.getSelectionModel().getSelectedItem() != "-- NO FILTER --") {
                ratingFrom = (Rating) ratingComboBox.getSelectionModel().getSelectedItem();
                } else { ratingFrom = null; }
            try {
                allMovies = MovieAPI.fetchMovies(query, genre, releaseYear, ratingFrom);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            observableMovies.setAll(allMovies);

            //used only when filter via filterMovies:
            //observableMovies.setAll(filterMovies(genreComboBox.getSelectionModel().getSelectedItem(), searchField.getText(), allMovies));
        });



        // Sort button
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                Movie.sortingAsc(observableMovies);
                sortBtn.setText("Sort (desc)");
            } else {
                Movie.sortingDes(observableMovies);
                sortBtn.setText("Sort (asc)");
            }
        });

    }
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
}