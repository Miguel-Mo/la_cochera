package Cochera.Controllers;

import Cochera.Controllers.Mecanicos.ControladorReparacion;
import Cochera.Controllers.Ventas.ControladorVentas;
import Cochera.Main;
import Cochera.Models.Usuario.Mecanico;
import Cochera.Models.Usuario.Usuario;
import Cochera.utils.vistas.VentanaCustom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;
import java.util.prefs.Preferences;

public class ControladorPanel extends VentanaCustom {

    @FXML
    private Pane root;

    //Constructor. Es lo primero que se realiza. Antes que initialize()
    public ControladorPanel() { }

    @FXML
    //Inicializa después de que se haya cargado el fxml (la vista) a la que está conectado
    private void initialize() {
        super.moverVentana();

        Preferences usuarioActivo = Preferences.userRoot();

        switch (usuarioActivo.get("tipo",null)) {
            case Usuario.VENDEDOR:
                iniciarVentas();
                break;
            case Usuario.MECANICO:
                iniciarMecanico();
                break;
        }
    }

    private void iniciarVentas() {
        try {
            FXMLLoader ventas = new FXMLLoader(getClass().getResource("/Ventas/ventas.fxml"));
            root.getChildren().add(ventas.load());
            ((ControladorVentas) ventas.getController()).setRoot(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarMecanico() {
        try {
            String ruta = ((Mecanico)Main.usuario).isEsJefe() ? "/Mecanicos/TablaMecanicos.fxml" : "/Mecanicos/TablaJornadas.fxml";
            FXMLLoader mecanicos  = new FXMLLoader(getClass().getResource(ruta));
            root.getChildren().add(mecanicos.load());
            ((ControladorReparacion) mecanicos.getController()).setRoot(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void cerrar(ActionEvent event) {
        app.cerrarSesion(); // TODO: Por el momento así. Luego cambiamos a que cierre el programa
    }
}
