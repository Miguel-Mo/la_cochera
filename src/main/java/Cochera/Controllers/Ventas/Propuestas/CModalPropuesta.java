package Cochera.Controllers.Ventas.Propuestas;

import Cochera.Controllers.Base.CMNuevoEditar;
import Cochera.DAO.ClienteDAO;
import Cochera.DAO.ConcesionarioDAO;
import Cochera.DAO.TipoVehiculosDAO;
import Cochera.DAO.VehiculoVenderDAO;
import Cochera.Models.Clientes.Cliente;
import Cochera.Models.Propuestas.Propuesta;
import Cochera.Models.Vehiculo.Vehiculo;
import Cochera.Models.Vehiculo.VehiculoVender;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.SearchableComboBox;

import java.sql.SQLException;
import java.util.prefs.Preferences;

public class CModalPropuesta extends CMNuevoEditar<Propuesta> {

    // Campos del formulario nuevo
    @FXML private SearchableComboBox<Cliente> cbCliente;
    @FXML private SearchableComboBox<VehiculoVender> cbVehiculo;
    @FXML private TextField tfPresupuesto;
    @FXML private DatePicker dpFechaValidez;

    // Campos del formulario editar

    private int concesionarioVendedor;

    public CModalPropuesta(Propuesta propuesta) {
        super(propuesta);

        concesionarioVendedor = Integer.parseInt(Preferences.userRoot().get("concesionarioID",null));
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
        boolean resultado = false;



        return resultado;
    }

    @Override
    protected void preEstablecerObjeto() {
        try (VehiculoVenderDAO dao = new VehiculoVenderDAO()) {
            cbVehiculo.setItems(dao.read().filtered(
                    vehiculo -> !vehiculo.isVendido() && vehiculo.getConcesionarioID() == concesionarioVendedor
            ));
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



    }

    @Override
    public void actualizarObjeto(Propuesta propuesta) {



    }
}
