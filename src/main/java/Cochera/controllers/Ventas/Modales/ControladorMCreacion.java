package Cochera.controllers.Ventas.Modales;

import Cochera.dao.ConcesionarioDAO;
import Cochera.dao.TipoVehiculosDAO;
import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Concesionarios.Concesionario;
import Cochera.models.Vehiculo.TipoVehiculo;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.ToggleSwitch;

import java.sql.SQLException;
import java.util.HashMap;

public class ControladorMCreacion {

    @FXML
    private TextField marcaVehiculo;
    @FXML
    private TextField potencia;
    @FXML
    private ComboBox <Concesionario>concesionarioRegistro;
    @FXML
    private TextField precio;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<TipoVehiculo> tipoVehiculo;
    @FXML
    private TextField modeloVehiculo;
    @FXML
    private TextField antiguedad;
    @FXML
    private ToggleSwitch tswitch;




    @FXML
    private void initialize(){
        try(TipoVehiculosDAO dao=new TipoVehiculosDAO()){
            tipoVehiculo.setItems(dao.read());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(ConcesionarioDAO daoR=new ConcesionarioDAO()){
            concesionarioRegistro.setItems(daoR.read());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void guardar(ActionEvent actionEvent){
        try(VehiculoVenderDAO dao = new VehiculoVenderDAO()){

            HashMap<String,Object> datos=new HashMap<>();

            datos.put("marca",marcaVehiculo.getText());
            datos.put("modelo",modeloVehiculo.getText());
            datos.put("potencia",potencia.getText());
            datos.put("concesionarioID",concesionarioRegistro.getValue().getId());
            datos.put("tiempoUsado",antiguedad.getText());
            datos.put("precio",precio.getText());
            datos.put("tipoVehiculo",tipoVehiculo.getValue());
            datos.put("tswitch",tswitch.isSelected());

            VehiculoVender vehiculo =new VehiculoVender(datos);

            dao.create(vehiculo);

        }catch (SQLException throwables){

        }
    }


    @FXML
    public void cerrar(ActionEvent actionEvent) {
    }
}
