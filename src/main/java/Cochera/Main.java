package Cochera;

import Cochera.controllers.ControladorLogin;
import Cochera.controllers.ControladorPanel;
import Cochera.dao.VehiculoDAO;
import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Usuario.Usuario;
import Cochera.models.Vehiculo.Vehiculo;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

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

    public void cerrarSesion() {
        usuario = null;
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
//        try (VehiculoVenderDAO vdao = new VehiculoVenderDAO()) {
//            ObservableList<VehiculoVender> v = vdao.read();
//            v.forEach(System.out::println);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        // ELIMINAR
//        try (VehiculoVenderDAO vdao = new VehiculoVenderDAO()) {
//            VehiculoVender v = vdao.read(30);
//            vdao.delete(v);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }


        // CREACION
//        try {
//            HashMap<String, Object> datos = new HashMap<>();
//
//            datos.put("tipoID",1);
//            datos.put("concesionarioID",1);
//            datos.put("potencia","40-CV");
//            datos.put("marca","Marca");
//            datos.put("modelo","Modelo");
//            datos.put("precio",20000f);
//
//            VehiculoVender vv = new VehiculoVender(datos);
//
//            try (VehiculoVenderDAO vdao = new VehiculoVenderDAO()) {
//                vdao.create(vv);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        // ACTUALIZAR
//        try (VehiculoVenderDAO vdao = new VehiculoVenderDAO()) {
//            VehiculoVender v = vdao.read(31);
//            v.setSegundaMano(true);
//            v.setPotencia("45-CV");
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
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
