package Cochera.controllers.Ventas;

import Cochera.controllers.ControladorPanel;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class ControladorVentas {

    @FXML
    private Pane contenido;
    @FXML
    private Button vehiculos;
    @FXML
    private Button clientes;
    @FXML
    private Button propuestas;
    
    private ControladorPanel panel;

    @FXML
    private void initialize() {
        try {
            FXMLLoader vehiculosFX = new FXMLLoader(getClass().getResource("/Ventas/tablaVehiculos.fxml"));
            contenido.getChildren().add(vehiculosFX.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cambiarVentana(ActionEvent e) throws IOException {
        String ruta;
        switch (((Node)e.getSource()).getId()) {
            case "vehiculos" :
                ruta = "/Ventas/tablaVehiculos.fxml";
                break;
            case "clientes":
                ruta = "/Ventas/tablaClientes.fxml";
                break;
            case "propuestas":
                ruta = "/Ventas/tablaHistorialVentas.fxml";
                break;
            default:
                throw new IllegalStateException("No existe: " + ((Node) e.getSource()).getId());
        }

        FXMLLoader tablaFX = new FXMLLoader(getClass().getResource(ruta));
        contenido.getChildren().add(tablaFX.load());
    }
}
