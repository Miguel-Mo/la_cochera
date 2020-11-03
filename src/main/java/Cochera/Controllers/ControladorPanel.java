package Cochera.controllers;

import Cochera.utils.Vistas.VentanaCustom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ControladorPanel extends VentanaCustom {

    @FXML
    private Pane root;

    //Constructor. Es lo primero que se realiza. Antes que initialize()
    public ControladorPanel() { }

    @FXML
    //Inicializa después de que se haya cargado el fxml (la vista) a la que está conectado
    private void initialize() {
        super.moverVentana();
        iniciarVehiculos();
    }

    private void iniciarVehiculos() {
        try {
            FXMLLoader tablaVehiculos = new FXMLLoader(getClass().getResource("/Ventas/tablaVehiculos.fxml"));
            root.getChildren().add(tablaVehiculos.load());

            tablaVehiculos.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cerrar(ActionEvent event) {
        app.cerrarSesion(); // TODO: Por el momento así. Luego cambiamos a que cierre el programa
    }
}
