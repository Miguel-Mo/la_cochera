package Cochera.Controllers.Ventas.Vehiculos;

import Cochera.Controllers.Base.CMNuevoEditar;
import Cochera.DAO.ConcesionarioDAO;
import Cochera.DAO.TipoVehiculosDAO;
import Cochera.Models.Concesionarios.Concesionario;
import Cochera.Models.Vehiculo.TipoVehiculo;
import Cochera.Models.Vehiculo.VehiculoVender;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.ToggleSwitch;
import java.sql.SQLException;

public class CModalVehiculo extends CMNuevoEditar<VehiculoVender> {

    // Campos del formulario
    @FXML protected TextField marcaVehiculo;
    @FXML protected TextField potencia;
    @FXML protected ComboBox<Concesionario> concesionarioRegistro;
    @FXML protected TextField precio;
    @FXML protected Label lantiguedad;
    @FXML protected ComboBox<TipoVehiculo> tipoVehiculo;
    @FXML protected ToggleSwitch tswitch;
    @FXML protected TextField modeloVehiculo;
    @FXML protected TextField antiguedad;

    public CModalVehiculo(VehiculoVender vehiculoVender) {
        super(vehiculoVender);
    }

    public CModalVehiculo() {
        super(new VehiculoVender());
    }

    @FXML
    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    protected void preEstablecerObjeto() {
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
}