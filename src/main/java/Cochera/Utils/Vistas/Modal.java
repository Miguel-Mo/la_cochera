package Cochera.Utils.Vistas;

import Cochera.Controllers.ControladorModal;
import Cochera.Controllers.DataTable;
import Cochera.Models.Modelo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Modal<T extends Modelo> {

    private Stage modal;
    private final DataTable<T> dataTable;
    private final FXMLLoader modalFX;

    public Modal(DataTable<T> dataTable, String recurso) throws IOException {
        this.dataTable = dataTable;
        this.modalFX = new FXMLLoader(dataTable.getClass().getResource(recurso));

        iniciarVentana();
    }

    private void iniciarVentana() throws IOException {
        modal = new Stage();

        modal.setScene(new Scene(modalFX.load()));
        modal.initOwner(dataTable.getRoot().getScene().getWindow());
        modal.initModality(Modality.WINDOW_MODAL);
        modal.initStyle(StageStyle.UNDECORATED);
        modal.alwaysOnTopProperty();
        modal.setResizable(false);

        dataTable.getRoot().setStyle("-fx-opacity: 0.4");
    }

    public void setControlador(ControladorModal<T> controlador) {
        modalFX.setController(controlador);
    }


    public void showAndWait() {
        modal.showAndWait();
    }
}
