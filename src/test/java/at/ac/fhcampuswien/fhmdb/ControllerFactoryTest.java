package at.ac.fhcampuswien.fhmdb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerFactoryTest {

    @Test
    void check_if_factory_returns_same_instance_of_Homecontroller() {
        ControllerFactory cf1 = new ControllerFactory();
        HomeController hc1 = cf1.getHomeController();
        ControllerFactory cf2 = new ControllerFactory();
        HomeController hc2 = cf2.getHomeController();
        assertTrue(hc1 == hc2);
    }
    @Test
    void check_if_factory_returns_same_instance_of_WatchlistController() {
        ControllerFactory cf1 = new ControllerFactory();
        WatchlistController wc1 = cf1.getWatchlistController();
        ControllerFactory cf2 = new ControllerFactory();
        WatchlistController wc2 = cf2.getWatchlistController();
        assertTrue(wc1 == wc2);
    }

}
