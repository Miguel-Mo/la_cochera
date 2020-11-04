package Cochera.controllers;

import Cochera.controllers.Ventas.ControladorVentas;
import Cochera.models.Usuario.Usuario;
import Cochera.utils.Vistas.VentanaCustom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarMecanico() {

    }

    @Override
    public void cerrar(ActionEvent event) {
        app.cerrarSesion(); // TODO: Por el momento así. Luego cambiamos a que cierre el programa
    }
}
