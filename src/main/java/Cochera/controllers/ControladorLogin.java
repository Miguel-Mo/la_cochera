package Cochera.controllers;

import Cochera.Main;
import Cochera.dao.UsuarioDAO;
import Cochera.models.Usuario.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControladorLogin {

    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoPass;
    @FXML
    private Button close;
    @FXML
    private Button login;
    @FXML
    private BorderPane parent;
    @FXML
    private Label error;

    private double xOffset = 0;
    private double yOffset = 0;

    // Referencia al Main Principal
    private Main app;

    //Constructor. Es lo primero que se realiza. Antes que initialize()
    public ControladorLogin() { }

    @FXML
    //Inicializa después de que se haya cargado el fxml (la vista) a la que está conectado
    private void initialize() {

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
    public void login(ActionEvent event) {
        resetError();

        if (checkCampos()) {
            Usuario usuario = new UsuarioDAO().login(campoUsuario.getText(), campoPass.getText());

            if (usuario == null) {
                mostrarError("Fallo al ecribir el usuario o la contraseña");
            } else {
                app.setUsuario(usuario);
                System.out.println("Usuario Logueado");
            }
        }
    }

    private boolean checkCampos() {
        if (campoUsuario.getText().isEmpty() || campoPass.getText().isEmpty()) {
            mostrarError("No puede haber campos vacíos");
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje) {
        error.setText(mensaje);
        error.setVisible(true);
    }

    private void resetError() {
        error.setVisible(false);
        error.setText("");
    }

    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
