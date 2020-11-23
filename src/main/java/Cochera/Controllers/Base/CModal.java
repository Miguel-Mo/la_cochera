package Cochera.Controllers.Base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class CModal {

    protected Parent root;

    @FXML protected Button btnCancelar;

    protected void initialize() {
        btnCancelar.setOnAction(this::cerrar);
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
