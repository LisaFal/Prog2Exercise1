package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    Dao<WatchlistEntity, Long> dao;
    String genres;

    public WatchlistRepository() {
        this.dao = Database.getDatabase().getDao();
    }

    public void addToWatchlist(Movie movie) throws SQLException {
        genres = WatchlistEntity.genresToString(movie.getGenres());
        WatchlistEntity movieEntry = new WatchlistEntity("api?", movie.getTitle(), movie.getDescription(), genres, movie.getReleaseYear(),
                movie.getImgUrl(), movie.getLengthInMinutes(), movie.getRating());
        dao.create(movieEntry);
    }

    public void removeFromWatchlist(Movie movie) throws SQLException {
        genres = WatchlistEntity.genresToString(movie.getGenres());
        WatchlistEntity movieEntry = new WatchlistEntity("api?", movie.getTitle(), movie.getDescription(), genres, movie.getReleaseYear(),
                movie.getImgUrl(), movie.getLengthInMinutes(), movie.getRating());
        dao.delete(movieEntry);

    }

    public List<WatchlistEntity> getAll() throws SQLException {
        return dao.queryForAll();
    }

}
