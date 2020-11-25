package Cochera.Controllers.Ventas.Propuestas;

import Cochera.Controllers.Base.DataTable;
import Cochera.Controllers.Ventas.Clientes.CModalCliente;
import Cochera.Models.Propuestas.Propuesta;
import Cochera.utils.vistas.Boton;
import Cochera.utils.vistas.Modal;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControladorPropuestas extends DataTable<Propuesta> {

    // Columnas
    public TableColumn<Propuesta,String> cocheVendido;
    public TableColumn<Propuesta,String> cliente;
    public TableColumn<Propuesta,Date> fechaLimite;
    public TableColumn<Propuesta,Float> precio;
    public TableColumn<Propuesta,String> estado;
    public TableColumn<Propuesta,Propuesta> acciones;


    public ControladorPropuestas() {

    }

    @Override
    protected void initialize() {
        super.initialize();
        iniciarColumnas();
    }

    private void iniciarColumnas() {
        cocheVendido.setCellValueFactory(dato -> dato.getValue().getVehiculoVender().marcaModeloProperty());
        cliente.setCellValueFactory(dato -> dato.getValue().getCliente().clienteProperty());

        fechaLimite.setCellValueFactory(dato -> dato.getValue().fechaLimiteProperty());
        fechaLimite.setCellFactory(dato -> new TableCell<>() {
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

        precio.setCellValueFactory(dato -> dato.getValue().presupuestoProperty().asObject());
        estado.setCellValueFactory(dato -> dato.getValue().estadoProperty());

        acciones.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue()));
        acciones.setSortable(false);
        acciones.setCellFactory(dato -> new TableCell<>() {

            private final Boton lupa = new Boton(new ImageView("/icons/lupa.png"));
            private final Boton eliminar = new Boton(new ImageView("/icons/delete.png"));

            private final HBox botonera = new HBox(lupa, eliminar);

            @Override
            protected void updateItem(Propuesta propuesta, boolean empty) {
                super.updateItem(propuesta, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                // LUPA
                lupa.ajustarImg(55);
                lupa.establecerTooltip("Mostrar Detalles");
                lupa.setOnAction(event -> mostrarModalModificacion(propuesta));


                // ELIMINAR
                eliminar.ajustarImg(55);
                eliminar.establecerTooltip("Eliminar Vehículo");
                eliminar.setOnAction(event -> mostrarModalEliminacion(propuesta));

                setGraphic(botonera);
            }
        });
    }

    @FXML
    private void mostrarModalCreacion() {
        Modal modal = new Modal(this,"/Ventas/Propuestas/FormNuevaPropuesta.fxml");
        modal.setControlador(new CModalPropuesta());
        modal.showAndWait();
    }

    @FXML
    private void mostrarModalModificacion(Propuesta propuesta) {
        Modal modal = new Modal(this,"/Ventas/Propuestas/FormPropuestaLupa.fxml");
        modal.setControlador(new CModalPropuesta(propuesta));
        modal.esVista();
        modal.showAndWait();
    }

    @FXML
    private void mostrarModalEliminacion(Propuesta propuesta) {
        Modal modal = new Modal(this, "/Modales/Eliminar.fxml");
        modal.setControlador(new CModalPropuesta(propuesta));
        modal.showAndWait();
    }
}
