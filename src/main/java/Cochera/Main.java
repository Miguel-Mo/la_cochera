package Cochera;

import Cochera.Controllers.ControladorLogin;
import Cochera.Controllers.ControladorPanel;
import Cochera.Models.Usuario.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage primaryStage;
    private Usuario usuario;

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

        /* ZONA DE PRUEBAS */


//      LECTURA
//        try (ClienteDAO vdao = new ClienteDAO()) {
//            ObservableList<Cliente> v = vdao.read();
//            v.forEach(System.out::println);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        // ELIMINAR
//        try (ClienteDAO vdao = new ClienteDAO()) {
//            Cliente v = vdao.read(52);
//            vdao.delete(v);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }


       // CREACION
//        try {
//            HashMap<String, Object> datos = new HashMap<>();
//
//            datos.put("id",1);
//            datos.put("nombre","Vendedor");
//            datos.put("apellidos","vendedor");
//            datos.put("telefono","600600666");
//            datos.put("dni","80180180S");
//            datos.put("email","vendedor@prueba.com");
//            datos.put("presupuesto",20000f);
//
//            Cliente vv = new Cliente(datos);
//
//            try (ClienteDAO vdao = new ClienteDAO()) {
//                vdao.create(vv);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        // ACTUALIZAR
//        try (ClienteDAO vdao = new ClienteDAO()) {
//            Cliente v = vdao.read(53);
//            v.setEmail("estoesunaPRUEBA@gmail.com");
//            v.setPresupuesto(6000);
//            vdao.update(v);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
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
        this.usuario = usuario;

        Preferences usuarioActivo = Preferences.userRoot();
        usuarioActivo.put("concesionarioID", Integer.toString(usuario.getConcesionarioID()));
        usuarioActivo.put("tipo",usuario.getTipo());
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
