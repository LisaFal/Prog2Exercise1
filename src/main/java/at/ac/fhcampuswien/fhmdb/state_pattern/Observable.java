package at.ac.fhcampuswien.fhmdb.state_pattern;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
