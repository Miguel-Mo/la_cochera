package Cochera.Controllers.Ventas.Vehiculos;

import Cochera.Controllers.Base.CMNuevoEditar;
import Cochera.DAO.ConcesionarioDAO;
import Cochera.DAO.TipoVehiculosDAO;
import Cochera.Models.Concesionarios.Concesionario;
import Cochera.Models.Vehiculo.TipoVehiculo;
import Cochera.Models.Vehiculo.VehiculoVender;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.controlsfx.control.ToggleSwitch;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CModalVehiculo extends CMNuevoEditar<VehiculoVender> {

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

    public CModalVehiculo(VehiculoVender objeto, boolean eliminar) {
        super(objeto, eliminar);
    }

    public CModalVehiculo() {
        super(new VehiculoVender());
    }

    @FXML
    @Override
    public void initialize() {

        if (!eliminar) {
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

        super.initialize();
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