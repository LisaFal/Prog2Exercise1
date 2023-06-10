package at.ac.fhcampuswien.fhmdb;

import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    @Override
    public Object call(Class<?> param) {
        if(param.getName().equals("at.ac.fhcampuswien.fhmdb.HomeController")) {
            return HomeController.getInstance();
        } else if(param.getName().equals("at.ac.fhcampuswien.fhmdb.WatchlistController")) {
            return WatchlistController.getInstance();
        }
        return null;
    }
}
