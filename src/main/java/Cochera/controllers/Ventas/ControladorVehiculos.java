package Cochera.controllers.Ventas;

import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.swing.text.html.ImageView;
import java.sql.SQLException;
import java.util.function.Predicate;
import java.util.prefs.Preferences;

public class ControladorVehiculos {


    @FXML
    private TableView<VehiculoVender> tabla;
    @FXML
    private TableColumn<VehiculoVender, Image> imagen;
    @FXML
    private TableColumn<VehiculoVender, String> modelo;
    @FXML
    private TableColumn<VehiculoVender, String> fechaEntrada;
    @FXML
    private TableColumn<VehiculoVender, String> tipo;
    @FXML
    private TableColumn<VehiculoVender, Integer> concesionario;
    @FXML
    private TableColumn<VehiculoVender, VehiculoVender> acciones;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfFecha;
    @FXML
    private TextField tfEstado;

    FilteredList<VehiculoVender> listaFiltrable;

    public ControladorVehiculos() {
    }

    @FXML
    private void initialize() {
        Preferences usuarioActivo = Preferences.userRoot();
        String concesionarioID = usuarioActivo.get("concesionarioID",null);

        try (VehiculoVenderDAO dao = new VehiculoVenderDAO()) {
            // Envolvemos los datos de la base de datos en una lista que nos permita filtrar
            listaFiltrable = new FilteredList<>(dao.read(), mostrarTodoAlInicio -> true);

            // Volvemos a envolver para darle la capacidad de ordenarse
            SortedList<VehiculoVender> listaVehiculos = new SortedList<>(listaFiltrable);
            listaVehiculos.comparatorProperty().bind(tabla.comparatorProperty());


            tabla.setItems(listaVehiculos);

            imagen.setCellValueFactory(dato -> dato.getValue().getImageView().imageProperty());


            modelo.setCellValueFactory(dato -> dato.getValue().modeloProperty());
            fechaEntrada.setCellValueFactory(dato -> dato.getValue().fechaRegistroProperty());
            tipo.setCellValueFactory(dato -> dato.getValue().getTipoVehiculo().descripcionProperty());

            concesionario.setCellValueFactory(dato -> dato.getValue().concesionarioIDProperty().asObject());
            concesionario.setCellFactory(column -> new TableCell<>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) { // En caso de que nos filtren tenemos que setear a null para no mostrar
                        setText(null);
                        return;
                    }

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


            acciones.setCellValueFactory(dato -> new ReadOnlyObjectWrapper(dato.getValue()));
            acciones.setCellFactory(dato -> new TableCell<>() {
                private final Button lupa = new Button("Lupa");

                @Override
                protected void updateItem(VehiculoVender vehiculo, boolean empty) {
                    super.updateItem(vehiculo, empty);

                    if (empty) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(lupa);
                    lupa.setOnAction(event -> mostrarModal(vehiculo));
                }
            });



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void filtrar(ActionEvent actionEvent) {
        String modelo = tfModelo.getText().trim();


        if (modelo.length() > 0)
            listaFiltrable.setPredicate(vehiculo -> vehiculo.getModelo().toLowerCase().contains(modelo.toLowerCase()));
    }

    public void limpiar(ActionEvent actionEvent) {
        tfModelo.setText("");
        tabla.getSortOrder().clear();
        listaFiltrable.setPredicate(mostrar -> true);
    }

    private void mostrarModal(VehiculoVender vehiculo) {
        System.out.println(vehiculo);
    }
}
