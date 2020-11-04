package Cochera.controllers.Ventas;

import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.prefs.Preferences;

public class ControladorVehiculos {


    @FXML
    private TableView<VehiculoVender> tabla;
    @FXML
    private TableColumn<VehiculoVender, String> imagen;
    @FXML
    private TableColumn<VehiculoVender, String> modelo;
    @FXML
    private TableColumn<VehiculoVender, String> fechaEntrada;
    @FXML
    private TableColumn<VehiculoVender, String> tipo;
    @FXML
    private TableColumn<VehiculoVender, Integer> concesionario;
    @FXML
    private TableColumn acciones;

    public ControladorVehiculos() {
    }

    @FXML
    private void initialize() {
        Preferences usuarioActivo = Preferences.userRoot();
        String concesionarioID = usuarioActivo.get("concesionarioID",null);

        try (VehiculoVenderDAO dao = new VehiculoVenderDAO()) {
            ObservableList<VehiculoVender> vehiculos = dao.read();

            tabla.setItems(vehiculos);

            imagen.setCellValueFactory(dato -> dato.getValue().imagenProperty());



            modelo.setCellValueFactory(dato -> dato.getValue().modeloProperty());
            fechaEntrada.setCellValueFactory(dato -> dato.getValue().fechaRegistroProperty());
            tipo.setCellValueFactory(dato -> dato.getValue().getTipoVehiculo().descripcionProperty());

            concesionario.setCellValueFactory(dato -> dato.getValue().concesionarioIDProperty().asObject());
            concesionario.setCellFactory(column -> new TableCell<>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) return;

                    // Si el concesionarioID del vehiculo coincide con el del usuario logueado es que son el mismo
                    if (item.equals(Integer.valueOf(concesionarioID))) {
                        setText("Concesionario Actual");
                        setTextFill(Color.valueOf("#3CC13B"));
                    } else { // si no, es que son concesionarios diferentes
                        setText("Otro Concesionario");
                        setTextFill(Color.valueOf("#F3BD32"));
                    }
                }
            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
