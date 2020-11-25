package Cochera.Controllers.Mecanicos;

import Cochera.DAO.ConcesionarioDAO;
import Cochera.DAO.TipoVehiculosDAO;
import Cochera.Models.Concesionarios.Concesionario;
import Cochera.Models.Reparaciones.Reparacion;
import Cochera.Models.Vehiculo.TipoVehiculo;
import Cochera.Models.Vehiculo.VehiculoVender;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.ToggleSwitch;

import java.sql.SQLException;
import java.util.HashMap;

public class ControladorModal  {
    /*

    @FXML private TextArea taDescripcion;
    @FXML private TextArea taComponentes;
    @FXML private Button btnEditar;
    @FXML private ComboBox cbTipoVehiculo;
    // Campos del formulario
    @FXML private TextField tfMarca;
    @FXML private TextField tfModelo;
    @FXML private TextField tfMecanico;
    @FXML private TextField tfPresupuestoEstimado;
    @FXML private TextField tfMatricula;
    @FXML private TextField tfTiempoEstimado;




    @FXML
    private void initialize() {

    }

    @Override
    public void prohibirEdicion() {
        tfMarca.setDisable(true);
        tfModelo.setDisable(true);
        tfMecanico.setDisable(true);
        tfPresupuestoEstimado.setDisable(true);
        tfMatricula.setDisable(true);
        tfTiempoEstimado.setDisable(true);
        cbTipoVehiculo.setDisable(true);
        taComponentes.setDisable(true);
        taDescripcion.setDisable(true);

        tfMarca.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        tfModelo.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        tfMecanico.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        tfPresupuestoEstimado.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        tfMatricula.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        tfTiempoEstimado.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        cbTipoVehiculo.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        taComponentes.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        taDescripcion.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
    }

    @Override
    public void permitirEdicion() {
        tfMarca.setDisable(false);
        tfModelo.setDisable(false);
        tfMecanico.setDisable(false);
        tfPresupuestoEstimado.setDisable(false);
        tfMatricula.setDisable(false);
        tfTiempoEstimado.setDisable(false);
        cbTipoVehiculo.setDisable(false);
        taComponentes.setDisable(false);
        taDescripcion.setDisable(false);
    }

    @Override
    public boolean checkCampos() {
        boolean resultado = true;

        if (tfMarca.getText().trim().length() == 0) {
            resultado = false;
            tfMarca.setStyle("-fx-border-color: RED");
        }

        if (tfModelo.getText().trim().length() == 0) {
            resultado = false;
            tfModelo.setStyle("-fx-border-color: RED");
        }

        if (tfMecanico.getText().trim().length() == 0) {
            resultado = false;
            tfMecanico.setStyle("-fx-border-color: RED");
        }

        if (tfPresupuestoEstimado.getText().length() == 0) {
            resultado = false;
            tfPresupuestoEstimado.setStyle("-fx-border-color: RED");
        }


        if (tfMatricula.getText().trim().length() == 0) {
            resultado = false;
            tfMatricula.setStyle("-fx-border-color: RED");
        }


        if (cbTipoVehiculo.getValue() == null) {
            resultado = false;
            cbTipoVehiculo.setStyle("-fx-border-color: RED");
        }

        if (taComponentes.getText().trim().length() == 0) {
            resultado = false;
            taComponentes.setStyle("-fx-border-color: RED");
        }

        if (taDescripcion.getText().trim().length() == 0) {
            resultado = false;
            taDescripcion.setStyle("-fx-border-color: RED");
        }

        return resultado;
    }

    @Override
    public void resetError() {


        tfMarca.setDisable(false);
        tfModelo.setDisable(false);
        tfMecanico.setDisable(false);
        tfPresupuestoEstimado.setDisable(false);
        tfMatricula.setDisable(false);
        tfTiempoEstimado.setDisable(false);
        cbTipoVehiculo.setDisable(false);
        taComponentes.setDisable(false);
        taDescripcion.setDisable(false);

        tfMarca.setStyle("-fx-border-color: transparent");
        tfModelo.setStyle("-fx-border-color: transparent");
        tfMecanico.setStyle("-fx-border-color: transparent");
        tfPresupuestoEstimado.setStyle("-fx-border-color: transparent");
        tfMatricula.setStyle("-fx-border-color: transparent");
        tfTiempoEstimado.setStyle("-fx-border-color: transparent");
        cbTipoVehiculo.setStyle("-fx-border-color: transparent");
        taComponentes.setStyle("-fx-border-color: transparent");
        taDescripcion.setStyle("-fx-border-color: transparent");

    }

    @Override
    protected Reparacion crearObjeto() {
        HashMap<String, Object> datos = new HashMap<>();

        datos.put("marca", tfMarca.getText());
        datos.put("modelo", tfModelo.getText());
        datos.put("potencia", tfMecanico.getText());
        datos.put("concesionarioID", concesionarioRegistro.getValue().getId());
        datos.put("precio", precio.getText());
        datos.put("tipo", tipoVehiculo.getValue());
        datos.put("tswitch", tswitch.isSelected());
        if (tswitch.isSelected()) datos.put("tiempoUsado", antiguedad.getText());

        return new VehiculoVender(datos);
    }

    @Override
    protected void actualizarObjeto(VehiculoVender vehiculo) {
        vehiculo.setMarca(tfMarca.getText());
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
        tfMarca.setText(vehiculo.getMarca());
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
}*/
}