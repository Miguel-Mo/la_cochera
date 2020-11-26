package Cochera.Controllers.Mecanicos;

import Cochera.Controllers.Base.CModal;
import Cochera.Models.Reparaciones.Reparacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CModalReparacionLupa extends CModal {

    private final Reparacion reparacion;

    @FXML private TextField tfMarca;
    @FXML private TextField tfPresupuestoEstimado;
    @FXML private TextField tfModelo;
    @FXML private TextField tfMatricula;
    @FXML private TextField tfTiempoEstimado;
    @FXML private TextField tfTiempoReal;
    @FXML private Label lbTiempoReal;
    @FXML private TextArea taDescripcion;
    @FXML private TextArea taComponentes;
    @FXML private TextField tfTipoVehiculo;

    private final boolean mostrarTiempoReal;

    public CModalReparacionLupa(Reparacion reparacion) {
        this.reparacion = reparacion;
        String estado = reparacion.getEstado();
        mostrarTiempoReal = estado.equals(Reparacion.FINALIZADO) || estado.equals(Reparacion.AVISADO);
    }

    @FXML
    protected void initialize() {
        super.initialize();

        if (!mostrarTiempoReal) {
            lbTiempoReal.setVisible(false);
            tfTiempoReal.setVisible(false);
        }

        establecerCampos();
    }

    private void establecerCampos() {

        tfMarca.setText(reparacion.getVehiculoReparar().getMarca());
        tfModelo.setText(reparacion.getVehiculoReparar().getModelo());

        tfTipoVehiculo.setText(reparacion.getVehiculoReparar().getTipoVehiculo().getDescripcion());
        tfMatricula.setText(reparacion.getVehiculoReparar().getMatricula());

        tfPresupuestoEstimado.setText(reparacion.getVehiculoReparar().getMarca());
        tfTiempoEstimado.setText(reparacion.getVehiculoReparar().getMarca());

        if (reparacion.getEstado().equals(Reparacion.FINALIZADO))
            tfTiempoReal.setText(reparacion.getTiempoReal());

        taDescripcion.setText(reparacion.getVehiculoReparar().getDescripcion());
        taComponentes.setText(reparacion.getComponentes());
    }

}
