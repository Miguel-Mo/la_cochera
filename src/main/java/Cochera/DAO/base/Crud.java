package Cochera.DAO.base;

import javafx.collections.ObservableList;

public interface Crud<T> extends AutoCloseable {

    int create(T objeto);
    ObservableList<T> read();
    T read(int id);
    boolean update(T objeto);
    boolean delete(T objeto);
}
