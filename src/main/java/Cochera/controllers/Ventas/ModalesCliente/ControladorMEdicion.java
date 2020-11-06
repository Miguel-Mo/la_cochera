package Cochera.controllers.Ventas.ModalesCliente;

import Cochera.controllers.AutoRoot;
import Cochera.dao.ClienteDAO;
import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Clientes.Cliente;
import Cochera.models.Concesionarios.Concesionario;
import Cochera.models.Vehiculo.TipoVehiculo;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

import java.sql.SQLException;
import java.util.HashMap;

public class ControladorMEdicion implements AutoRoot {

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

    private Parent root;
    private Cliente cliente;

    @FXML
    private void initialize() {
        prohibirEdicion();
    }

    private void prohibirEdicion() {
        Nombre.setDisable(true);
        Apellidos.setDisable(true);
        Telefono.setDisable(true);
        dni.setDisable(true);
        Presupuesto.setDisable(true);
        descripcion.setDisable(true);
        Email.setDisable(true);
    }

    private void permitirEdicion() {
        Nombre.setDisable(false);
        Apellidos.setDisable(false);
        Telefono.setDisable(false);
        dni.setDisable(false);
        Presupuesto.setDisable(false);
        descripcion.setDisable(false);
        Email.setDisable(false);
    }


    @FXML
    public void editar(ActionEvent actionEvent){
        resetError();

        Button boton = (Button) actionEvent.getSource();

        if(boton.getText().equals("Guardar")) {
            if (!checkCampos()) return;

            try(ClienteDAO dao = new ClienteDAO()){

                cliente.setApellidos(Apellidos.getText());
                cliente.setDni(dni.getText());
                cliente.setEmail(Email.getText());
                cliente.setNombre(Nombre.getText());
                cliente.setPresupuesto(Float.parseFloat(Presupuesto.getText()));
                cliente.setTelefono(Telefono.getText());
                cliente.setDescripcionVehiculo(descripcion.getText());

                dao.update(cliente);

                btnCancelar.fire();

            }catch (SQLException throwables){
                throwables.printStackTrace();
            }

        } else if(boton.getText().contentEquals("Editar")){
            permitirEdicion();
            boton.setText("Guardar");
        }
    }

    private boolean checkCampos() {
        boolean resultado = true;

        if (Nombre.getText().trim().length() == 0) {
            resultado = false;
            Nombre.setStyle("-fx-border-color: RED");
        }

        if (Apellidos.getText().trim().length() == 0) {
            resultado = false;
            Apellidos.setStyle("-fx-border-color: RED");
        }

        if (Telefono.getText().trim().length() == 0) {
            resultado = false;
            Telefono.setStyle("-fx-border-color: RED");
        }

        if (dni.getText().trim().length() == 0) {
            resultado = false;
            dni.setStyle("-fx-border-color: RED");
        }

        if (Presupuesto.getText().trim().length() == 0) {
            resultado = false;
            Presupuesto.setStyle("-fx-border-color: RED");
        }

        if (descripcion.getText().length() == 0) {
            resultado = false;
            descripcion.setStyle("-fx-border-color: RED");
        }

        if (Email.getText().length() == 0) {
            resultado = false;
            Email.setStyle("-fx-border-color: RED");
        }

        return resultado;
    }

    private void resetError() {

        Nombre.setStyle("-fx-border-color: transparent");
        Apellidos.setStyle("-fx-border-color: transparent");
        Telefono.setStyle("-fx-border-color: transparent");
        dni.setStyle("-fx-border-color: transparent");
        Presupuesto.setStyle("-fx-border-color: transparent");
        descripcion.setStyle("-fx-border-color: transparent");
        Email.setStyle("-fx-border-color: transparent");

    }

    @FXML
    public void cerrar(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        root.setStyle("-fx-opacity: 1");
        stage.close();
    }

    @Override
    public void setRoot(Parent root) {
        this.root = root;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        establecerCliente();
    }

    private void establecerCliente() {
        Nombre.setText(cliente.getNombre());
        Apellidos.setText(cliente.getApellidos());
        Telefono.setText(cliente.getTelefono());
        dni.setText(cliente.getDni());
        Presupuesto.setText(String.valueOf(cliente.getPresupuesto()));
        descripcion.setText(cliente.getDescripcionVehiculo());
        Email.setText(cliente.getEmail());
    }

}
