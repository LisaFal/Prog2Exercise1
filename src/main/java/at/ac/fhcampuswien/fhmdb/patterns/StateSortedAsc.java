package at.ac.fhcampuswien.fhmdb.patterns;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;
import java.util.List;

public class StateSortedAsc implements State {

    @Override
    public List<Movie> sort(List<Movie> list, Sorter sorter) {
        sorter.setState(new StateSortedDesc());
        list.sort(Comparator.comparing(Movie::getTitle));
        return list;
    }
}
