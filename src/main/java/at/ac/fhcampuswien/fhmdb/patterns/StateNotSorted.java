package at.ac.fhcampuswien.fhmdb.patterns;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public class StateNotSorted implements State {

    @Override
    public List<Movie> sort(List<Movie> list, Sorter sorter) {
        sorter.setState(new StateSortedAsc());
        return list;
    }

}
