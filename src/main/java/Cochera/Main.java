package Cochera;

import Cochera.controllers.ControladorLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initLogin();
    }

    public void initLogin() throws IOException {
        // Cargamos el Layout
        FXMLLoader login = new FXMLLoader(getClass().getResource("/Login/login.fxml"));

        // Lo colocamos como escena en el Stage
        primaryStage.setScene(new Scene(login.load()));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Damos acceso a la app a su controlador
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
}
