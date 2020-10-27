package Cochera.controllers;

import Cochera.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControladorLogin {

    @FXML
    private Button close;

    @FXML
    private BorderPane parent;

    private double xOffset = 0;
    private double yOffset = 0;

    // Referencia a la Main Principal
    private Main app;

    //Constructor. Es lo primero que se realiza. Antes que initialize()
    public ControladorLogin() { }

    //Inicializa el constructor después de que se haya cargado el fxml (la vista) a la que está conectado
    @FXML
    private void initialize() {
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
