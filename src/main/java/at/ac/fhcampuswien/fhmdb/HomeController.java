package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import exceptions.MovieAPIException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    @FXML
    public Label longestMovie;
    public Label popularActor;
    public TextField searchfieldDirectors;
    public JFXButton searchDirectorsBtn;
    public Label amountDirectors;


    private String query = null;
    private Genre genre = null;
    private int releaseYear = -1;
    private double ratingFrom = -1;
    public List<Movie> allMovies;
    private static final Double[] RATING_VALUES = {6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5};

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public HomeController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        { //initializing the movielist
            try {
                allMovies = MovieAPI.fetchMovies(query, genre, releaseYear, ratingFrom);
            } catch (MovieAPIException e) {
              //  throw new RuntimeException(e);
            }
        }
        observableMovies.addAll(allMovies);         // add movie data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // adding genre filter items
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().add("-- NO FILTER --");
        genreComboBox.getItems().addAll(Genre.values()); //comboBox filled with Genre values (enum)

        //adding release year filter items
        List<Integer> years = new ArrayList<>();
        for(Movie m : allMovies) {
            if(!years.contains(m.getReleaseYear()))
                years.add(m.getReleaseYear());
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

        // adding event handler to search button
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
            } catch (MovieAPIException exception) {
               // throw new RuntimeException(exception);
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

        //implementing the stream-methods from Exercise 2 to UI
        longestMovie.setText(String.valueOf(getLongestMovieTitle(observableMovies)) + " Chars");
        popularActor.setText(getMostPopularActor(observableMovies));
        searchDirectorsBtn.setOnAction(actionEvent -> {
            amountDirectors.setText(String.valueOf(countMoviesFrom(observableMovies, searchfieldDirectors.getText())));
        });
        //TO DO: getMoviesBetweenTwoYears - should this replace the filtering via parameters (URL)?


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
}