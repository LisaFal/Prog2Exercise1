package at.ac.fhcampuswien.fhmdb.models;

@FunctionalInterface
public interface ClickEventHandler<T>{
    void onClick(T t);
}
