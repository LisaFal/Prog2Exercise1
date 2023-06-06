package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import exceptions.MovieAPIException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieAPI {
    private static final OkHttpClient client = new OkHttpClient();
    public static final String URL_START = "http://prog2.fh-campuswien.ac.at/movies";

    private static String run(String url) {
        Request request = new Request.Builder()
                .addHeader("User-Agent", "Chrome")
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response
                    .body()
                    .string();
        } catch (IOException e) {
            throw new MovieAPIException("Something went wrong with the API!", e);
        }
    }

    public static List<Movie> fetchMovies(String query, Genre genre, int releaseYear, double ratingFrom) {
        try {
            String response = run(new MovieAPIRequestBuilder(URL_START)
                    .query(query)
                    .genre(genre)
                    .releaseYear(releaseYear)
                    .ratingFrom(ratingFrom)
                    .build());
            Gson gson = new Gson();
            Movie[] movies = gson.fromJson(response, Movie[].class);
            return Arrays.asList(movies);
        } catch (Exception exception) {
            throw new MovieAPIException("Something went wrong with fetching data from your API!", exception);
        }
    }
}
