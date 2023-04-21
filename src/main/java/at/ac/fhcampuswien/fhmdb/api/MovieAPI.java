package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Rating;
import at.ac.fhcampuswien.fhmdb.models.ReleaseYear;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieAPI {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String urlStart = "http://prog2.fh-campuswien.ac.at/movies";

    private static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .addHeader("User-Agent", "Chrome")
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response
                    .body()
                    .string();
        }
    }

    private static String createUrl(String query, Genre genre, int releaseYear, double ratingFrom) {
        String url = urlStart;
        List<String> params = new ArrayList<>();
        if(query != null) {
            params.add("query=" + query);
        }
        if(genre != null) {
            params.add("genre=" + genre.name());
        }
        if(releaseYear != -1) {
            params.add("releaseYear=" + releaseYear);
        }
        if(ratingFrom != -1) {
            params.add("ratingFrom=" + ratingFrom);
        }
        for(int i = 0; i < params.size(); i++) {
            if(i == 0)
                url += "?" + params.get(i);
            else
                url += "&" + params.get(i);
        }
        return url;
    }
    public static List<Movie> fetchMovies(String query, Genre genre, int releaseYear, double ratingFrom) throws IOException {
        String response = null;
        response = run(createUrl(query, genre, releaseYear, ratingFrom));
        Gson gson = new Gson();
        Movie[] movies = gson.fromJson(response, Movie[].class);
        return Arrays.asList(movies);
    }
}
