package at.ac.fhcampuswien.fhmdb.patterns;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public class Sorter {
    private State currentState;

    public Sorter() {
        // bei Öffnen des Programms ist Zustand StateNotSorted
        currentState = new StateNotSorted();
    }

    public void setState(State state) {
        currentState = state;
    }

    public List<Movie> sort(List<Movie> list) {
        return currentState.sort(list);
    }


}
