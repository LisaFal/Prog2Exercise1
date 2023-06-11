package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.state_pattern.Observable;
import at.ac.fhcampuswien.fhmdb.state_pattern.Observer;
import com.j256.ormlite.dao.Dao;
import exceptions.DataBaseException;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WatchlistRepository implements Observable {
    private Dao<WatchlistEntity, Long> dao;
    private String genres;
    private List<Observer> observers = new ArrayList<>();

    private static WatchlistRepository instance;

    private WatchlistRepository() {
        this.dao = Database.getDatabase().getDao();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addToWatchlist(WatchlistEntity movie) throws DataBaseException {
        try {
            List<WatchlistEntity> entities = dao.queryForAll();
            for (WatchlistEntity entity : entities) {
                if (entity.equals(movie)) {
                    throw  new DataBaseException("Movie already in watchlist");
                }
            }
            dao.create(movie);
            notifyObservers(); // Notify observers when a movie is added
        } catch (SQLException e) {
            throw new DataBaseException("Error while adding a movie to watchlist!", e);
        }
    }

    public void removeFromWatchlist(WatchlistEntity movie) {
        try {
            List<WatchlistEntity> entities = dao.queryForAll();
            for (WatchlistEntity entity : entities) {
                if (entity.equals(movie)) {
                    dao.delete(entity); // delete the matching entity
                    notifyObservers(); // Notify observers when a movie is removed
                }
            }
        } catch(SQLException e) {
            throw new DataBaseException("Error while removing a movie!", e);
        }
    }

    public List<WatchlistEntity> getAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new DataBaseException("Error while getting watchlist!", e);
        }
    }

    public static synchronized WatchlistRepository getInstance() {
        if(instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
    }


    public boolean isInWatchlist(WatchlistEntity movie) throws DataBaseException {
        try {
            Dao<WatchlistEntity, Long> watchlistDao = Database.getDatabase().getDao();
            List<WatchlistEntity> existingMovies = watchlistDao.queryForEq("title", movie.getTitle());
            return !existingMovies.isEmpty();
        } catch (SQLException e) {
            throw new DataBaseException("Error while accessing the database!", e);
        }
    }


}
