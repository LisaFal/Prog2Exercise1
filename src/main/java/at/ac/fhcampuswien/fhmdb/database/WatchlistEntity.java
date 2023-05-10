package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;
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
    public WatchlistEntity(String apiId, String title, String description, String genres, int releaseYear, String imgURL, int lengthInMinutes, double rating) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgURL = imgURL;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public static String genresToString(List<Genre> genres) {
        List<String> genreNames = genres.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        return String.join(", ", genreNames);
    }

}



