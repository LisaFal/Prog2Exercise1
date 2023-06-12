package at.ac.fhcampuswien.fhmdb;

import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {
    private static HomeController hc = new HomeController();
    private static WatchlistController wc = new WatchlistController();
    @Override
    public Object call(Class<?> controllerType) {
        if(controllerType == HomeController.class) {
            return getHomeController();
        } else if(controllerType == WatchlistController.class) {
            return getWatchlistController();
        }
        return null;
    }
    public HomeController getHomeController() {
        return hc;
    }
    public WatchlistController getWatchlistController() {
        return wc;
    }
}
