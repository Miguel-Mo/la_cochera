package Cochera.Controllers.Ventas.Vehiculos;

import Cochera.Controllers.DataTable;
import Cochera.DAO.TipoVehiculosDAO;
import Cochera.Models.Vehiculo.TipoVehiculo;
import Cochera.Models.Vehiculo.VehiculoVender;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.prefs.Preferences;

public class ControladorVehiculos extends DataTable<VehiculoVender> {

    // Columnas
    @FXML private TableColumn<VehiculoVender, Image> imagen;
    @FXML private TableColumn<VehiculoVender, String> marca;
    @FXML private TableColumn<VehiculoVender, String> modelo;
    @FXML private TableColumn<VehiculoVender, Date> fechaEntrada;
    @FXML private TableColumn<VehiculoVender, String> tipo;
    @FXML private TableColumn<VehiculoVender, Integer> concesionario;
    @FXML private TableColumn<VehiculoVender, VehiculoVender> acciones;

    // Filtros
    @FXML private DatePicker fDesde;
    @FXML private DatePicker fHasta;
    @FXML private TextField fModelo;
    @FXML private ComboBox<TipoVehiculo> fTipo;
    @FXML private TextField fMarca;
    @FXML private ComboBox<EstadoVehiculo> fEstado;

    /** Almacenamos el ID del concesionario del usuario logueado */
    private final String concesionarioActual;

    public ControladorVehiculos() {
        concesionarioActual = Preferences.userRoot().get("concesionarioID",null);
    }

    @Override
    protected void initialize() {
        super.initialize();
        iniciarColumnas();
        iniciarFiltros();
    }

    private void iniciarFiltros() {
        try(TipoVehiculosDAO dao = new TipoVehiculosDAO()) {
            fTipo.setItems(dao.read());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        fEstado.setItems(EstadoVehiculo.obtenerEstados());
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

            //Creating a graphic (image)
            Image img = new Image("/icons/lupa.png");
            ImageView view = new ImageView(img);

            private final Button lupa = new Button();

            @Override
            protected void updateItem(VehiculoVender vehiculo, boolean empty) {
                super.updateItem(vehiculo, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                lupa.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");

                //TODO HOVER DE LUPA
                if(lupa.isHover()){
                    lupa.setStyle(" -fx-background-color: red , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
                }


                lupa.setGraphic(view);
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

        modal.showAndWait();
    }

    @FXML
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
}


/** Clase interna para envovler si un vehículo está o no en el mismo concesionario que el usuario logueado. */
class EstadoVehiculo {
    private final StringProperty texto;
    private final BooleanProperty enConcesionario;

    public EstadoVehiculo(String texto, boolean enConcesionario) {
        this.texto = new SimpleStringProperty(texto);
        this.enConcesionario = new SimpleBooleanProperty(enConcesionario);
    }

    /**
     * Método que usamos para crear las dos opciones que hay que mostrar en el ComboBox de 'Estado'
     * @return La lista a mostrar en el ComboBox
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
