package Cochera;

import Cochera.controllers.ControladorLogin;
import Cochera.models.Usuario.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private Usuario usuario;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        iniciarLogin();
    }

    public void iniciarLogin() throws IOException {
        // Cargamos el Layout
        FXMLLoader login = new FXMLLoader(getClass().getResource("/Login/login.fxml"));

        // Lo colocamos como escena en el Stage
        primaryStage.setScene(new Scene(login.load()));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Le damos la referencia del Main al controlador
        ControladorLogin controladorLogin =  login.getController();
        controladorLogin.setMain(this);

        // Mostramos
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
