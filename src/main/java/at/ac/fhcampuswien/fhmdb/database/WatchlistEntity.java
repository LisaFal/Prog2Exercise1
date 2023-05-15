package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DatabaseTable(tableName = "movie")
public class WatchlistEntity {


    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField()
    private String apiId;
    @DatabaseField()
    private String title;
    @DatabaseField()
    private String description;
    @DatabaseField()
    private String genres;
    @DatabaseField()
    private int releaseYear;
    @DatabaseField()
    private String imgURL;
    @DatabaseField()
    private int lengthInMinutes;
    @DatabaseField()
    private double rating;

    public WatchlistEntity() {}

    public WatchlistEntity(Movie movie) {
        this.apiId = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.genres = genresToString(movie.getGenres());
        this.releaseYear = movie.getReleaseYear();
        this.imgURL = movie.getImgUrl();
        this.lengthInMinutes = movie.getLengthInMinutes();
        this.rating = movie.getRating();
    }

    public long getId() {
        return id;
    }

    public String getApiId() {
        return apiId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }


    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    public static String genresToString(List<Genre> genres) {
        List<String> genreNames = genres.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        return String.join(", ", genreNames);
    }

    public String getImgUrl() {
        return imgURL;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WatchlistEntity other = (WatchlistEntity) obj;
        return Objects.equals(this.title, other.title);
    }
}



