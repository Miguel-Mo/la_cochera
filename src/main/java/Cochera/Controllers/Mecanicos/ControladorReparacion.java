package Cochera.Controllers.Mecanicos;

import Cochera.Controllers.Base.DataTable;
import Cochera.Models.Reparaciones.Reparacion;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Time;
import java.util.ArrayList;

public class ControladorReparacion extends DataTable<Reparacion> {

    // Columnas
    @FXML private TableColumn<Reparacion,String> marca;
    @FXML private TableColumn<Reparacion,String> modelo;
    @FXML private TableColumn<Reparacion, Time> tiempoEstimado;
    @FXML private TableColumn<Reparacion,String> estado;
    @FXML private TableColumn<Reparacion,Reparacion> acciones;

    // Filtro
    @FXML private TextField tfFiltroNombreMecanico;
    @FXML private TextField tfFiltroMarca;
    @FXML private TextField tfFiltroModelo;
    @FXML private ComboBox<String> cbEstado;



    public ControladorReparacion() {

    }

    @Override
    protected void initialize() {
        super.initialize();
        iniciarColumnas();
        iniciarFiltros();
    }

    private void iniciarColumnas() {
        marca.setCellValueFactory(dato -> dato.getValue().getVehiculoReparar().marcaProperty());
        modelo.setCellValueFactory(dato -> dato.getValue().getVehiculoReparar().modeloProperty());
        //tiempoEstimado.setCellValueFactory(dato -> (ObservableValue<Time>) dato.getValue().getTiempoEstimado());
        estado.setCellValueFactory(dato -> dato.getValue().estadoProperty());

        acciones.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue()));
        acciones.setSortable(false);
        acciones.setCellFactory(dato -> new TableCell<>() {

            @Override
            protected void updateItem(Reparacion reparacion, boolean empty) {
                super.updateItem(reparacion, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

            }
        });
    }

    private void iniciarFiltros() {

        ArrayList<String> datos=new ArrayList<>();
        datos.add(Reparacion.PENDIENTE);
        datos.add(Reparacion.ENPROCESO);
        datos.add(Reparacion.FINALIZADO);

        cbEstado.setItems(FXCollections.observableArrayList(datos));
    }




    @FXML
    private void filtrar() {
        String marca = tfFiltroMarca.getText().trim();
        String modelo = tfFiltroModelo.getText().trim();
        String nombreMecanico = tfFiltroNombreMecanico.getText().trim();
        String estado = cbEstado.getValue();

        listaFiltrable.setPredicate(reparacion -> {
            boolean resNombreMecanico = true;
            boolean resMarca = true;
            boolean resModelo = true;
            boolean resEstado = true;


            if (nombreMecanico.length() > 0)
                resNombreMecanico = reparacion.getMecanico().getNombre().toLowerCase().contains(nombreMecanico.toLowerCase());
            if (marca.length() > 0)
                resMarca = reparacion.getVehiculoReparar().getMarca().toLowerCase().contains(marca.toLowerCase());
            if (modelo.length() > 0)
                resNombreMecanico = reparacion.getVehiculoReparar().getModelo().toLowerCase().contains(modelo.toLowerCase());
            if (estado != null)
                resEstado = reparacion.getEstado().equals(estado);

            return resNombreMecanico && resMarca && resModelo && resEstado;
        });
    }

    @FXML
    private void limpiar() {
        tfFiltroNombreMecanico.setText("");
        tfFiltroModelo.setText("");
        tfFiltroMarca.setText("");
        cbEstado.setValue(null);

        tabla.getSortOrder().clear();
        listaFiltrable.setPredicate(mostrar -> true);
    }
}




