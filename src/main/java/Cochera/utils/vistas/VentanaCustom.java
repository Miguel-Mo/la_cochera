package Cochera.utils.vistas;

import Cochera.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * Para heredar de esta clase es necesario que el al panel padre se le coloque como id 'parent'
 */
public abstract class VentanaCustom {

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

    /**
     * El botón de cerrar no tiene por qué apagar la aplicación directamente, por lo que obligamos a que
     * se deba implementar por cada caso
     */
    @FXML
    public abstract void cerrar(ActionEvent event);
}
