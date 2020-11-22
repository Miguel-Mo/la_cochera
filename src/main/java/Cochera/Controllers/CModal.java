package Cochera.Controllers;

import Cochera.DAO.Crud;
import Cochera.DAO.DAOFactory;
import Cochera.Models.Modelo;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public abstract class CModal<T extends Modelo> {

    protected final String claseGenerica;
    protected Parent root;

    @FXML protected Button btnCancelar;


    public CModal() {
        String rutaClase = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        claseGenerica = Arrays.stream(rutaClase.split("\\.")).reduce((primero, ultimo) -> ultimo).get();
    }

    @FXML
    protected void cerrar(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        root.setStyle("-fx-opacity: 1");
        stage.close();
    }

    public void setRoot(Parent root) {
        this.root = root;
    }
}
