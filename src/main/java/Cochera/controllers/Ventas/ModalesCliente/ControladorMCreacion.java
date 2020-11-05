package Cochera.controllers.Ventas.ModalesCliente;

import Cochera.controllers.AutoRoot;
import Cochera.dao.ClienteDAO;

import Cochera.models.Clientes.Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.HashMap;

public class ControladorMCreacion implements AutoRoot {


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


    @FXML
    private void initialize() {


    }



    @FXML
    public void guardar(ActionEvent actionEvent) {
        resetError();

        if (!checkCampos()) return;

        try(ClienteDAO dao = new ClienteDAO()){

            HashMap<String,Object> datos=new HashMap<>();

            datos.put("nombre",Nombre.getText());
            datos.put("apellidos",Apellidos.getText());
            datos.put("telefono",Telefono.getText());
            datos.put("dni",dni.getText());
            datos.put("FechaReg",FechaReg.getValue());
            datos.put("presupuesto",Presupuesto.getText());
            datos.put("descripcion",descripcion.getText());
            datos.put("email",Email.getText());

            Cliente cliente = new Cliente(datos);

            dao.create(cliente);

            btnCancelar.fire();

        } catch (Exception e){
            e.printStackTrace();
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

        if (FechaReg.getValue() == null) {
            resultado = false;
            FechaReg.setStyle("-fx-border-color: RED");
        }

        if (Presupuesto.getText().trim().length() == 0) {
            resultado = false;
            Presupuesto.setStyle("-fx-border-color: RED");
        }

        if (descripcion.getText().trim().length() == 0) {
            resultado = false;
            descripcion.setStyle("-fx-border-color: RED");
        }

        if (Email.getText().trim().length() == 0) {
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
        FechaReg.setStyle("-fx-border-color: transparent");
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
}
