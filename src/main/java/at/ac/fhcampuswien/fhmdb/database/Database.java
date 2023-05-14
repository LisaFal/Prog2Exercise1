package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import exceptions.DataBaseException;

import java.sql.SQLException;

public class Database {
    public static final String DB_URL = "jdbc:h2:file:./db/moviesdb";
    public static String username = "user";
    public static String password = "pass";

    private static ConnectionSource connectionSource;
    private Dao<WatchlistEntity, Long> dao;

    //singleton pattern
    private static Database instance;

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(DB_URL, username, password);
        } catch (SQLException e) {
            throw new DataBaseException("Error while creating connection");
        }
    }

    private Database() {
        try {
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource, WatchlistEntity.class);
            createTables();
        } catch (SQLException e) {
            throw new DataBaseException("Error in connection to database!");
        }
    }

    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public Dao<WatchlistEntity, Long> getDao() {
        return dao;
    }

    private static void createTables() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, WatchlistEntity.class);
        } catch (SQLException e) {
            throw new DataBaseException("Error while creating a new table!");
        }
    }
}