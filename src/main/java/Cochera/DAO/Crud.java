package Cochera.DAO;

import javafx.collections.ObservableList;

interface Crud<T> {

    int create(T objeto);
    ObservableList<T> read();
    boolean update(T objeto);
    boolean delete(T objeto);
}
