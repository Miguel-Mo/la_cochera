package Cochera.Controllers;

import Cochera.DAO.*;
import Cochera.Models.Modelo;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;


public abstract class DataTable<T extends Modelo> {

    private final String claseGenerica;

    @FXML protected TableView<T> tabla;
    protected Parent root;
    protected FilteredList<T> listaFiltrable;

    protected String modalCreacionView;
    protected String modalModificacionView;

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

    @FXML
    protected void mostrarModalCreacion() {
        FXMLLoader modalFX = new FXMLLoader(getClass().getResource(modalCreacionView));

        try {
            Stage modal = generarModal(modalFX);
            ControladorModal<T> controlador = modalFX.getController();
            controlador.setRoot(root);
            controlador.setLista(listaFiltrable);

            modal.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void mostrarModalModificacion(T objeto) {
        FXMLLoader modalFX = new FXMLLoader(getClass().getResource(modalModificacionView));

        try {
            Stage modal = generarModal(modalFX);
            ControladorModal<T> controlador = modalFX.getController();
            controlador.setRoot(root);
            controlador.setObjeto(objeto);

            modal.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Stage generarModal(FXMLLoader modalFX) throws IOException {
        Stage modal = new Stage();

        modal.setScene(new Scene(modalFX.load()));
        modal.initOwner(root.getScene().getWindow());
        modal.initModality(Modality.WINDOW_MODAL);
        modal.initStyle(StageStyle.UNDECORATED);
        modal.alwaysOnTopProperty();
        modal.setResizable(false);

        root.setStyle("-fx-opacity: 0.4");

        return modal;
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
