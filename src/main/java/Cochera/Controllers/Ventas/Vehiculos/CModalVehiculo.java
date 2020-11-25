package Cochera.Controllers.Ventas.Vehiculos;

import Cochera.Controllers.Base.CMNuevoEditar;
import Cochera.DAO.CombustibleVehiculoDAO;
import Cochera.DAO.ConcesionarioDAO;
import Cochera.DAO.TipoVehiculosDAO;
import Cochera.Models.Concesionarios.Concesionario;
import Cochera.Models.Vehiculo.CombustibleVehiculo;
import Cochera.Models.Vehiculo.TipoVehiculo;
import Cochera.Models.Vehiculo.VehiculoVender;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.ToggleSwitch;
import java.sql.SQLException;

public class CModalVehiculo extends CMNuevoEditar<VehiculoVender> {

    // Campos del formulario
    @FXML protected TextField marcaVehiculo;
    @FXML protected TextField modeloVehiculo;

    @FXML protected TextField potencia;
    @FXML protected ToggleSwitch tswitch;
    @FXML protected Label lantiguedad;
    @FXML protected TextField antiguedad;

    @FXML protected ComboBox<TipoVehiculo> tipoVehiculo;
    @FXML protected ComboBox<Concesionario> concesionarioRegistro;

    @FXML protected TextField precio;
    @FXML protected ComboBox<CombustibleVehiculo> combustible;
    @FXML protected TextField kmRecorridos;

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

        try (CombustibleVehiculoDAO dao = new CombustibleVehiculoDAO()) {
            combustible.setItems(dao.read());
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
        combustible.setValue(vehiculo.getCombustibleVehiculo());
        modeloVehiculo.setText(vehiculo.getModelo());
        antiguedad.setText(vehiculo.getTiempoUsado());
        kmRecorridos.setText(String.valueOf(vehiculo.getKmRecorridos()));
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
        vehiculo.setCombustibleVehiculo(combustible.getValue());
        vehiculo.setKmRecorridos(Integer.parseInt(kmRecorridos.getText()));
        if (!tswitch.isSelected()) vehiculo.setTiempoUsado(null);
        else vehiculo.setTiempoUsado(antiguedad.getText());
        vehiculo.setSegundaMano(tswitch.isSelected());
    }

    @Override
    public boolean checkCampos() {
        boolean resultado = true;

        if (marcaVehiculo.getText() == null || marcaVehiculo.getText().trim().length() == 0) {
            resultado = false;
            marcaVehiculo.setStyle("-fx-border-color: RED");
        }


        if (modeloVehiculo.getText() == null) {
            resultado = false;
            modeloVehiculo.setStyle("-fx-border-color: RED");
        }

        if (potencia.getText() == null) {
            resultado = false;
            potencia.setStyle("-fx-border-color: RED");
        }

        if (concesionarioRegistro.getValue() == null) {
            resultado = false;
            concesionarioRegistro.setStyle("-fx-border-color: RED");
        }

        if (tswitch.isSelected() && antiguedad.getText() == null) {
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

        if (combustible.getValue() == null) {
            resultado = false;
            combustible.setStyle("-fx-border-color: RED");
        }

        if (kmRecorridos.getText().length() == 0) {
            resultado = false;
            kmRecorridos.setStyle("-fx-border-color: RED");
        }

        return resultado;
    }
}