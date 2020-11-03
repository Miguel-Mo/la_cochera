package Cochera.controllers;

import Cochera.Main;
import Cochera.models.Usuario.Usuario;
import Cochera.utils.Vistas.VentanaCustom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ControladorPanel extends VentanaCustom {

    @FXML
    private Pane navegacion;
    @FXML
    private Pane tablas;

    //Constructor. Es lo primero que se realiza. Antes que initialize()
    public ControladorPanel() { }

    @FXML
    //Inicializa después de que se haya cargado el fxml (la vista) a la que está conectado
    private void initialize() {
        super.moverVentana();
    }

    private void iniciarVentas() {
        try {
            FXMLLoader tablaVehiculos = new FXMLLoader(getClass().getResource("/Ventas/tablaVehiculos.fxml"));
            FXMLLoader navegacionFX = new FXMLLoader(getClass().getResource("/Ventas/Navegacion.fxml"));

            navegacion.getChildren().add(navegacionFX.load());
            tablas.getChildren().add(tablaVehiculos.load());



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarMecanico() {

    }

    @Override
    public void setMain(Main app) {
        super.setMain(app);

        switch (app.getUsuario().getTipo()) {
            case Usuario.VENDEDOR:
                iniciarVentas();
                break;
            case Usuario.MECANICO:
                iniciarMecanico();
                break;
        }
    }

    @Override
    public void cerrar(ActionEvent event) {
        app.cerrarSesion(); // TODO: Por el momento así. Luego cambiamos a que cierre el programa
    }
}
