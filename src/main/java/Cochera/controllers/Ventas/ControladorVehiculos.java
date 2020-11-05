package Cochera.controllers.Ventas;

import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Vehiculo.TipoVehiculo;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;
import java.util.prefs.Preferences;

public class ControladorVehiculos {


    // Estado de la tabla
    @FXML
    private TableView<VehiculoVender> tabla;
    @FXML
    private TableColumn<VehiculoVender, Image> imagen;
    @FXML
    private TableColumn<VehiculoVender, String> marca;
    @FXML
    private TableColumn<VehiculoVender, String> modelo;
    @FXML
    private TableColumn<VehiculoVender, Date> fechaEntrada;
    @FXML
    private TableColumn<VehiculoVender, String> tipo;
    @FXML
    private TableColumn<VehiculoVender, Integer> concesionario;
    @FXML
    private TableColumn<VehiculoVender, VehiculoVender> acciones;

    // Estado para el filtro
    @FXML
    private DatePicker fDesde;
    @FXML
    private DatePicker fHasta;
    @FXML
    private TextField fModelo;
    @FXML
    private ComboBox<TipoVehiculo> fTipo;
    @FXML
    private TextField fMarca;
    @FXML
    private ComboBox<String> fEstado;

    // Lista de vehiculos que permite hacer búsquedas para filtrar
    private FilteredList<VehiculoVender> listaFiltrable;

    public ControladorVehiculos() {
    }

    @FXML
    private void initialize() {
        inicarFiltros();
        inicarTabla();
    }

    private void inicarFiltros() {

    }

    private void inicarTabla() {
        Preferences usuarioActivo = Preferences.userRoot();
        String concesionarioID = usuarioActivo.get("concesionarioID",null);

        try (VehiculoVenderDAO dao = new VehiculoVenderDAO()) {
            // Envolvemos los datos de la base de datos en una lista que nos permita filtrar
            // Lo mantenemos en el estado porque es este tipo de lista la que nos permitirá filtrar por campo en el método correcpondiente
            listaFiltrable = new FilteredList<>(dao.read(), mostrarTodoAlInicio -> true);

            // Volvemos a envolver para darle la capacidad de ordenarse
            SortedList<VehiculoVender> listaVehiculos = new SortedList<>(listaFiltrable);
            listaVehiculos.comparatorProperty().bind(tabla.comparatorProperty());

            // Finalmente seteamos la lista para mostrarla en la tabla
            tabla.setItems(listaVehiculos);

            // TODO: Mostrar imagenes
            imagen.setCellValueFactory(dato -> dato.getValue().getImageView().imageProperty());
            imagen.setSortable(false);

            marca.setCellValueFactory(dato -> dato.getValue().marcaProperty());
            modelo.setCellValueFactory(dato -> dato.getValue().modeloProperty());

            fechaEntrada.setCellValueFactory(dato -> dato.getValue().fechaRegistroProperty());
            fechaEntrada.setCellFactory(dato -> new TableCell<>() {
                private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                protected void updateItem(Date fecha, boolean empty) {
                    super.updateItem(fecha, empty);

                    if (empty) { // En caso de que nos filtren tenemos que setear a null para no mostrar
                        setText(null);
                        return;
                    }

                    setText(format.format(fecha));
                }
            });


            tipo.setCellValueFactory(dato -> dato.getValue().getTipoVehiculo().descripcionProperty());

            concesionario.setCellValueFactory(dato -> dato.getValue().concesionarioIDProperty().asObject());
            concesionario.setCellFactory(dato -> new TableCell<>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
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
            acciones.setSortable(false);
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
        String marca = fMarca.getText().trim();
        String modelo = fModelo.getText().trim();
        TipoVehiculo tipo = fTipo.getValue();


        listaFiltrable.setPredicate(vehiculo -> {
            boolean resultado = true;

            if (marca.length() > 0)
                resultado = vehiculo.getMarca().toLowerCase().contains(marca.toLowerCase());
            if (modelo.length() > 0)
                resultado = vehiculo.getModelo().toLowerCase().contains(modelo.toLowerCase());

            return resultado;
        });
    }

    public void limpiar(ActionEvent actionEvent) {
        fModelo.setText("");
        fMarca.setText("");
        fEstado.setValue("");
        fTipo.setValue(null);
        fDesde.setValue(null);
        fHasta.setValue(null);

        tabla.getSortOrder().clear();
        listaFiltrable.setPredicate(mostrar -> true);
    }

    private void mostrarModal(VehiculoVender vehiculo) {
        System.out.println(vehiculo);
        // TODO : Mostrar modal
    }
}
