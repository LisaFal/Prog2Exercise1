package at.ac.fhcampuswien.fhmdb;

import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    @Override
    public Object call(Class<?> controllerType) {
        if(controllerType == HomeController.class) {
            return HomeController.getInstance();
        } else if(controllerType == WatchlistController.class) {
            return WatchlistController.getInstance();
        }
        return null;
    }
}
