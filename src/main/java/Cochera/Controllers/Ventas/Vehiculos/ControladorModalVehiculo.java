package Cochera.Controllers.Ventas.Vehiculos;

import Cochera.Controllers.CMNuevoEditar;
import Cochera.DAO.ConcesionarioDAO;
import Cochera.DAO.TipoVehiculosDAO;
import Cochera.Models.Concesionarios.Concesionario;
import Cochera.Models.Vehiculo.TipoVehiculo;
import Cochera.Models.Vehiculo.VehiculoVender;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.ToggleSwitch;

import java.sql.SQLException;
import java.util.HashMap;

public class ControladorModalVehiculo extends CMNuevoEditar<VehiculoVender> {

    // Campos del formulario
    @FXML private TextField marcaVehiculo;
    @FXML private TextField potencia;
    @FXML private ComboBox<Concesionario> concesionarioRegistro;
    @FXML private TextField precio;
    @FXML private Label lantiguedad;
    @FXML private ComboBox<TipoVehiculo> tipoVehiculo;
    @FXML private ToggleSwitch tswitch;
    @FXML private TextField modeloVehiculo;
    @FXML private TextField antiguedad;

    public ControladorModalVehiculo(VehiculoVender objeto, boolean eliminar) {
        super(objeto, eliminar);
    }

    @FXML
    protected void initialize() {
        try (TipoVehiculosDAO dao = new TipoVehiculosDAO()) {
            tipoVehiculo.setItems(dao.read());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ConcesionarioDAO daoR = new ConcesionarioDAO()) {
            concesionarioRegistro.setItems(daoR.read());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lantiguedad.visibleProperty().bind(tswitch.selectedProperty());
        antiguedad.visibleProperty().bind(tswitch.selectedProperty());
    }

    @Override
    public void prohibirEdicion() {
        marcaVehiculo.setDisable(true);
        potencia.setDisable(true);
        precio.setDisable(true);
        modeloVehiculo.setDisable(true);
        antiguedad.setDisable(true);
        tswitch.setDisable(true);
        tipoVehiculo.setDisable(true);
        concesionarioRegistro.setDisable(true);

        marcaVehiculo.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        modeloVehiculo.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        potencia.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        concesionarioRegistro.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        antiguedad.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        precio.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        tipoVehiculo.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
    }

    @Override
    public void permitirEdicion() {
        marcaVehiculo.setDisable(false);
        potencia.setDisable(false);
        precio.setDisable(false);
        modeloVehiculo.setDisable(false);
        antiguedad.setDisable(false);
        tswitch.setDisable(false);
        tipoVehiculo.setDisable(false);
        concesionarioRegistro.setDisable(false);
    }

    @Override
    public boolean checkCampos() {
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

        if (tswitch.isSelected() && antiguedad.getText().trim().isEmpty()) {
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

    @Override
    public void resetError() {

        marcaVehiculo.setStyle("-fx-border-color: transparent");
        modeloVehiculo.setStyle("-fx-border-color: transparent");
        potencia.setStyle("-fx-border-color: transparent");
        concesionarioRegistro.setStyle("-fx-border-color: transparent");
        antiguedad.setStyle("-fx-border-color: transparent");
        precio.setStyle("-fx-border-color: transparent");
        tipoVehiculo.setStyle("-fx-border-color: transparent");

    }

    @Override
    protected void actualizarObjeto(VehiculoVender vehiculo) {
        vehiculo.setMarca(marcaVehiculo.getText());
        vehiculo.setModelo(modeloVehiculo.getText());
        vehiculo.setPotencia(potencia.getText());
        vehiculo.setConcesionarioID(concesionarioRegistro.getValue().getId());
        vehiculo.setPrecio(Float.parseFloat(precio.getText()));
        vehiculo.setTipoVehiculo(tipoVehiculo.getValue());
        if (!tswitch.isSelected()) vehiculo.setTiempoUsado(null);
        else vehiculo.setTiempoUsado(antiguedad.getText());
        vehiculo.setSegundaMano(tswitch.isSelected());
    }

    @Override
    protected void establecerObjeto(VehiculoVender vehiculo) {
        marcaVehiculo.setText(vehiculo.getMarca());
        potencia.setText(vehiculo.getPotencia());
        concesionarioRegistro.getItems().forEach(concesionario -> {
            if (concesionario.getId() == vehiculo.getConcesionarioID())
                concesionarioRegistro.setValue(concesionario);
        });
        precio.setText(String.valueOf(vehiculo.getPrecio()));
        tipoVehiculo.setValue(vehiculo.getTipoVehiculo());
        modeloVehiculo.setText(vehiculo.getModelo());
        antiguedad.setText(vehiculo.getTiempoUsado());
        tswitch.setSelected(vehiculo.isSegundaMano());
    }
}