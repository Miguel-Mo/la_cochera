package Cochera.Controllers.Ventas.Clientes;

import Cochera.Controllers.Base.DataTable;
import Cochera.Models.Clientes.Cliente;
import Cochera.utils.vistas.Boton;
import Cochera.utils.vistas.Modal;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ControladorClientes extends DataTable<Cliente> {

    // Columnas
    @FXML private TableColumn<Cliente,String> nombreCliente;
    @FXML private TableColumn<Cliente,Date> fechaRegistro;
    @FXML private TableColumn<Cliente,String> telefono;
    @FXML private TableColumn<Cliente,Cliente> acciones;

    // Filtro
    @FXML private DatePicker fDesde;
    @FXML private DatePicker fHasta;
    @FXML private TextField fNombre;
    @FXML private TextField fTelefono;

    @Override
    protected void initialize() {
        super.initialize();
        iniciarColumnas();
    }

    private void iniciarColumnas() {
        nombreCliente.setCellValueFactory(dato -> dato.getValue().clienteProperty());
        telefono.setCellValueFactory(dato -> dato.getValue().telefonoProperty());

        fechaRegistro.setCellValueFactory(dato -> dato.getValue().fechaRegistroProperty());
        fechaRegistro.setCellFactory(dato -> new TableCell<>() {
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

        acciones.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue()));
        acciones.setSortable(false);
        acciones.setCellFactory(dato -> new TableCell<>() {

            private final Boton lupa = new Boton(new ImageView("/icons/lupa.png"));
            private final Boton eliminar = new Boton(new ImageView("/icons/delete.png"));

            private final HBox botonera = new HBox(lupa, eliminar);

            @Override
            protected void updateItem(Cliente cliente, boolean empty) {
                super.updateItem(cliente, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                // LUPA
                lupa.ajustarImg(55);
                lupa.establecerTooltip("Mostrar Detalles");
                lupa.setOnAction(event -> mostrarModalModificacion(cliente));


                // ELIMINAR
                eliminar.ajustarImg(55);
                eliminar.establecerTooltip("Eliminar Vehículo");
                eliminar.setOnAction(event -> mostrarModalEliminacion(cliente));

                setGraphic(botonera);
            }
        });
    }

    @FXML
    private void mostrarModalCreacion() {
        Modal modal = new Modal(this,"/Ventas/Clientes/FormNuevoCliente.fxml");
        modal.setControlador(new CModalCliente());
        modal.showAndWait();
    }

    @FXML
    private void mostrarModalModificacion(Cliente cliente) {
        Modal modal = new Modal(this,"/Ventas/Clientes/FormClienteLupa.fxml");
        modal.setControlador(new CModalCliente(cliente));
        modal.esVista();
        modal.showAndWait();
    }

    @FXML
    private void mostrarModalEliminacion(Cliente cliente) {
        Modal modal = new Modal(this, "/Modales/Eliminar.fxml");
        modal.setControlador(new CModalCliente(cliente));
        modal.showAndWait();
    }

    @FXML
    private void filtrar() {
        String nombre = fNombre.getText().trim();
        String telefono = fTelefono.getText().trim();

        LocalDate desde = fDesde.getValue();
        LocalDate hasta = fHasta.getValue();

        // Hacemos un pequeño control de errores para obligar a seleccionar un Desde y Hasta si se ha escogido solo uno
        if ((desde != null && hasta == null) || (desde == null && hasta != null)) {
            fDesde.setStyle("-fx-border-color: #ff0000");
            fHasta.setStyle("-fx-border-color: red");
            return;
        } else {
            fDesde.setStyle("-fx-border-color: transparent");
            fHasta.setStyle("-fx-border-color: transparent");
        }


        listaFiltrable.setPredicate(cliente -> {
            boolean resNombre = true;
            boolean resTelefono = true;
            boolean resFecha = true;


            if (nombre.length() > 0)
                resNombre = cliente.getNombre().toLowerCase().contains(nombre.toLowerCase());
            if (telefono.length() > 0)
                resTelefono = cliente.getTelefono().toLowerCase().contains(telefono.toLowerCase());
            if (desde != null) {
                LocalDate fecha = cliente.getFechaRegistro().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                resFecha = fecha.isAfter(desde) && fecha.isBefore(hasta);
            }


            return resNombre && resTelefono && resFecha;
        });
    }

    @FXML
    private void limpiar() {
        fNombre.setText("");
        fTelefono.setText("");

        fDesde.setValue(null);
        fHasta.setValue(null);
        fDesde.setStyle("-fx-border-color: transparent");
        fHasta.setStyle("-fx-border-color: transparent");

        tabla.getSortOrder().clear();
        listaFiltrable.setPredicate(mostrar -> true);
    }
}
