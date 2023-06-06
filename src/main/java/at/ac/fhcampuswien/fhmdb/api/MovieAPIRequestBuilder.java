package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;

import java.util.ArrayList;
import java.util.List;

public class MovieAPIRequestBuilder {
        private String baseUrl;
        private String query;
        private Genre genre;
        private int releaseYear;
        private double ratingFrom;

        public MovieAPIRequestBuilder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public MovieAPIRequestBuilder query(String query) {
            this.query = query;
            return this;
        }

        public MovieAPIRequestBuilder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public MovieAPIRequestBuilder releaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public MovieAPIRequestBuilder ratingFrom(double ratingFrom) {
            this.ratingFrom = ratingFrom;
            return this;
        }

        public String build() {
            List<String> params = new ArrayList<>();
            if (query != null) {
                params.add("query=" + query);
            }
            if (genre != null) {
                params.add("genre=" + genre.name());
            }
            if (releaseYear != -1) {
                params.add("releaseYear=" + releaseYear);
            }
            if (ratingFrom != -1) {
                params.add("ratingFrom=" + ratingFrom);
            }

            String url = baseUrl;
            if (!params.isEmpty()) {
                url += "?" + String.join("&", params);
            }
            return url;
        }
    }

