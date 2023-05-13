package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class WatchlistRepository {
    Dao<WatchlistEntity, Long> dao;
    String genres;

    public WatchlistRepository() {
        this.dao = Database.getDatabase().getDao();
    }

    public void addToWatchlist(WatchlistEntity movie) throws SQLException {
        genres = movie.getGenres();
        WatchlistEntity movieEntry = new WatchlistEntity("?", movie.getTitle(), movie.getDescription(), genres, movie.getReleaseYear(),
                movie.getImgUrl(), movie.getLengthInMinutes(), movie.getRating());
        dao.create(movieEntry);
    }


    public void removeFromWatchlist(WatchlistEntity movie) throws SQLException {
        List<WatchlistEntity> entities = dao.queryForAll();
        for (WatchlistEntity entity : entities) {
            if (entity.equals(movie)) {
                dao.delete(entity); // delete the matching entity
            }
        }
    }

    public List<WatchlistEntity> getAll() throws SQLException {
        return dao.queryForAll();
    }



}
