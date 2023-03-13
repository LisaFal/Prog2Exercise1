package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {


    @Test
    void filterMovies_empty_textfield_and_no_genre_selected_returns_all_movies() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = Movie.initializeMovies();

        // when
        List<Movie> movies = hc.filterMovies(null, "");

        // then
        assertEquals(expectedMovies, movies);
    }
    @Test
    void filterMovies_empty_textfield_and_String_selected_returns_all_movies() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = Movie.initializeMovies();

        // when
        List<Movie> movies = hc.filterMovies("-- NO FILTER --", "");

        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_single_l_and_no_Genre_selected_returns_all_movies() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = Movie.initializeMovies();

        // when
        List<Movie> movies = hc.filterMovies(null, "l");

        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_empty_and_Genre_selected_returns_movies_of_that_genre() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie("Midnight Escape", "A group of friends embark on a dangerous journey to break out of a maximum-security prison in order to clear their names.", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.DRAMA)));
        expectedMovies.add(new Movie("Lost Treasure of the Amazon", "A team of explorers search for a legendary treasure in the heart of the Amazon jungle, but they soon realize that they are not alone.", Arrays.asList(Movie.Genre.ACTION, Movie.Genre.HORROR)));

        // when
        List<Movie> movies = hc.filterMovies(Movie.Genre.ACTION, "");


        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_contains_Titanic_and_Genre_Romance_selected_returns_Titanic() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Movie.Genre.ROMANCE, Movie.Genre.DRAMA)));

        // when
        List<Movie> movies = hc.filterMovies(Movie.Genre.ROMANCE, "Titanic");


        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_contains_Titanic_and_Genre_Drama_selected_returns_Titanic() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Movie.Genre.ROMANCE, Movie.Genre.DRAMA)));

        // when
        List<Movie> movies = hc.filterMovies(Movie.Genre.DRAMA, "Titanic");


        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_contains_Titanic_and_Genre_Action_selected_returns_empty_List() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = new ArrayList<>();

        // when
        List<Movie> movies = hc.filterMovies(Movie.Genre.ACTION, "Titanic");


        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }

}