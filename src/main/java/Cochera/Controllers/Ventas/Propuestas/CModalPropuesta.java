package Cochera.Controllers.Ventas.Propuestas;

import Cochera.Controllers.Base.CMNuevoEditar;
import Cochera.DAO.ClienteDAO;
import Cochera.DAO.vehiculos.VehiculoVenderDAO;
import Cochera.Models.Clientes.Cliente;
import Cochera.Models.Propuestas.Propuesta;
import Cochera.Models.Vehiculo.VehiculoVender;
import Cochera.utils.Conversor;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.SearchableComboBox;

import java.sql.SQLException;
import java.util.prefs.Preferences;

public class CModalPropuesta extends CMNuevoEditar<Propuesta> {

    // Campos del formulario
    @FXML private SearchableComboBox<Cliente> cbCliente;
    @FXML private SearchableComboBox<VehiculoVender> cbVehiculo;
    @FXML private TextField tfPresupuesto;
    @FXML private DatePicker dpFechaValidez;

    private final int concesionarioVendedor = Integer.parseInt(Preferences.userRoot().get("concesionarioID",null));

    public CModalPropuesta(Propuesta propuesta) {
        super(propuesta);
    }

    public CModalPropuesta() {
        super(new Propuesta());
    }

    @FXML
    protected void initialize() {
        super.initialize();
    }

    @Override
    public boolean checkCampos() {
        boolean resultado = true;

        if (cbCliente.getValue() == null) {
            resultado = false;
            cbCliente.setStyle("-fx-border-color: RED");
        }

        if (cbVehiculo.getValue() == null) {
            resultado = false;
            cbVehiculo.setStyle("-fx-border-color: RED");
        }

        if (tfPresupuesto.getText().trim().length() == 0) {
            resultado = false;
            tfPresupuesto.setStyle("-fx-border-color: RED");
        }

        if (dpFechaValidez.getValue() == null) {
            resultado = false;
            dpFechaValidez.setStyle("-fx-border-color: RED");
        }

        return resultado;
    }

    @Override
    protected void preEstablecerObjeto() {
        try (VehiculoVenderDAO dao = new VehiculoVenderDAO()) {
            cbVehiculo.setItems(dao.read());
            /*cbVehiculo.setItems(dao.read().filtered(
                    vehiculo -> !vehiculo.isVendido() && vehiculo.getConcesionarioID() == concesionarioVendedor
            ));*/
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ClienteDAO daoC = new ClienteDAO()) {
            cbCliente.setItems(daoC.read());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void establecerObjeto(Propuesta propuesta) {

        cbVehiculo.setValue(propuesta.getVehiculoVender());
        cbCliente.setValue(propuesta.getCliente());
        tfPresupuesto.setText(String.valueOf(propuesta.getPresupuesto()));
        //dpFechaValidez.setConverter(Conversor(propuesta.getFechaLimite()));


    }

    @Override
    public void actualizarObjeto(Propuesta propuesta) {

        propuesta.setClienteID(cbCliente.getValue().getId());
        propuesta.setVehiculoVenderID(cbVehiculo.getValue().getId());
        propuesta.setPresupuesto(Integer.parseInt(tfPresupuesto.getText()));
        propuesta.setFechaLimite(Conversor.datePickerToDate(dpFechaValidez));

        if (propuesta.getId() == 0) { // Estamos creando una nueva propuesta
            propuesta.setEstado(Propuesta.PENDIENTE);
            propuesta.setVendedorID(Integer.parseInt(Preferences.userRoot().get("id",null)));
        }

    }
}
