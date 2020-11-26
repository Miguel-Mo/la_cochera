package Cochera.Controllers.Mecanicos;

import Cochera.Controllers.Base.CModal;
import Cochera.DAO.ClienteDAO;
import Cochera.DAO.ReparacionesDAO;
import Cochera.DAO.usuario.MecanicoDAO;
import Cochera.DAO.vehiculos.TipoVehiculosDAO;
import Cochera.DAO.vehiculos.VehiculoDAO;
import Cochera.DAO.vehiculos.VehiculoRepararDAO;
import Cochera.Models.Clientes.Cliente;
import Cochera.Models.Reparaciones.Reparacion;
import Cochera.Models.Usuario.Mecanico;
import Cochera.Models.Vehiculo.TipoVehiculo;
import Cochera.Models.Vehiculo.VehiculoReparar;
import Cochera.utils.Conversor;
import Cochera.utils.vistas.Boton;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.controlsfx.control.SearchableComboBox;

import javax.swing.event.ChangeListener;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class CModalReparacionNueva extends CModal {


    @FXML
    private Boton btnAccion;
    @FXML
    private Boton btnAtras;
    @FXML
    private Boton btnAdelante;

    @FXML
    private Pane seccion1;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfApellidos;
    @FXML
    private TextField tfTelefono;

    @FXML
    private Pane seccion2;
    @FXML
    private TextField marcaVehiculo;
    @FXML
    private TextField modeloVehiculo;
    @FXML
    private TextField matricula;
    @FXML
    private ComboBox<TipoVehiculo> cbTipoVehiculo;

    @FXML
    private Pane seccion3;
    @FXML
    private SearchableComboBox<Mecanico> cbMecanico;
    @FXML
    private TextField tfPresupuestoEstimado;
    @FXML
    private TextField tfTiempoEstimado;
    @FXML
    private TextArea taProblema;
    @FXML
    private TextArea taComponentes;

    private int numSeccion;
    private Pane[] secciones;
    private FilteredList<Reparacion> listaFiltrable;
    private FilteredList<Mecanico> mecanicos;

    private final int concesionarioID = Integer.parseInt(Preferences.userRoot().get("concesionarioID", null));


    public CModalReparacionNueva() {
        numSeccion = 1;
    }

    @FXML
    protected void initialize() {
        super.initialize();

        secciones = new Pane[]{seccion1, seccion2, seccion3};

        btnAtras.establecerImagen(new ImageView("/icons/left.png"), 50);
        btnAtras.setOnAction(this::cambiarSeccion);
        btnAtras.setVisible(false);

        btnAdelante.establecerImagen(new ImageView("/icons/right.png"), 50);
        btnAdelante.setOnAction(this::cambiarSeccion);

        btnAccion.establecerImagen(new ImageView("/icons/icons8-checked-64.png"), 50);
        btnAccion.setOnAction(this::crear);
        btnAccion.setVisible(false);


        iniciarDesplegables();
        mostrarSeccion();
    }

    private void iniciarDesplegables() {

        try (TipoVehiculosDAO dao = new TipoVehiculosDAO()) {
            cbTipoVehiculo.setItems(dao.read());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (MecanicoDAO dao = new MecanicoDAO()) {
            mecanicos = new FilteredList<>(dao.read());
            cbMecanico.setItems(mecanicos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cbTipoVehiculo.valueProperty().addListener((observableValue, antiguo, actual) -> {
            mecanicos.setPredicate(mecanico ->
                    mecanico.getEspecialidades().contains(actual.getDescripcion())
                            && mecanico.getConcesionarioID() == concesionarioID
            );
        });
    }

    @FXML
    private void cambiarSeccion(ActionEvent e) {
        Button botonPulsado = (Button) e.getSource();

        if (numSeccion == 1 && botonPulsado.equals(btnAdelante)) {
            if (!checkSeccion()) return;
            numSeccion++;
            btnAtras.setVisible(true);
        } else if (numSeccion == 2 && botonPulsado.equals(btnAtras)) {
            numSeccion--;
            btnAtras.setVisible(false);
        } else if (numSeccion == 2 && botonPulsado.equals(btnAdelante)) {
            if (!checkSeccion()) return;
            numSeccion++;
            btnAdelante.setVisible(false);
            btnAccion.setVisible(true);
        } else if (numSeccion == 3 && botonPulsado.equals(btnAtras)) {
            numSeccion--;
            btnAdelante.setVisible(true);
            btnAccion.setVisible(false);
        }

        mostrarSeccion();
    }

    private void mostrarSeccion() {
        for (Pane seccion : secciones) seccion.setVisible(false);
        secciones[numSeccion - 1].setVisible(true);
    }

    private void crear(ActionEvent e) {
        if (!checkSeccion()) return;

        // Guardamos el cliente
        Cliente cliente = new Cliente();

        cliente.setNombre(tfNombre.getText());
        cliente.setApellidos(tfApellidos.getText());
        cliente.setEmail(tfEmail.getText());
        cliente.setTelefono(tfTelefono.getText());

        try (ClienteDAO clienteDAO = new ClienteDAO()) {
            cliente = clienteDAO.read(clienteDAO.create(cliente));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Guardamos el vehículo
        VehiculoReparar vehiculoReparar = new VehiculoReparar();

        vehiculoReparar.setDescripcion(taProblema.getText());
        vehiculoReparar.setMatricula(matricula.getText());
        vehiculoReparar.setMarca(marcaVehiculo.getText());
        vehiculoReparar.setModelo(modeloVehiculo.getText());
        vehiculoReparar.setTipoVehiculo(cbTipoVehiculo.getValue());
        vehiculoReparar.setConcesionarioID(concesionarioID);


        try (VehiculoRepararDAO vehiculoRepararDAO = new VehiculoRepararDAO()) {
            vehiculoReparar = vehiculoRepararDAO.read(vehiculoRepararDAO.create(vehiculoReparar));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Guardamos la reparación en sí
        Reparacion reparacion = new Reparacion();

        reparacion.setClienteID(cliente.getId());
        reparacion.setVehiculoRepararID(vehiculoReparar.getId());
        reparacion.setMecanicoID(cbMecanico.getValue().getId());

        reparacion.setEstado(Reparacion.PENDIENTE);
        reparacion.setTiempoEstimado(tfTiempoEstimado.getText());
        reparacion.setPresupuestoEstimado(Integer.parseInt(tfPresupuestoEstimado.getText()));
        reparacion.setComponentes(taComponentes.getText());

        try (ReparacionesDAO reparacionesDAO = new ReparacionesDAO()) {
            reparacion = reparacionesDAO.read(reparacionesDAO.create(reparacion));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ((ObservableList<Reparacion>) listaFiltrable.getSource()).add(reparacion);

        btnCancelar.fire();
    }

    private boolean checkSeccion() {
        resetError();
        boolean resultado = true;

        switch (numSeccion) {
            case 1:

                if (tfNombre.getText().trim().length() == 0) {
                    resultado = false;
                    tfNombre.setStyle("-fx-border-color: RED");
                }

                if (tfEmail.getText().trim().length() == 0) {
                    resultado = false;
                    tfEmail.setStyle("-fx-border-color: RED");
                }

                if (tfApellidos.getText().trim().length() == 0) {
                    resultado = false;
                    tfApellidos.setStyle("-fx-border-color: RED");
                }

                if (tfTelefono.getText().trim().length() == 0) {
                    resultado = false;
                    tfTelefono.setStyle("-fx-border-color: RED");
                }

                break;

            case 2:

                if (marcaVehiculo.getText().trim().length() == 0) {
                    resultado = false;
                    marcaVehiculo.setStyle("-fx-border-color: RED");
                }

                if (modeloVehiculo.getText().trim().length() == 0) {
                    resultado = false;
                    modeloVehiculo.setStyle("-fx-border-color: RED");
                }

                if (matricula.getText().trim().length() == 0) {
                    resultado = false;
                    matricula.setStyle("-fx-border-color: RED");
                }

                if (cbTipoVehiculo.getValue() == null) {
                    resultado = false;
                    cbTipoVehiculo.setStyle("-fx-border-color: RED");
                }

                break;

            case 3:

                if (cbMecanico.getValue() == null) {
                    resultado = false;
                    cbMecanico.setStyle("-fx-border-color: RED");
                }

                if (tfPresupuestoEstimado.getText().trim().length() == 0 ||
                        !tfPresupuestoEstimado.getText().matches("^[0-9]*$")) {
                    resultado = false;
                    tfPresupuestoEstimado.setStyle("-fx-border-color: RED");
                }

                if (tfTiempoEstimado.getText().trim().length() == 0 ||
                        !tfTiempoEstimado.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
                    resultado = false;
                    tfTiempoEstimado.setStyle("-fx-border-color: RED");
                }

                if (taProblema.getText().trim().length() == 0) {
                    resultado = false;
                    taProblema.setStyle("-fx-border-color: RED");
                }

                if (taComponentes.getText().trim().length() == 0) {
                    resultado = false;
                    taComponentes.setStyle("-fx-border-color: RED");
                }

                break;
        }

        return resultado;
    }

    private void resetError() {

        tfNombre.setStyle("-fx-border-color: transparent");
        tfEmail.setStyle("-fx-border-color: transparent");
        tfApellidos.setStyle("-fx-border-color: transparent");
        tfTelefono.setStyle("-fx-border-color: transparent");
        modeloVehiculo.setStyle("-fx-border-color: transparent");
        matricula.setStyle("-fx-border-color: transparent");
        cbTipoVehiculo.setStyle("-fx-border-color: transparent");
        cbMecanico.setStyle("-fx-border-color: transparent");
        tfPresupuestoEstimado.setStyle("-fx-border-color: transparent");
        tfTiempoEstimado.setStyle("-fx-border-color: transparent");
        taProblema.setStyle("-fx-border-color: transparent");
        taComponentes.setStyle("-fx-border-color: transparent");

    }


    public void setLista(FilteredList<Reparacion> lista) {
        this.listaFiltrable = lista;
    }
}