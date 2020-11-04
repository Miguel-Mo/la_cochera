package Cochera.controllers.Ventas.Modales;

import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.HashMap;

public class ControladorMCreacion {
    @FXML
    private TextField modeloVehiculo;
    @FXML
    private TextField potencia;
    @FXML
    private TextField antiguedad;
    @FXML
    private DatePicker fechaEntrada;
    @FXML
    private ComboBox concesionarioRegistro;
    @FXML
    private TextField precio;
    @FXML
    private TextArea descripcion;
    @FXML
    private Button btnAceptar;



    @FXML
    public void guardar(ActionEvent actionEvent){
        try(VehiculoVenderDAO dao = new VehiculoVenderDAO()){

            HashMap<String,Object> datos=new HashMap<>();

            datos.put("modelo",modeloVehiculo.getText());
            datos.put("potencia",modeloVehiculo.getText());
            datos.put("modelo",modeloVehiculo.getText());
            datos.put("modelo",modeloVehiculo.getText());


            VehiculoVender vehiculo=new VehiculoVender();

            dao.create(vehiculo);





        }catch (SQLException throwables){

        }
    }


    @FXML
    public void cerrar(ActionEvent actionEvent) {
    }
}
