package Cochera.controllers.Ventas;

import Cochera.controllers.ControladorPanel;
import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

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
}
