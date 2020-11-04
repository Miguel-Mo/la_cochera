package Cochera.controllers.Ventas;

import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class ControladorVehiculos {


    @FXML
    private TableView<VehiculoVender> tabla;
    @FXML
    private TableColumn<VehiculoVender, String> imagen;
    @FXML
    private TableColumn<VehiculoVender, String> modelo;
    @FXML
    private TableColumn<VehiculoVender, String> fechaEntrada;
    @FXML
    private TableColumn<VehiculoVender, String> tipo;
    @FXML
    private TableColumn<VehiculoVender, String> concesionario;
    @FXML
    private TableColumn acciones;

    public ControladorVehiculos() {
    }

    @FXML
    private void initialize() {
        try (VehiculoVenderDAO dao = new VehiculoVenderDAO()) {
            ObservableList<VehiculoVender> vehiculos = dao.read();
            tabla.setItems(vehiculos);

            imagen.setCellValueFactory(dato -> dato.getValue().imagenProperty());
            modelo.setCellValueFactory(dato -> dato.getValue().modeloProperty());
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
