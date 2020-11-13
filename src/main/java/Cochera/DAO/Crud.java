package Cochera.DAO;

import javafx.collections.ObservableList;

public interface Crud<T> extends AutoCloseable {

    int create(T objeto);
    ObservableList<T> read();
    boolean update(T objeto);
    boolean delete(T objeto);
}
