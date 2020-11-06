package Cochera.controllers.Ventas;

import Cochera.controllers.AutoRoot;
import Cochera.controllers.Ventas.Modales.ControladorMCreacion;
import Cochera.controllers.Ventas.Modales.ControladorMEdicion;
import Cochera.dao.TipoVehiculosDAO;
import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Vehiculo.TipoVehiculo;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.prefs.Preferences;

public class ControladorVehiculos implements AutoRoot {


    @FXML
    private AnchorPane parent;
    private Parent root;

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
    private ComboBox<EstadoVehiculo> fEstado;

    /**
     * Lista de vehiculos que permite hacer búsquedas para filtrar
     */
    private FilteredList<VehiculoVender> listaFiltrable;

    /**
     * Almacenamos el ID del concesionario del usuario logueado
     */
    private String concesionarioActual;

    public ControladorVehiculos() {
    }

    @FXML
    private void initialize() {
        iniciarFiltros();
        iniciarTabla();
        iniciarColumnas();
    }

    private void iniciarFiltros() {
        try(TipoVehiculosDAO dao = new TipoVehiculosDAO()) {
            fTipo.setItems(dao.read());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        fEstado.setItems(EstadoVehiculo.obtenerEstados());
    }

    private void iniciarTabla() {
        concesionarioActual = Preferences.userRoot().get("concesionarioID",null);

        try (VehiculoVenderDAO dao = new VehiculoVenderDAO()) {
            // Envolvemos los datos de la base de datos en una lista que nos permita filtrar
            // Lo mantenemos en el estado porque es este tipo de lista la que nos permitirá filtrar por campo en el método correcpondiente
            listaFiltrable = new FilteredList<>(dao.read(), mostrarTodoAlInicio -> true);

            // Actualizamos la tabla cuando haya algún cambio. TODO: Por el momento no lo hay porque no va la actualización
            listaFiltrable.addListener((ListChangeListener.Change<? extends VehiculoVender> change) -> tabla.refresh());

            // Volvemos a envolver para darle la capacidad de ordenarse
            SortedList<VehiculoVender> listaVehiculos = new SortedList<>(listaFiltrable);
            listaVehiculos.comparatorProperty().bind(tabla.comparatorProperty());

            // Finalmente seteamos la lista para mostrarla en la tabla
            tabla.setItems(listaVehiculos);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void iniciarColumnas() {
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
                if (item.equals(Integer.valueOf(concesionarioActual))) {
                    setText("Concesionario Actual");
                    setTextFill(Color.valueOf("#3CC13B"));
                } else { // si no, es que son concesionarios diferentes
                    setText("Otro Concesionario");
                    setTextFill(Color.valueOf("#F3BD32"));
                }
            }
        });


        acciones.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue()));
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
    }

    @FXML
    private void filtrar(ActionEvent actionEvent) {
        String marca = fMarca.getText().trim();
        String modelo = fModelo.getText().trim();
        TipoVehiculo tipo = fTipo.getValue();
        EstadoVehiculo estado = fEstado.getValue();

        LocalDate desde = fDesde.getValue();
        LocalDate hasta = fHasta.getValue();


        // Hacemos un pequeño control de errores para obligar a seleccionar un Desde y Hasta si se ha escogido solo uno
        if ((desde != null && hasta == null) || (desde == null && hasta != null)) {
            fDesde.setStyle("-fx-border-color: red");
            fHasta.setStyle("-fx-border-color: red");
            return;
        } else {
            fDesde.setStyle("-fx-border-color: transparent");
            fHasta.setStyle("-fx-border-color: transparent");
        }


        listaFiltrable.setPredicate(vehiculo -> {
            boolean resMarca = true;
            boolean resModelo = true;
            boolean resTipo = true;
            boolean resEstado = true;
            boolean resFecha = true;


            if (marca.length() > 0)
                resMarca = vehiculo.getMarca().toLowerCase().contains(marca.toLowerCase());
            if (modelo.length() > 0)
                resModelo = vehiculo.getModelo().toLowerCase().contains(modelo.toLowerCase());
            if (tipo != null)
                resTipo = vehiculo.getTipoVehiculo().getDescripcion().equals(tipo.getDescripcion());
            if (estado != null)
                resEstado = estado.isEnConcesionario() == (vehiculo.getConcesionarioID() == Integer.parseInt(concesionarioActual));
            if (desde != null) {
                LocalDate fecha = vehiculo.getFechaRegistro().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                resFecha = fecha.isAfter(desde) && fecha.isBefore(hasta);
            }


            return resMarca && resModelo && resTipo && resEstado && resFecha;
        });
    }

    public void limpiar(ActionEvent actionEvent) {
        fModelo.setText("");
        fMarca.setText("");
        fEstado.setValue(null);
        fTipo.setValue(null);

        fDesde.setValue(null);
        fHasta.setValue(null);
        fDesde.setStyle("-fx-border-color: transparent");
        fHasta.setStyle("-fx-border-color: transparent");

        tabla.getSortOrder().clear();
        listaFiltrable.setPredicate(mostrar -> true);
    }

    @FXML
    private void mostrarModalCreacion() throws IOException {
        Stage modal = new Stage();
        FXMLLoader modalFX = new FXMLLoader(getClass().getResource("/Ventas/Modales/FormNuevoVehiculo.fxml"));

        modal.setScene(new Scene(modalFX.load()));
        modal.initOwner(root.getScene().getWindow());
        modal.initModality(Modality.WINDOW_MODAL);
        modal.initStyle(StageStyle.UNDECORATED);
        modal.setResizable(false);

        root.setStyle("-fx-opacity: 0.4");
        ControladorMCreacion controlador = modalFX.getController();
        controlador.setRoot(root);
        controlador.setLista(listaFiltrable);
        controlador.setControladorVehiculos(this);

        modal.showAndWait();
    }

    // TODO : Borrar esto cuando vaya el refresco de la tabla
    public void cerrarModal(ControladorMCreacion c) {
        c.getBtnCancelar().fire();
        iniciarTabla();
        iniciarColumnas();
    }

    private void mostrarModal(VehiculoVender vehiculo) {
        Stage modal = new Stage();
        FXMLLoader modalFX = new FXMLLoader(getClass().getResource("/Ventas/Modales/FormVehiculoLupa.fxml"));

        try {
            modal.setScene(new Scene(modalFX.load()));
            modal.initOwner(root.getScene().getWindow());
            modal.initModality(Modality.WINDOW_MODAL);
            modal.initStyle(StageStyle.UNDECORATED);
            modal.setResizable(false);

            root.setStyle("-fx-opacity: 0.4");
            ControladorMEdicion controlador = modalFX.getController();
            controlador.setRoot(root);
            controlador.setVehiculo(vehiculo);

            modal.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setRoot(Parent root) {
        this.root = root;
    }
}


/**
 * Clase interna para envovler si un vehículo está o no en el mismo concesionario que el usuario logueado.
 */
class EstadoVehiculo {
    private StringProperty texto;
    private BooleanProperty enConcesionario;

    public EstadoVehiculo(String texto, boolean enConcesionario) {
        this.texto = new SimpleStringProperty(texto);
        this.enConcesionario = new SimpleBooleanProperty(enConcesionario);
    }

    /**
     * Método que usamos para crear las dos opciones que hay que mostrar en el ComboBox de 'Estado'
     * @return
     */
    public static ObservableList<EstadoVehiculo> obtenerEstados() {
        ObservableList<EstadoVehiculo> lista = FXCollections.observableArrayList();

        lista.add(new EstadoVehiculo("Mismo Concesionario", true));
        lista.add(new EstadoVehiculo("Distinto Concesionario", false));

        return lista;
    }

    public boolean isEnConcesionario() {
        return enConcesionario.get();
    }

    @Override
    public String toString() {
        return texto.getValue();
    }
}
