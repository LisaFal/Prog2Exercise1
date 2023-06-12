package at.ac.fhcampuswien.fhmdb.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WatchlistRepositoryTest {

    @BeforeEach
    void setUp() {
        WatchlistRepository.reset();
    }
    @Test
    void check_if_singleton() {
        WatchlistRepository instance1 = WatchlistRepository.getInstance();
        WatchlistRepository instance2 = WatchlistRepository.getInstance();
        assertTrue(instance1 == instance2);
    }
}
