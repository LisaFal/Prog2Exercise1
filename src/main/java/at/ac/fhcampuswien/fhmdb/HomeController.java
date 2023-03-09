package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

import static java.util.Collections.*;
import static java.util.Collections.sort;

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
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().add("--NO FILTER--");
        genreComboBox.getItems().addAll(Movie.Genre.values()); //comboBox filled with Genre values (enum)

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here


        searchBtn.setOnAction(e -> {
            Movie.Genre selectedGenre = (Movie.Genre) genreComboBox.getSelectionModel().getSelectedItem();
            List<Movie> filteredMovies = Movie.filter(allMovies, selectedGenre);
            observableMovies.setAll(filteredMovies);

        });


        //sorting(observableMovies);

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                Movie.sortingAsc(observableMovies);
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");
            } else {
                Movie.sortingDes(observableMovies);
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });

    }
}