package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WatchlistEntityTest {
    @Test
    void genre_list_to_string() {
        String expected = "THRILLER, SCIENCE_FICTION, CRIME";

        List<Genre> list = Arrays.asList(Genre.THRILLER, Genre.SCIENCE_FICTION, Genre.CRIME);
        String actual = WatchlistEntity.genresToString(list);

        assertEquals(expected, actual);

    }
}
