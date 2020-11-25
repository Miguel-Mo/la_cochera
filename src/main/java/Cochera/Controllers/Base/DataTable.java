package Cochera.Controllers.Base;

import Cochera.DAO.base.Crud;
import Cochera.DAO.base.DAOFactory;
import Cochera.Models.Modelo;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;


public abstract class DataTable<T extends Modelo> {

    private final String claseGenerica;

    @FXML protected TableView<T> tabla;
    protected Parent root;
    protected FilteredList<T> listaFiltrable;

    public DataTable() {
        String rutaClase = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        claseGenerica = Arrays.stream(rutaClase.split("\\.")).reduce((primero, ultimo) -> ultimo).get();
    }

    @FXML
    protected void initialize() {
        iniciarTabla();
    }

    protected void iniciarTabla() {
        try (Crud<T> dao = DAOFactory.obtener(claseGenerica)) {
            // Envolvemos los datos de la base de datos en una lista que nos permita filtrar
            // Lo mantenemos en el estado porque es este tipo de lista la que nos permitirá filtrar por campo en el método correspondiente
            listaFiltrable = new FilteredList<>(dao.read(), mostrarTodoAlInicio -> true);

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

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Parent getRoot() {
        return root;
    }

    public FilteredList<T> getListaFiltrable() {
        return listaFiltrable;
    }
}
