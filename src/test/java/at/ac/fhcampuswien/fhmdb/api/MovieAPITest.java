package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import org.junit.jupiter.api.Test;

import static at.ac.fhcampuswien.fhmdb.api.MovieAPI.URL_START;
import static org.junit.jupiter.api.Assertions.*;

class MovieAPITest {

    @Test
    void no_parameter_set_returns_correct_url() {
        // given
        String expectedUrl = "http://prog2.fh-campuswien.ac.at/movies";

        // when
        String actualUrl = new MovieAPIRequestBuilder(URL_START)
                .query(null)
                .genre(null)
                .releaseYear(-1)
                .ratingFrom(-1)
                .build();

        // then
        assertEquals(expectedUrl, actualUrl);
    }
    @Test
    void only_query_parameter_set_returns_correct_url() {
        // given
        String expectedUrl = "http://prog2.fh-campuswien.ac.at/movies?query=test";

        // when

        String actualUrl = new MovieAPIRequestBuilder(URL_START)
                .query("test")
                .genre(null)
                .releaseYear(-1)
                .ratingFrom(-1)
                .build();

        // then
        assertEquals(expectedUrl, actualUrl);
    }
    @Test
    void query_and_genre_parameter_set_returns_correct_url() {
        // given
        String expectedUrl = "http://prog2.fh-campuswien.ac.at/movies?query=test&genre=DRAMA";

        // when
        String actualUrl = new MovieAPIRequestBuilder(URL_START)
                .query("test")
                .genre(Genre.DRAMA)
                .releaseYear(-1)
                .ratingFrom(-1)
                .build();
        // then
        assertEquals(expectedUrl, actualUrl);
    }
    @Test
    void query_and_genre_and_releaseYear_parameter_set_returns_correct_url() {
        // given
        String expectedUrl = "http://prog2.fh-campuswien.ac.at/movies?query=test&genre=DRAMA&releaseYear=1990";

        // when
        String actualUrl = new MovieAPIRequestBuilder(URL_START)
                .query("test")
                .genre(Genre.DRAMA)
                .releaseYear(1990)
                .ratingFrom(-1)
                .build();

        // then
        assertEquals(expectedUrl, actualUrl);
    }
    @Test
    void query_genre_releaseYear_and_ratingFrom_parameter_set_returns_correct_url() {
        // given
        String expectedUrl = "http://prog2.fh-campuswien.ac.at/movies?query=test&genre=DRAMA&releaseYear=1990&ratingFrom=1.0";

        // when
        String actualUrl = new MovieAPIRequestBuilder(URL_START)
                .query("test")
                .genre(Genre.DRAMA)
                .releaseYear(1990)
                .ratingFrom(1.0)
                .build();

        // then
        assertEquals(expectedUrl, actualUrl);
    }

}