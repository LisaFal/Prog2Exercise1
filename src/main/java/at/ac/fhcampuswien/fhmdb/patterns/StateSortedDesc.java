package at.ac.fhcampuswien.fhmdb.patterns;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;
import java.util.List;

public class StateSortedDesc implements State {
    public List<Movie> sort(List<Movie> list) {
        list.sort(Comparator.comparing(Movie::getTitle).reversed());
        return list;
    }
}
