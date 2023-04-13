package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.models.Movie.search;
import static org.junit.jupiter.api.Assertions.*;


class MovieTest {

    List<Movie> dummyMovies = new ArrayList<>();
    @BeforeEach
    void createMovieList() {
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
    }

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
        List<Movie> expected = Arrays.asList( dummyMovies.get(0), dummyMovies.get(1) );

        // when
        List<Movie> filteredMovies = Movie.filter(dummyMovies, Genre.ACTION);

        // then
        assertEquals(expected, filteredMovies);
    }

    @Test
    void test_filter_by_genre_western() {
        // given
        List<Movie> expected = Arrays.asList( dummyMovies.get(12), dummyMovies.get(13));

        // when
        List<Movie> filteredMovies = Movie.filter(dummyMovies, Genre.WESTERN);

        // then
        assertEquals(expected, filteredMovies);
    }
    @Test
    void test_filter_wrong_movie_for_genre_crime() {
        // given
        List<Movie> expected = Arrays.asList( dummyMovies.get(0), dummyMovies.get(1) );

        // when
        List<Movie> filteredMovies = Movie.filter(dummyMovies, Genre.CRIME);

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