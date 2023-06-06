package at.ac.fhcampuswien.fhmdb.state_pattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.state_pattern.State;

import java.util.Comparator;
import java.util.List;

public class StateSortedDesc implements State {
    public List<Movie> sort(List<Movie> list) {
        list.sort(Comparator.comparing(Movie::getTitle).reversed());
        return list;
    }
}
