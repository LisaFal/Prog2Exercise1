package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    List<Movie> dummyMovies = new ArrayList<>();
    List<Movie> dummyMov2 = new ArrayList<>();

    HomeControllerTest() throws IOException {
    }

    @BeforeEach
    void createMovieList() throws IOException {

        dummyMovies.add(new Movie("Midnight Escape", "A group of friends embark on a dangerous journey to break out of a maximum-security prison in order to clear their names.", Arrays.asList(Genre.ACTION, Genre.DRAMA)));
        dummyMovies.add(new Movie("Lost Treasure of the Amazon", "A team of explorers search for a legendary treasure in the heart of the Amazon jungle, but they soon realize that they are not alone.", Arrays.asList(Genre.ACTION, Genre.HORROR)));
        dummyMovies.add(new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        dummyMovies.add(new Movie("Saw", "A very bloody film about people who have to cut off their limbs.", Arrays.asList(Genre.HORROR, Genre.THRILLER, Genre.CRIME)));
        dummyMovies.add(new Movie("The Sound of Silence", "A drama about a young musician who struggles to come to terms with his deafness, and must find a new way to connect with the world and his art.", Arrays.asList(Genre.DRAMA, Genre.SCIENCE_FICTION)));
        dummyMovies.add(new Movie("The Art of War", "A suspenseful thriller about a military strategist who must outwit a dangerous terrorist group who threatens to plunge the world into chaos.", Arrays.asList(Genre.THRILLER, Genre.SCIENCE_FICTION, Genre.CRIME)));
        dummyMovies.add(new Movie("The Hidden City", "An action-packed adventure about a team of treasure hunters who journey to a remote jungle to uncover a lost city filled with untold riches.", Arrays.asList(Genre.ADVENTURE, Genre.ANIMATION)));
        dummyMovies.add(new Movie("The Spy Next Door", "A hilarious family comedy about a retired spy who must juggle his secret past with his mundane suburban life when his neighbors become embroiled in a dangerous plot.", Arrays.asList(Genre.COMEDY, Genre.ANIMATION)));
        dummyMovies.add(new Movie("The Last Princess", "A historical drama about a young princess who must navigate the treacherous political landscape of her kingdom to protect her family and people.", Arrays.asList(Genre.HISTORY, Genre.BIOGRAPHY, Genre.DOCUMENTARY)));
        dummyMovies.add(new Movie("The Silent Witness", "A tense courtroom drama about a lawyer defending a young man accused of murder, who refuses to speak about what really happened.", Arrays.asList(Genre.CRIME, Genre.FANTASY)));
        dummyMovies.add(new Movie("A Song for Emily", "A heartwarming musical about a young girl who discovers her talent for singing and overcomes adversity to follow her dreams.", Arrays.asList(Genre.MUSICAL, Genre.FAMILY)));
        dummyMovies.add(new Movie("The Final Frontier", "A group of astronauts embark on a perilous journey to explore a distant planet, but they soon realize that something sinister is lurking on the surface.", Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        dummyMovies.add(new Movie("The Great Escape Room", "A group of strangers must work together to solve a series of puzzles and escape a deadly game created by a twisted mastermind.", Arrays.asList(Genre.THRILLER, Genre.WESTERN)));
        dummyMovies.add(new Movie("The Iron Horsemen", "A gripping western about a lone rider who seeks revenge against the corrupt officials who wronged him.", Arrays.asList(Genre.HORROR, Genre.WESTERN, Genre.WAR)));
        dummyMovies.add(new Movie("Murder on the Nile", "A classic whodunit mystery set on a luxury cruise ship sailing down the Nile river.", Arrays.asList(Genre.MYSTERY, Genre.ADVENTURE, Genre.CRIME)));



        dummyMov2.addAll(MovieAPI.fetchMovies(null, null, -1, -1));
    }
    @Test
    void filterMovies_empty_textfield_and_no_genre_selected_returns_all_movies() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = dummyMovies;

        // when
        List<Movie> movies = hc.filterMovies(null, "", dummyMovies);

        // then
        assertEquals(expectedMovies, movies);
    }
    @Test
    void filterMovies_empty_textfield_and_String_selected_returns_all_movies() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = dummyMovies;

        // when
        List<Movie> movies = hc.filterMovies("-- NO FILTER --", "", dummyMovies);

        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_single_l_and_no_Genre_selected_returns_all_movies() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = dummyMovies;

        // when
        List<Movie> movies = hc.filterMovies(null, "l", dummyMovies);

        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_empty_and_Genre_selected_returns_movies_of_that_genre() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie("Midnight Escape", "A group of friends embark on a dangerous journey to break out of a maximum-security prison in order to clear their names.", Arrays.asList(Genre.ACTION, Genre.DRAMA)));
        expectedMovies.add(new Movie("Lost Treasure of the Amazon", "A team of explorers search for a legendary treasure in the heart of the Amazon jungle, but they soon realize that they are not alone.", Arrays.asList(Genre.ACTION, Genre.HORROR)));

        // when
        List<Movie> movies = hc.filterMovies(Genre.ACTION, "", dummyMovies);


        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_contains_Titanic_and_Genre_Romance_selected_returns_Titanic() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));

        // when
        List<Movie> movies = hc.filterMovies(Genre.ROMANCE, "Titanic", dummyMovies);


        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_contains_Titanic_and_Genre_Drama_selected_returns_Titanic() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));

        // when
        List<Movie> movies = hc.filterMovies(Genre.DRAMA, "Titanic", dummyMovies);


        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }
    @Test
    void filterMovies_textfield_contains_Titanic_and_Genre_Action_selected_returns_empty_List() {
        // given
        HomeController hc = new HomeController();
        List<Movie> expectedMovies = new ArrayList<>();

        // when
        List<Movie> movies = hc.filterMovies(Genre.ACTION, "Titanic", dummyMovies);


        // then
        assertArrayEquals(expectedMovies.toArray(), movies.toArray());
    }

    // test for exercise 2 - java streams methods



    private final HomeController controller = new HomeController();





    @Test
    void testGetMostPopularActor() {
        String mostPopularActor = controller.getMostPopularActor(dummyMov2);
        assertEquals("Tom Hanks", mostPopularActor);
    }

    @Test
    void testGetLongestMovieTitle() {
        int longestMovieTitle = controller.getLongestMovieTitle(dummyMov2);
        assertEquals(46, longestMovieTitle);
    }

    @Test
    void testCountMoviesFrom() {
        long movieCount = controller.countMoviesFrom(dummyMov2, "Christopher Nolan");
        assertEquals(2, movieCount);
    }

    @Test
    void testGetMoviesBetweenYears() {
        List<Movie> moviesBetweenYears = controller.getMoviesBetweenYears(dummyMov2, 2000, 2010);
        assertEquals(7, moviesBetweenYears.size());
    }





}