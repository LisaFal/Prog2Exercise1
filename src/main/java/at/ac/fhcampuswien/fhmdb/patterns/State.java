package at.ac.fhcampuswien.fhmdb.patterns;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public interface State {
    List<Movie> sort(List<Movie> list, Sorter sorter);
}
