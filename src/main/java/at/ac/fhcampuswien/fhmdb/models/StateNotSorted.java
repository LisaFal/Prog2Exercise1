package at.ac.fhcampuswien.fhmdb.models;

import java.util.List;

public class StateNotSorted implements State {

    @Override
    public List<Movie> sort(List<Movie> list) {
        return list;
    }
}
