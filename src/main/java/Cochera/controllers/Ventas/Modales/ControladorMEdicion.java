package Cochera.controllers.Ventas.Modales;

import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Concesionarios.Concesionario;
import Cochera.models.Vehiculo.TipoVehiculo;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.ToggleSwitch;

import java.sql.SQLException;
import java.util.HashMap;

public class ControladorMEdicion {
    @FXML
    private TextField marcaVehiculo;
    @FXML
    private TextField potencia;
    @FXML
    private ComboBox<Concesionario> concesionarioRegistro;
    @FXML
    private TextField precio;

    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<TipoVehiculo> tipoVehiculo;
    @FXML
    private ToggleSwitch tswitch;
    @FXML
    private TextField modeloVehiculo;
    @FXML
    private TextField antiguedad;
    @FXML
    private Button btnEditar;


    @FXML
    private void initialize(){

        marcaVehiculo.setEditable(false);
        potencia.setEditable(false);
        concesionarioRegistro.setEditable(false);
        precio.setEditable(false);
        tipoVehiculo.setEditable(false);
        //tswitch.setEditable(true);
        modeloVehiculo.setEditable(false);
        antiguedad.setEditable(false);


    }


    @FXML
    public void editar(ActionEvent actionEvent){

        btnEditar.setText("Guardar");


        if(btnEditar.getText().contentEquals("Guardar")){
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
        }else if(btnEditar.getText().contentEquals("Editar")){
            marcaVehiculo.setEditable(true);
            potencia.setEditable(true);
            concesionarioRegistro.setEditable(true);
            precio.setEditable(true);
            tipoVehiculo.setEditable(true);
            //tswitch.setEditable(true);
            modeloVehiculo.setEditable(true);
            antiguedad.setEditable(true);


        }
    }
    @FXML
    public void cerrar(ActionEvent actionEvent) {}

}
