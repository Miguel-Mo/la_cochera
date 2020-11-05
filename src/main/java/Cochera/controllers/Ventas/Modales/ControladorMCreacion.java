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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

import java.sql.SQLException;
import java.util.HashMap;

public class ControladorMCreacion implements AutoRoot {

    @FXML
    private Label lantiguedad;

    @FXML
    private TextField marcaVehiculo;
    @FXML
    private TextField potencia;
    @FXML
    private ComboBox <Concesionario>concesionarioRegistro;
    @FXML
    private TextField precio;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<TipoVehiculo> tipoVehiculo;
    @FXML
    private TextField modeloVehiculo;
    @FXML
    private TextField antiguedad;
    @FXML
    private ToggleSwitch tswitch;

    private Parent root;


    @FXML
    private void initialize() {

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



    @FXML
    public void guardar(ActionEvent actionEvent) {
        resetError();

        if (!checkCampos()) return;

        try(VehiculoVenderDAO dao = new VehiculoVenderDAO()){

            HashMap<String,Object> datos=new HashMap<>();

            datos.put("marca",marcaVehiculo.getText());
            datos.put("modelo",modeloVehiculo.getText());
            datos.put("potencia",potencia.getText());
            datos.put("concesionarioID",concesionarioRegistro.getValue().getId());
            datos.put("precio",precio.getText());
            datos.put("tipoVehiculo",tipoVehiculo.getValue());
            datos.put("tswitch",tswitch.isSelected());
            if (tswitch.isSelected()) datos.put("tiempoUsado",antiguedad.getText());

            VehiculoVender vehiculo = new VehiculoVender(datos);

            dao.create(vehiculo);

            btnCancelar.fire();

        } catch (Exception e){
            e.printStackTrace();
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
}
