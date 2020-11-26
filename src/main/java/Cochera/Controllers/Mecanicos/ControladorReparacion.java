package Cochera.Controllers.Mecanicos;

import Cochera.Controllers.Base.DataTable;
import Cochera.DAO.ReparacionesDAO;
import Cochera.Main;
import Cochera.Models.Reparaciones.Reparacion;
import Cochera.Models.Usuario.Mecanico;
import Cochera.utils.vistas.Boton;
import Cochera.utils.vistas.Modal;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class ControladorReparacion extends DataTable<Reparacion> {

    // Columnas
    @FXML private TableColumn<Reparacion, String> marca;
    @FXML private TableColumn<Reparacion, String> modelo;
    @FXML private TableColumn<Reparacion, String> mecanico;
    @FXML private TableColumn<Reparacion, String> cliente;
    @FXML private TableColumn<Reparacion, String> tiempoEstimado;
    @FXML private TableColumn<Reparacion, String> estado;
    @FXML private TableColumn<Reparacion, Reparacion> acciones;

    // Filtro
    @FXML private TextField tfFiltroNombreMecanico;
    @FXML private TextField tfFiltroMarca;
    @FXML private TextField tfFiltroModelo;
    @FXML private ComboBox<String> cbEstado;


    private final boolean esJefe = ((Mecanico) Main.usuario).isEsJefe();
    private final int mecanicoID = Integer.parseInt(Preferences.userRoot().get("id",null));

    public ControladorReparacion() {

    }

    @Override
    protected void initialize() {
        super.initialize();
        iniciarColumnas();

        if (esJefe) iniciarFiltros();
        else listaFiltrable.setPredicate(reparacion -> reparacion.getMecanico().getId() == mecanicoID);
    }

    private void iniciarColumnas() {

        marca.setCellValueFactory(dato -> dato.getValue().getVehiculoReparar().marcaProperty());
        modelo.setCellValueFactory(dato -> dato.getValue().getVehiculoReparar().modeloProperty());

        if (esJefe) {
            mecanico.setCellValueFactory(dato -> dato.getValue().getMecanico().nombreCompletoProperty());
        } else {
            cliente.setCellValueFactory(dato -> dato.getValue().getCliente().clienteProperty());
        }

        //mecanico.setCellValueFactory(dato -> dato.getValue().getMecanico().nombreCompletoProperty());

        tiempoEstimado.setCellValueFactory(dato -> dato.getValue().tiempoEstimadoProperty());

        estado.setCellValueFactory(dato -> dato.getValue().estadoProperty());

        acciones.setCellValueFactory(dato -> new ReadOnlyObjectWrapper<>(dato.getValue()));
        acciones.setSortable(false);
        acciones.setCellFactory(dato -> new TableCell<>() {


            private final Boton lupa = new Boton(new ImageView("/icons/lupa.png"));
            private final Boton play = new Boton(new ImageView("/icons/play.png"));
            private final Boton eliminar = new Boton(new ImageView("/icons/delete.png"));

            private final HBox botonera = new HBox(lupa, play);

            @Override
            protected void updateItem(Reparacion reparacion, boolean empty) {
                super.updateItem(reparacion, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                if (!esJefe) {

                    // LUPA
                    lupa.ajustarImg(55);
                    lupa.establecerTooltip("Mostrar Detalles");
                    lupa.setOnAction(event -> mostrarModalLupa(reparacion));

                    // PLAY
                    play.ajustarImg(55);

                    switch (reparacion.getEstado()) {
                        case Reparacion.ENPROCESO:
                            play.establecerImagen(new ImageView("/icons/finish.png"),55);
                            play.establecerTooltip("Finalizar");
                            break;
                        case Reparacion.FINALIZADO:
                            play.establecerImagen(new ImageView("/icons/send.png"),55);
                            play.establecerTooltip("Enviar parte");
                            break;
                        case Reparacion.AVISADO:
                            play.setVisible(false);
                            break;
                    }

                    play.setOnAction(event -> realizarAccion(event,reparacion));

                    setGraphic(botonera);

                } else {

                    // ELIMINAR
                    eliminar.ajustarImg(55);
                    eliminar.establecerTooltip("Eliminar Reparación");

                    setGraphic(eliminar);

                }
            }
        });

    }

    private void iniciarFiltros() {
        ArrayList<String> datos=new ArrayList<>();
        datos.add(Reparacion.PENDIENTE);
        datos.add(Reparacion.ENPROCESO);
        datos.add(Reparacion.FINALIZADO);
        datos.add(Reparacion.AVISADO);

        cbEstado.setItems(FXCollections.observableArrayList(datos));
    }

    @FXML
    private void mostrarModalCreacion() {
        Modal modal = new Modal(this, "/Mecanicos/VentanaAgregarReparacion.fxml");
        CModalReparacionNueva controlador = new CModalReparacionNueva();
        controlador.setLista(listaFiltrable);
        modal.setControlador(controlador);
        modal.showAndWait();
    }

    private void mostrarModalLupa(Reparacion reparacion) {
        Modal modal = new Modal(this,"/Mecanicos/FormReparacionLupa.fxml");
        modal.setControlador(new CModalReparacionLupa(reparacion));
        modal.showAndWait();
    }

    private void realizarAccion(ActionEvent actionEvent, Reparacion reparacion) {
        Boton boton = (Boton) actionEvent.getSource();

        switch (reparacion.getEstado()) {
            case Reparacion.PENDIENTE:

                reparacion.setEstado(Reparacion.ENPROCESO);

                try (ReparacionesDAO dao = new ReparacionesDAO()) {
                    dao.comenzarTrabajo(reparacion);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                boton.establecerImagen(new ImageView("/icons/finish.png"),55);
                boton.establecerTooltip("Finalizar");

                break;
            case Reparacion.ENPROCESO:

                reparacion.setEstado(Reparacion.FINALIZADO);

                try (ReparacionesDAO dao = new ReparacionesDAO()) {
                    dao.finalizarTrabajo(reparacion);
                    reparacion.setTiempoReal(dao.read(reparacion.getId()).getTiempoReal());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                boton.establecerImagen(new ImageView("/icons/send.png"),55);
                boton.establecerTooltip("Enviar parte");

                break;
            case Reparacion.FINALIZADO:

                reparacion.setEstado(Reparacion.AVISADO);

                try (ReparacionesDAO dao = new ReparacionesDAO()) {
                    dao.update(reparacion);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                boton.setVisible(false);
                break;
        }
    }

//    private void mostrarModalEliminacion(Reparacion reparacion) {
//        Modal modal = new Modal(this, "/Modales/Eliminar.fxml");
//        modal.setControlador(new CModalReparacion(reparacion));
//        modal.showAndWait();
//    }

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
                resNombreMecanico = reparacion.getMecanico().getNombreCompleto().toLowerCase().contains(nombreMecanico.toLowerCase());
            if (marca.length() > 0)
                resMarca = reparacion.getVehiculoReparar().getMarca().toLowerCase().contains(marca.toLowerCase());
            if (modelo.length() > 0)
                resModelo = reparacion.getVehiculoReparar().getModelo().toLowerCase().contains(modelo.toLowerCase());
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




