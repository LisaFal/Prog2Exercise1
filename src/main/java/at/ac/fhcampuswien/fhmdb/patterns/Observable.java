package at.ac.fhcampuswien.fhmdb.patterns;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
