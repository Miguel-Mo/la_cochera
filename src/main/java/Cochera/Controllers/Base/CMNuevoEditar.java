package Cochera.Controllers.Base;

import Cochera.Controllers.Base.CModal;
import Cochera.DAO.Crud;
import Cochera.DAO.DAOFactory;
import Cochera.Models.Modelo;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CMNuevoEditar<T extends Modelo> extends CModal {

    protected final String claseGenerica;

    private final T objeto;
    private FilteredList<T> listaFiltrable;

    protected boolean eliminar;

    @FXML protected Button btnEliminar;
    @FXML protected Button btnAceptar;

    private List<Control> camposFormulario;

    public CMNuevoEditar(T objeto, boolean eliminar) {
        String rutaClase = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        claseGenerica = Arrays.stream(rutaClase.split("\\.")).reduce((primero, ultimo) -> ultimo).get();

        this.objeto = objeto;
        this.eliminar = eliminar;
    }

    public CMNuevoEditar(T objeto) {
        this(objeto,false);
    }

    public CMNuevoEditar() {
        this(null,false);
    }

    @Override
    protected void initialize() {
        super.initialize();

        if (eliminar) {
            btnEliminar.setOnAction(actionEvent -> eliminar());
            return;
        }

        btnAceptar.setOnAction(this::nuevoEditar);

        this.camposFormulario = new ArrayList<>();
        Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(FXML.class))
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        camposFormulario.add((Control) field.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        establecerObjeto(objeto);
        prohibirEdicion();
    }

    @FXML
    protected void nuevoEditar(ActionEvent actionEvent) {
        resetError();
        Button boton = (Button) actionEvent.getSource();

        if (boton.getText().equals("Guardar") || boton.getText().equals("Crear")) {
            if (!checkCampos()) return;

            try (Crud<T> dao = DAOFactory.obtener(claseGenerica)) {

                actualizarObjeto(objeto);

                if (objeto.getId() == 0) {

                    // Creamos el objeto en la base de datos y lo añadimos a la lista
                    T objetoCreado = dao.read(dao.create(objeto));
                    ObservableList<T> listaObs = (ObservableList<T>) listaFiltrable.getSource();

                    listaObs.add(objetoCreado);

                } else {

                    // Actualizamos la base de datos
                    dao.update(objeto);

                }

                btnCancelar.fire();

            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        } else if (boton.getText().contentEquals("Editar")) {

            permitirEdicion();
            boton.setText("Guardar");

        }
    }

    public void eliminar() {
        try (Crud<T> dao = DAOFactory.obtener(claseGenerica)) {

            if (dao.delete(objeto)) {
                listaFiltrable.getSource().remove(objeto);
                btnCancelar.fire();
            } else {

            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    protected abstract boolean checkCampos();
    protected abstract void actualizarObjeto(T objeto);
    protected abstract void establecerObjeto(T objeto);

    protected void resetError() {
        camposFormulario.forEach(campo -> campo.setStyle("-fx-border-color: transparent"));
    }

    protected void prohibirEdicion() {
        camposFormulario.forEach(campo -> campo.setDisable(true));
    }

    protected void permitirEdicion() {
        camposFormulario.forEach(campo -> campo.setDisable(false));
    }

    public void setLista(FilteredList<T> lista) {
        this.listaFiltrable = lista;
    }
}
