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
        movieList.add(new Movie("No short title", "description", Arrays.asList(Genre.HORROR)));
        movieList.add(new Movie("A short title", "description", Arrays.asList(Genre.ACTION)));
//when
        Movie.sortingAsc(movieList);
//then
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("A short title", "description", Arrays.asList(Genre.ACTION)));
        expectedList.add(new Movie("No short title", "description", Arrays.asList(Genre.HORROR)));
        assertEquals(expectedList, movieList);
    }

    @Test
    void sorting_with_one_entry() {
        //given
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("No short title", "description", Arrays.asList(Genre.HORROR)));
        //when
        List<Movie> expectedList = Movie.sortingAsc(movieList);
        //then
        assertEquals(expectedList, movieList);
    }


    @Test
    void sorting_descending_test() {
//given
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("No short title", "description", Arrays.asList(Genre.HORROR)));
        movieList.add(new Movie("A short title", "description", Arrays.asList(Genre.ACTION)));
        movieList.add(new Movie("Maybe a short title", "description", Arrays.asList(Genre.ACTION)));
//when
        Movie.sortingDes(movieList);

//then
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("No short title", "description", Arrays.asList(Genre.HORROR)));
        expectedList.add(new Movie("Maybe a short title", "description", Arrays.asList(Genre.ACTION)));
        expectedList.add(new Movie("A short title", "description", Arrays.asList(Genre.ACTION)));

        assertEquals(expectedList, movieList);
    }


    @Test
    void search_case_insensitive() {
        //given
        String testSearch = "maybe";
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Maybe a short title", "description", Arrays.asList(Genre.ACTION)));

        //when and then
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("Maybe a short title", "description", Arrays.asList(Genre.ACTION)));

        assertEquals(expectedList, search(testSearch, movieList));
    }

    @Test
    void search_delivers_correct_list() {
        String testSearch = "Maybe";
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("No short title", "description", Arrays.asList(Genre.HORROR)));
        movieList.add(new Movie("A short title", "description", Arrays.asList(Genre.ACTION)));
        movieList.add(new Movie("Maybe a short title", "description", Arrays.asList(Genre.ACTION)));
        movieList.add(new Movie("Out of good titles!", "Maybe a description here?", Arrays.asList(Genre.DRAMA)));

        //when and then
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("Maybe a short title", "description", Arrays.asList(Genre.ACTION)));
        expectedList.add(new Movie("Out of good titles!", "Maybe a description here?", Arrays.asList(Genre.DRAMA)));
        assertEquals(expectedList, search(testSearch, movieList));
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
    void test_filter_by_genre_action() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        List<Movie> expected = Arrays.asList( movies.get(0), movies.get(1) );

        // when
        List<Movie> filteredMovies = Movie.filter(movies, Genre.ACTION);

        // then
        assertEquals(expected, filteredMovies);
    }

    @Test
    void test_filter_by_genre_western() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        List<Movie> expected = Arrays.asList( movies.get(12), movies.get(13));

        // when
        List<Movie> filteredMovies = Movie.filter(movies, Genre.WESTERN);

        // then
        assertEquals(expected, filteredMovies);
    }
    @Test
    void test_filter_wrong_movie_for_genre_crime() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        List<Movie> expected = Arrays.asList( movies.get(0), movies.get(1) );

        // when
        List<Movie> filteredMovies = Movie.filter(movies, Genre.CRIME);

        // then
        assertNotEquals(expected, filteredMovies);
    }

    @Test
    void equals_same_object_reference() {
        // given
        Movie movie = new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA));

        // when
        Movie sameMovie = movie;

        // then
        assertTrue(movie.equals(sameMovie));
    }
    @Test
    void equals_same_object() {
        // given
        Movie movie = new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA));

        // when
        Movie sameMovie = new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA));

        // then
        assertTrue(movie.equals(sameMovie));
    }
    @Test
    void equals_no_movie() {
        // given
        Movie movie = new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA));

        // when
        String noMovie = "not a movie!";

        // then
        assertFalse(movie.equals(noMovie));
    }
    @Test
    void equals_not_same_movie() {
        // given
        Movie movie = new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA));

        // when
        Movie differentMovie = new Movie("The Sound of Silence", "A drama about a young musician who struggles to come to terms with his deafness, and must find a new way to connect with the world and his art.", Arrays.asList(Genre.DRAMA, Genre.SCIENCE_FICTION));

        // then
        assertFalse(movie.equals(differentMovie));
    }







}