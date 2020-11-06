package Cochera.controllers.Ventas.ModalesCliente;

import Cochera.dao.ClienteDAO;
import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Clientes.Cliente;
import Cochera.models.Concesionarios.Concesionario;
import Cochera.models.Vehiculo.TipoVehiculo;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.ToggleSwitch;

import java.sql.SQLException;
import java.util.HashMap;

public class ControladorMEdicion {

    @FXML
    private TextField Nombre;
    @FXML
    private TextField Apellidos;
    @FXML
    private TextField Telefono;
    @FXML
    private TextField dni;
    @FXML
    private DatePicker FechaReg;
    @FXML
    private TextField Presupuesto;
    @FXML
    private TextArea descripcion;
    @FXML
    private TextField Email;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;


    @FXML
    private void initialize(){

        Nombre.setEditable(false);
        Apellidos.setEditable(false);
        Telefono.setEditable(false);
        dni.setEditable(false);
        FechaReg.setEditable(false);
        Presupuesto.setEditable(false);
        descripcion.setEditable(false);
        Email.setEditable(false);


    }


    @FXML
    public void editar(ActionEvent actionEvent){

        btnAceptar.setText("Guardar");


        if(btnAceptar.getText().contentEquals("Guardar")){
            try(ClienteDAO dao = new ClienteDAO()){

                HashMap<String,String> datos=new HashMap<>();

                datos.put("nombre",Nombre.getText());
                datos.put("apellidos",Apellidos.getText());
                datos.put("telefono",Telefono.getText());
                datos.put("dni",dni.getText());
                datos.put("presupuesto",Presupuesto.getText());
                datos.put("descripcion",descripcion.getText());
                datos.put("email",Email.getText());

                Cliente cliente =new Cliente(datos);

                dao.create(cliente);

            }catch (SQLException throwables){

            }
        }else if(btnAceptar.getText().contentEquals("Editar")){
            Nombre.setEditable(true);
            Apellidos.setEditable(true);
            Telefono.setEditable(true);
            dni.setEditable(true);
            Presupuesto.setEditable(true);
            descripcion.setEditable(true);
            Email.setEditable(true);

        }
    }
    @FXML
    public void cerrar(ActionEvent actionEvent) {}
}
