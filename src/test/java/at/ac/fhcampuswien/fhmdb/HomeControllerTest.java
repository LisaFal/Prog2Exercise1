package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void filter_movies_no_text_and_genre_selected() {
        //given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = Movie.initializeMovies();

        //when
        List<Movie> movies = hc.filterMovies(null, "");
        //then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }


}