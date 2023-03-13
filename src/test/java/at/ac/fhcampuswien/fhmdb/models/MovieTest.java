package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.models.Movie.search;
import static org.junit.jupiter.api.Assertions.*;


class MovieTest {

    @Test
    void sorting_ascending_test() {
//given
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("No short title", "description", Arrays.asList(Movie.Genre.HORROR)));
        movieList.add(new Movie("A short title", "description", Arrays.asList(Movie.Genre.ACTION)));
//when
        Movie.sortingAsc(movieList);

//then
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("A short title", "description", Arrays.asList(Movie.Genre.ACTION)));
        expectedList.add(new Movie("No short title", "description", Arrays.asList(Movie.Genre.HORROR)));
        assertEquals(expectedList.get(0).getTitle(), movieList.get(0).getTitle());
        assertEquals(expectedList.get(1).getTitle(), movieList.get(1).getTitle());
    }


    @Test
    void sorting_descending_test() {
//given
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("No short title", "description", Arrays.asList(Movie.Genre.HORROR)));
        movieList.add(new Movie("A short title", "description", Arrays.asList(Movie.Genre.ACTION)));
        movieList.add(new Movie("Maybe a short title", "description", Arrays.asList(Movie.Genre.ACTION)));
//when
        Movie.sortingDes(movieList);

//then
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("No short title", "description", Arrays.asList(Movie.Genre.HORROR)));
        expectedList.add(new Movie("Maybe a short title", "description", Arrays.asList(Movie.Genre.ACTION)));
        expectedList.add(new Movie("A short title", "description", Arrays.asList(Movie.Genre.ACTION)));

        assertEquals(expectedList.get(0).getTitle(), movieList.get(0).getTitle());
        assertEquals(expectedList.get(1).getTitle(), movieList.get(1).getTitle());
        assertEquals(expectedList.get(2).getTitle(), movieList.get(2).getTitle());
    }


    @Test
    void search_case_insensitive() {
        //given
        String testSearch = "maybe";
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Maybe a short title", "description", Arrays.asList(Movie.Genre.ACTION)));

        //when and then
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("Maybe a short title", "description", Arrays.asList(Movie.Genre.ACTION)));

        assertEquals(expectedList.get(0).getTitle(), search(testSearch, movieList).get(0).getTitle());
    }

    @Test
    void search_delivers_correct_list() {
        String testSearch = "Maybe";
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("No short title", "description", Arrays.asList(Movie.Genre.HORROR)));
        movieList.add(new Movie("A short title", "description", Arrays.asList(Movie.Genre.ACTION)));
        movieList.add(new Movie("Maybe a short title", "description", Arrays.asList(Movie.Genre.ACTION)));
        movieList.add(new Movie("Out of good titles!", "Maybe a description here?", Arrays.asList(Movie.Genre.DRAMA)));

        //when and then
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("Maybe a short title", "description", Arrays.asList(Movie.Genre.ACTION)));
        expectedList.add(new Movie("Out of good titles!", "Maybe a description here?", Arrays.asList(Movie.Genre.DRAMA)));
        assertEquals(expectedList.get(0).getTitle(), search(testSearch, movieList).get(0).getTitle());
        assertEquals(expectedList.get(1).getTitle(), search(testSearch, movieList).get(1).getTitle());
    }

    @Test
    void search_field_is_empty() {
        //given
        String searchString = "";

        //when
        List<Movie> movieList = search(searchString, Movie.initializeMovies());

        //then
        assertEquals(Movie.initializeMovies(),movieList);
    }


    @Test
    void search_string_is_not_valid() {
        //given
        String searchString = "asdjklö123%$%&";
        List <Movie> expectedList = new ArrayList<>();

        //when
        List <Movie> actualmovieList = search(searchString, Movie.initializeMovies());

        //then
        assertEquals(expectedList, actualmovieList);
    }


    @Test
    public void test_filter_by_genre_action() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        List<Movie> expected = Arrays.asList( movies.get(0), movies.get(1) );

        // when
        List<Movie> filteredMovies = Movie.filter(movies, Movie.Genre.ACTION);

        // then
        assertEquals(expected, filteredMovies);
    }

}