package at.ac.fhcampuswien.fhmdb.models;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here

    private List<Genre> genres;

    public enum Genre {

        CLEAR_FILTER,
        ACTION,
        ADVENTURE,
        ANIMATION,
        BIOGRAPHY,
        COMEDY,
        CRIME,
        DRAMA,
        DOCUMENTARY,
        FAMILY,
        FANTASY,
        HISTORY,
        HORROR,
        MUSICAL,
        MYSTERY,
        ROMANCE,
        SCIENCE_FICTION,
        SPORT,
        THRILLER,
        WAR,
        WESTERN
    }


    public Movie(String title, String description, List <Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;

    }

    public List<Genre> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here

        movies.add(new Movie("Midnight Escape", "A group of friends embark on a dangerous journey to break out of a maximum-security prison in order to clear their names.", Arrays.asList(Genre.ACTION, Genre.DRAMA)));
        movies.add(new Movie("Lost Treasure of the Amazon", "A team of explorers search for a legendary treasure in the heart of the Amazon jungle, but they soon realize that they are not alone.", Arrays.asList(Genre.ACTION, Genre.HORROR)));
        movies.add(new Movie("Titanic", "Two stangers fall in love on a ship which unfortunately sinks.", Arrays.asList(Genre.ROMANCE, Genre.DRAMA)));
        movies.add(new Movie("Saw", "A very bloody film about people who have to cut off their limbs.", Arrays.asList(Genre.HORROR, Genre.THRILLER, Genre.CRIME)));
        movies.add(new Movie("The Sound of Silence", "A drama about a young musician who struggles to come to terms with his deafness, and must find a new way to connect with the world and his art.", Arrays.asList(Genre.DRAMA, Genre.SCIENCE_FICTION)));
        movies.add(new Movie("The Art of War", "A suspenseful thriller about a military strategist who must outwit a dangerous terrorist group who threatens to plunge the world into chaos.", Arrays.asList(Genre.THRILLER, Genre.SCIENCE_FICTION, Genre.CRIME)));
        movies.add(new Movie("The Hidden City", "An action-packed adventure about a team of treasure hunters who journey to a remote jungle to uncover a lost city filled with untold riches.", Arrays.asList(Genre.ADVENTURE, Genre.ANIMATION)));
        movies.add(new Movie("The Spy Next Door", "A hilarious family comedy about a retired spy who must juggle his secret past with his mundane suburban life when his neighbors become embroiled in a dangerous plot.", Arrays.asList(Genre.COMEDY, Genre.ANIMATION)));
        movies.add(new Movie("The Last Princess", "A historical drama about a young princess who must navigate the treacherous political landscape of her kingdom to protect her family and people.", Arrays.asList(Genre.HISTORY, Genre.BIOGRAPHY, Genre.DOCUMENTARY)));
        movies.add(new Movie("The Silent Witness", "A tense courtroom drama about a lawyer defending a young man accused of murder, who refuses to speak about what really happened.", Arrays.asList(Genre.CRIME, Genre.FANTASY)));
        movies.add(new Movie("A Song for Emily", "A heartwarming musical about a young girl who discovers her talent for singing and overcomes adversity to follow her dreams.", Arrays.asList(Genre.MUSICAL, Genre.FAMILY)));
        movies.add(new Movie("The Final Frontier", "A group of astronauts embark on a perilous journey to explore a distant planet, but they soon realize that something sinister is lurking on the surface.", Arrays.asList(Genre.SCIENCE_FICTION, Genre.ADVENTURE)));
        movies.add(new Movie("The Great Escape Room", "A group of strangers must work together to solve a series of puzzles and escape a deadly game created by a twisted mastermind.", Arrays.asList(Genre.THRILLER, Genre.WESTERN)));
        movies.add(new Movie("The Iron Horsemen", "A gripping western about a lone rider who seeks revenge against the corrupt officials who wronged him.", Arrays.asList(Genre.HORROR, Genre.WESTERN, Genre.WAR)));
        movies.add(new Movie("Murder on the Nile", "A classic whodunit mystery set on a luxury cruise ship sailing down the Nile river.", Arrays.asList(Genre.MYSTERY, Genre.ADVENTURE, Genre.CRIME)));


        return movies;
    }


    public static List<Movie> filter(List<Movie> movies, Movie.Genre genre) {
        if (genre == null || genre == Genre.CLEAR_FILTER) {
            return movies;
        }

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenres().contains(genre)) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

}
