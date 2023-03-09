package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.HomeController;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MovieTest {

    @Test
    void test_initialize_movie_list () {

    }

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
        Assert.assertEquals(expectedList.get(0).getTitle(), movieList.get(0).getTitle());
        Assert.assertEquals(expectedList.get(1).getTitle(), movieList.get(1).getTitle());

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

        Assert.assertEquals(expectedList.get(0).getTitle(), movieList.get(0).getTitle());
        Assert.assertEquals(expectedList.get(1).getTitle(), movieList.get(1).getTitle());
        Assert.assertEquals(expectedList.get(2).getTitle(), movieList.get(2).getTitle());

    }

}