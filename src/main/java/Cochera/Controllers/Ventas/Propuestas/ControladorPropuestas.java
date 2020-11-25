package Cochera.Controllers.Ventas.Propuestas;

import Cochera.Controllers.Base.DataTable;
import Cochera.Models.Propuestas.Propuesta;
import Cochera.utils.vistas.Boton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControladorPropuestas extends DataTable<Propuesta> {

    // Columnas
    public TableColumn<Propuesta,Integer> cocheVendido;
    public TableColumn<Propuesta,Integer> cliente;
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
        cocheVendido.setCellValueFactory(dato -> dato.getValue().vehiculoVenderIDProperty().asObject());
        cliente.setCellValueFactory(dato -> dato.getValue().clienteIDProperty().asObject());

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


                // ELIMINAR
                eliminar.ajustarImg(55);
                eliminar.establecerTooltip("Eliminar Vehículo");

                setGraphic(botonera);
            }
        });
    }
}
