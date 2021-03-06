package Cochera;

import Cochera.Controllers.ControladorLogin;
import Cochera.Controllers.ControladorPanel;
import Cochera.Models.Usuario.Usuario;
import Cochera.Models.Usuario.Vendedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage primaryStage;
    public static Usuario usuario;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.initStyle(StageStyle.UNDECORATED);

        iniciarLogin();
    }

    public void iniciarLogin() {
        // Cargamos el Layout
        FXMLLoader login = new FXMLLoader(getClass().getResource("/Login/login.fxml"));

        try {
            // Lo colocamos como escena en el Stage
            primaryStage.setScene(login.load());
            primaryStage.setMaximized(false);

            // Le damos la referencia del Main al controlador
            ControladorLogin controladorLogin =  login.getController();
            controladorLogin.setMain(this);

            // Mostramos
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciarPanel() {
        FXMLLoader panel = new FXMLLoader(getClass().getResource("/Panel.fxml"));

        try {
            primaryStage.setScene(panel.load());
            primaryStage.setMaximized(true);

            ControladorPanel controladorPanel = panel.getController();
            controladorPanel.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setUsuario(Usuario usuario) {
        Main.usuario = usuario;

        Preferences usuarioActivo = Preferences.userRoot();
        usuarioActivo.put("concesionarioID", Integer.toString(usuario.getConcesionarioID()));
        usuarioActivo.put("tipo",usuario.getTipo());
        usuarioActivo.put("id",Integer.toString(usuario.getId()));
    }

    public void cerrarSesion()  {
        try {
            Preferences usuarioActivo = Preferences.userRoot();
            usuarioActivo.clear();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
        usuario = null;
        iniciarLogin();
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
