package at.ac.fhcampuswien.fhmdb.state_pattern;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.state_pattern.State;

import java.util.List;

public class StateNotSorted implements State {

    @Override
    public List<Movie> sort(List<Movie> list) {
        return list;
    }
}
