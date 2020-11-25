package Cochera.utils.vistas;

import Cochera.Controllers.Base.CMNuevoEditar;
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
    private boolean esVista;

    public Modal(DataTable dataTable, String recurso) {
        this.dataTable = dataTable;
        this.modalFX = new FXMLLoader(dataTable.getClass().getResource(recurso));
    }

    public void setControlador(CMNuevoEditar controlador) {
        modalFX.setController(controlador);
        controlador.setRoot(dataTable.getRoot());
        controlador.setLista(dataTable.getListaFiltrable());
    }


    public void showAndWait() {
        iniciarVentana();
        if (esVista) ((CMNuevoEditar) modalFX.getController()).prohibirEdicion();
        modal.showAndWait();
    }

    private void iniciarVentana()  {
        modal = new Stage();

        try {
            modal.setScene(new Scene(modalFX.load()));
            modal.initOwner(dataTable.getRoot().getScene().getWindow());
            modal.initModality(Modality.WINDOW_MODAL);
            modal.initStyle(StageStyle.UNDECORATED);
            modal.alwaysOnTopProperty();
            modal.setResizable(false);

            dataTable.getRoot().setStyle("-fx-opacity: 0.4");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void esVista() {
        this.esVista = true;
    }
}
