package Cochera.Controllers.Ventas.Clientes;

import Cochera.Controllers.DataTable;
import Cochera.Models.Clientes.Cliente;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ControladorClientes extends DataTable<Cliente> {

    // Columnas
    @FXML private TableColumn<Cliente,String> nombreCliente;
    @FXML private TableColumn <Cliente,Date> fechaRegistro;
    @FXML private TableColumn<Cliente,String> telefono;
    @FXML private TableColumn <Cliente,Cliente> acciones;

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

            private final Button lupa = new Button("Lupa");
            private final ImageView iconoLupa = new ImageView("/icons/lupa.png");

            @Override
            protected void updateItem(Cliente cliente, boolean empty) {
                super.updateItem(cliente, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                lupa.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");

                //TODO HOVER DE LUPA
                if(lupa.isHover()){
                    lupa.setStyle(" -fx-background-color: red , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
                }

                lupa.setGraphic(iconoLupa);
                setGraphic(lupa);
                lupa.setOnAction(event -> mostrarModal(cliente));
            }
        });
    }

    @FXML
    private void filtrar(ActionEvent actionEvent) {
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
    private void limpiar(ActionEvent actionEvent) {
        fNombre.setText("");
        fTelefono.setText("");

        fDesde.setValue(null);
        fHasta.setValue(null);
        fDesde.setStyle("-fx-border-color: transparent");
        fHasta.setStyle("-fx-border-color: transparent");

        tabla.getSortOrder().clear();
        listaFiltrable.setPredicate(mostrar -> true);
    }

    @FXML
    private void mostrarModalCreacion() {
        Stage modal = new Stage();
        FXMLLoader modalFX = new FXMLLoader(getClass().getResource("/Ventas/Modales/FormNuevoCliente.fxml"));

        try {
            modal.setScene(new Scene(modalFX.load()));
            modal.initOwner(root.getScene().getWindow());
            modal.initModality(Modality.WINDOW_MODAL);
            modal.initStyle(StageStyle.UNDECORATED);
            modal.alwaysOnTopProperty();
            modal.setResizable(false);

            root.setStyle("-fx-opacity: 0.4");
            ControladorModal controlador = modalFX.getController();
            controlador.setRoot(root);
            controlador.setLista(listaFiltrable);

            modal.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarModal(Cliente cliente) {
        Stage modal = new Stage();
        FXMLLoader modalFX = new FXMLLoader(getClass().getResource("/Ventas/Modales/FormClienteLupa.fxml"));

        try {
            modal.setScene(new Scene(modalFX.load()));
            modal.initOwner(root.getScene().getWindow());
            modal.initModality(Modality.WINDOW_MODAL);
            modal.initStyle(StageStyle.UNDECORATED);
            modal.alwaysOnTopProperty();
            modal.setResizable(false);

            root.setStyle("-fx-opacity: 0.4");
            ControladorModal controlador = modalFX.getController();
            controlador.setRoot(root);
            controlador.setObjeto(cliente);

            modal.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
