package Cochera.utils.vistas;

import Cochera.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VentanaCustom {

    @FXML
    protected Parent parent;
    @FXML
    protected Button close;

    protected double xOffset = 0;
    protected double yOffset = 0;

    // Referencia al Main Principal
    protected Main app;

    /**
     * Nos controla que la ventana se pueda mover por la pantalla al clicar sobre ella. Esta funcionalidad
     * se tiene que implementar por código, porque al quitarle la ventana por defecto perdemos
     * las opciones de arrastre o de cerrar la ventana que nos da el SO
     */
    protected void moverVentana() {
        // Eventos que nos controlan que la pantalla se mueva aunque hayamos eliminado la ventana del SO
        parent.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        parent.setOnMouseDragged(event -> {
            app.getPrimaryStage().setX(event.getScreenX() - xOffset);
            app.getPrimaryStage().setY(event.getScreenY() - yOffset);
        });
    }

    public void setMain(Main app) {
        this.app = app;
    }

    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
