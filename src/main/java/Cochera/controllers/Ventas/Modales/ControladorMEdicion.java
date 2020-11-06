package Cochera.controllers.Ventas.Modales;

import Cochera.controllers.AutoRoot;
import Cochera.dao.ConcesionarioDAO;
import Cochera.dao.TipoVehiculosDAO;
import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Concesionarios.Concesionario;
import Cochera.models.Vehiculo.TipoVehiculo;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

import java.sql.SQLException;
import java.util.HashMap;

public class ControladorMEdicion implements AutoRoot {
    @FXML
    private TextField marcaVehiculo;
    @FXML
    private TextField potencia;
    @FXML
    private ComboBox<Concesionario> concesionarioRegistro;
    @FXML
    private TextField precio;
    @FXML
    private Label lantiguedad;
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

    private Parent root;
    private VehiculoVender vehiculo;


    @FXML
    private void initialize(){
        prohibirEdicion();

        try(TipoVehiculosDAO dao=new TipoVehiculosDAO()) {
            tipoVehiculo.setItems(dao.read());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(ConcesionarioDAO daoR=new ConcesionarioDAO()) {
            concesionarioRegistro.setItems(daoR.read());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lantiguedad.visibleProperty().bind(tswitch.selectedProperty());
        antiguedad.visibleProperty().bind(tswitch.selectedProperty());
    }

    private void prohibirEdicion() {
        marcaVehiculo.setEditable(false);
        potencia.setEditable(false);
        precio.setEditable(false);
        modeloVehiculo.setEditable(false);
        antiguedad.setEditable(false);

        tipoVehiculo.setEditable(false);
        tipoVehiculo.setEditable(false);
    }

    private void permitirEdicion() {
        marcaVehiculo.setEditable(true);
        potencia.setEditable(true);
        precio.setEditable(true);
        modeloVehiculo.setEditable(true);
        antiguedad.setEditable(true);
    }


    @FXML
    public void editar(ActionEvent actionEvent){
        resetError();

        Button boton = (Button) actionEvent.getSource();

        if(boton.getText().equals("Guardar")) {
            if (!checkCampos()) return;

            try(VehiculoVenderDAO dao = new VehiculoVenderDAO()){

                // Actualizamos el vehiculo con lo que nos da el usuario
                vehiculo.setMarca(marcaVehiculo.getText());
                vehiculo.setModelo(modeloVehiculo.getText());
                vehiculo.setPotencia(potencia.getText());
                vehiculo.setConcesionarioID(concesionarioRegistro.getValue().getId());
                vehiculo.setPrecio(Float.parseFloat(precio.getText()));
                vehiculo.setTipoVehiculo(tipoVehiculo.getValue());
                if (!tswitch.isSelected()) vehiculo.setTiempoUsado(null);
                vehiculo.setSegundaMano(tswitch.isSelected());

                dao.update(vehiculo);

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

        if (marcaVehiculo.getText().trim().length() == 0) {
            resultado = false;
            marcaVehiculo.setStyle("-fx-border-color: RED");
        }

        if (modeloVehiculo.getText().trim().length() == 0) {
            resultado = false;
            modeloVehiculo.setStyle("-fx-border-color: RED");
        }

        if (potencia.getText().trim().length() == 0) {
            resultado = false;
            potencia.setStyle("-fx-border-color: RED");
        }

        if (concesionarioRegistro.getValue() == null) {
            resultado = false;
            concesionarioRegistro.setStyle("-fx-border-color: RED");
        }

        if (tswitch.isSelected() && antiguedad.getText().trim().length() == 0) {
            resultado = false;
            antiguedad.setStyle("-fx-border-color: RED");
        }

        if (precio.getText().length() == 0) {
            resultado = false;
            precio.setStyle("-fx-border-color: RED");
        }

        if (tipoVehiculo.getValue() == null) {
            resultado = false;
            tipoVehiculo.setStyle("-fx-border-color: RED");
        }

        return resultado;
    }

    private void resetError() {

        marcaVehiculo.setStyle("-fx-border-color: transparent");
        modeloVehiculo.setStyle("-fx-border-color: transparent");
        potencia.setStyle("-fx-border-color: transparent");
        concesionarioRegistro.setStyle("-fx-border-color: transparent");
        antiguedad.setStyle("-fx-border-color: transparent");
        precio.setStyle("-fx-border-color: transparent");
        tipoVehiculo.setStyle("-fx-border-color: transparent");

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

    public void setVehiculo(VehiculoVender vehiculo) {
        this.vehiculo = vehiculo;
        establecerVehiculo();
    }

    private void establecerVehiculo() {
        marcaVehiculo.setText(vehiculo.getMarca());
        potencia.setText(vehiculo.getPotencia());
        concesionarioRegistro.getItems().forEach(concesionario ->  {
            if (concesionario.getId() == vehiculo.getConcesionarioID())
                concesionarioRegistro.setValue(concesionario);
        });
        precio.setText(String.valueOf(vehiculo.getPrecio()));
        tipoVehiculo.setValue(vehiculo.getTipoVehiculo());
        modeloVehiculo.setText(vehiculo.getModelo());
        antiguedad.setText(vehiculo.getTiempoUsado());
    }
}
