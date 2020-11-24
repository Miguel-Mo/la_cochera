package Cochera.Controllers.Base;

import Cochera.DAO.Crud;
import Cochera.DAO.DAOFactory;
import Cochera.Models.Modelo;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class CMNuevoEditar<T extends Modelo> extends CModal {

    protected final String claseGenerica;

    private final T objeto;
    private FilteredList<T> listaFiltrable;

    @FXML protected Button btnAccion;
    private List<Control> camposFormulario;

    public CMNuevoEditar(T objeto) {
        String rutaClase = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        claseGenerica = Arrays.stream(rutaClase.split("\\.")).reduce((primero, ultimo) -> ultimo).get();

        this.objeto = objeto;
    }

    @Override
    protected void initialize() {
        super.initialize();

        btnAccion.setOnAction(this::accionesBasicas);

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


        if (!camposFormulario.stream().allMatch(Objects::isNull)) {
            preEstablecerObjeto();
            establecerObjeto(objeto);
        }
    }

    protected void preEstablecerObjeto() { }
    protected abstract void establecerObjeto(T objeto);
    protected abstract void actualizarObjeto(T objeto);
    protected abstract boolean checkCampos();

    @FXML
    protected void accionesBasicas(ActionEvent actionEvent) {
        Button boton = (Button) actionEvent.getSource();

        if (boton.getText().equals("Guardar") || boton.getText().equals("Crear")) {

            resetError();
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

        } else if (boton.getText().contentEquals("Eliminar")) {

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
    }

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
