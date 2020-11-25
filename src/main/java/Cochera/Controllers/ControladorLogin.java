package Cochera.Controllers;

import Cochera.DAO.UsuarioDAO;
import Cochera.Models.Usuario.Usuario;
import Cochera.utils.vistas.VentanaCustom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControladorLogin extends VentanaCustom {

    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoPass;
    @FXML
    private Button login;
    @FXML
    private Label error;

    //Constructor. Es lo primero que se realiza. Antes que initialize()
    public ControladorLogin() { }

    @FXML
    //Inicializa después de que se haya cargado el fxml (la vista) a la que está conectado
    private void initialize() {
        super.moverVentana();

        // Para que al clicar Enter en el teclado se mande el formulario
        parent.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login.fire();
                event.consume();
            }
        });
    }


    @FXML
    public void login(ActionEvent event) {
        resetError();

        if (checkCampos()) {
            try (UsuarioDAO dao = new UsuarioDAO()) {

                Usuario usuario = dao.login(campoUsuario.getText(), campoPass.getText());

                if (usuario == null) {
                    mostrarError("Fallo al ecribir el usuario o la contraseña");
                } else {
                    app.setUsuario(usuario);
                    app.iniciarPanel();
                }

            } catch (SQLException throwables) {
                mostrarError("Fallo al conectar a la Base de Datos");
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

    @Override
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
