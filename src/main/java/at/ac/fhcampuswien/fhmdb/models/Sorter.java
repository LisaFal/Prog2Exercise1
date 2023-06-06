package at.ac.fhcampuswien.fhmdb.models;

import java.util.List;

public class Sorter {
    private State currentState;

    public Sorter() {
        // Initialer Zustand ist StateNotSorted
        currentState = new StateNotSorted();
    }

    public void setState(State state) {
        currentState = state;
    }

    public List<Movie> sort(List<Movie> list) {
        return currentState.sort(list);
    }


}
