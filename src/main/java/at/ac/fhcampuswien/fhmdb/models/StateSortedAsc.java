package at.ac.fhcampuswien.fhmdb.models;

import java.util.Comparator;
import java.util.List;

public class StateSortedAsc implements State {

    @Override
    public List<Movie> sort(List<Movie> list) {
        list.sort(Comparator.comparing(Movie::getTitle));
        return list;
    }
}
