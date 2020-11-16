package Cochera.Controllers;

import Cochera.Controllers.AutoRoot;
import Cochera.DAO.*;
import Cochera.Models.Modelo;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;


public abstract class DataTable<T extends Modelo> implements AutoRoot {

    protected Parent root;

    // Tabla
    @FXML protected TableView<T> tabla;

    protected FilteredList<T> listaFiltrable;

    @FXML
    protected void initialize() {
        iniciarTabla();
    }

    protected void iniciarTabla() {
        try (Crud<T> dao = obtenerDao()) {
            // Envolvemos los datos de la base de datos en una lista que nos permita filtrar
            // Lo mantenemos en el estado porque es este tipo de lista la que nos permitirá filtrar por campo en el método correspondiente
            listaFiltrable = new FilteredList<T>(dao.read(), mostrarTodoAlInicio -> true);

            // Actualizamos la tabla cuando haya algún cambio.
            listaFiltrable.addListener((ListChangeListener.Change<? extends T> change) -> tabla.refresh());

            // Volvemos a envolver para darle la capacidad de ordenarse
            SortedList<T> lista = new SortedList<>(listaFiltrable);
            lista.comparatorProperty().bind(tabla.comparatorProperty());

            // Finalmente seteamos la lista para mostrarla en la tabla
            tabla.setItems(lista);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private Crud<T> obtenerDao() throws Exception {
        String rutaClase = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();

        String clase = Arrays.stream(rutaClase.split("\\.")).reduce((primero, ultimo) -> ultimo).get();

        switch (clase) {
            case "Cliente" : return (Crud<T>) new ClienteDAO();
            case "VehiculoVender" : return (Crud<T>) new VehiculoVenderDAO();
            default: throw new Exception("No existe un DAO marcado para " + clase);
        }
    }

    @Override
    public void setRoot(Parent root) {
        this.root = root;
    }
}
