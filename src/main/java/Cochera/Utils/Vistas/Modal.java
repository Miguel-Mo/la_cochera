package Cochera.Utils.Vistas;

import Cochera.Controllers.Base.CMNuevoEditar;
import Cochera.Controllers.Base.CModal;
import Cochera.Controllers.Base.DataTable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Modal {

    private Stage modal;
    private final DataTable dataTable;
    private final FXMLLoader modalFX;

    public Modal(DataTable dataTable, String recurso) throws IOException {
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

    public void setControlador(CModal controlador) {
        modalFX.setController(controlador);
        controlador.setRoot(dataTable.getRoot());
    }

    public void setControlador(CMNuevoEditar controlador) {
        modalFX.setController(controlador);
        controlador.setRoot(dataTable.getRoot());
        controlador.setLista(dataTable.getListaFiltrable());
    }


    public void showAndWait() {
        modal.showAndWait();
    }
}
