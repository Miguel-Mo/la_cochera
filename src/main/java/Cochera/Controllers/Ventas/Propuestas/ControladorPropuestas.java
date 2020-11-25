package Cochera.Controllers.Ventas.Propuestas;

import Cochera.Controllers.DataTable;
import Cochera.Models.Clientes.Cliente;
import Cochera.Models.Propuestas.Propuesta;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControladorPropuestas extends DataTable<Propuesta> {
/*
    // Columnas
    @FXML private TableColumn<Propuesta,String> marca;
    @FXML private TableColumn<Propuesta,String> modelo;
    @FXML private TableColumn<Propuesta, Date> fechaLimite;
    @FXML private TableColumn<Propuesta,String> cliente;
    @FXML private TableColumn<Propuesta,Integer> precio;
    @FXML private TableColumn<Propuesta,Propuesta> acciones;

    // Filtro
    @FXML private DatePicker fDesde;
    @FXML private DatePicker fHasta;
    @FXML private TextField fNombre;
    @FXML private TextField fStatus;


    public ControladorPropuestas() {
        modalCreacionView = "/Ventas/Modales/FormNuevaPropuesta.fxml";
        modalModificacionView = "/Ventas/Modales/FormPropuestaLupa.fxml";
    }

    @Override
    protected void initialize() {
        super.initialize();
        iniciarColumnas();
    }


    private void iniciarColumnas() {
        marca.setCellValueFactory(dato -> dato.getValue().);





        precio.setCellValueFactory(dato -> dato.getValue().clienteProperty());
        fechaLimite.setCellValueFactory(dato -> dato.getValue().telefonoProperty());

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
                lupa.setOnAction(event -> mostrarModalModificacion(cliente));
            }
        });
    }



*/


}
