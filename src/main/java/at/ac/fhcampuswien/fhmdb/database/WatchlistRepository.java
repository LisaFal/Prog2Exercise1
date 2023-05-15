package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import exceptions.DataBaseException;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class WatchlistRepository {
    Dao<WatchlistEntity, Long> dao;
    String genres;

    public WatchlistRepository() {
        this.dao = Database.getDatabase().getDao();
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

}
