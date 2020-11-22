package Cochera.Controllers;

import Cochera.DAO.ClienteDAO;
import Cochera.DAO.Crud;
import Cochera.DAO.DAOFactory;
import Cochera.Models.Clientes.Cliente;
import Cochera.Models.Modelo;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public abstract class CMNuevoEditar<T extends Modelo> extends CModal {

    protected final String claseGenerica;

    private final T objeto;
    private FilteredList<T> listaFiltrable;
    private List<Node> camposFormulario;

    public CMNuevoEditar(List<Node> camposFormulario, T objeto) {
        this(objeto);
        this.camposFormulario = camposFormulario;
    }

    public CMNuevoEditar(T objeto) {
        String rutaClase = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        claseGenerica = Arrays.stream(rutaClase.split("\\.")).reduce((primero, ultimo) -> ultimo).get();

        this.objeto = objeto;
    }

    public CMNuevoEditar() {
        this(null);
    }

    protected void initialize() {
        if (camposFormulario != null) {
            establecerObjeto(objeto);
            prohibirEdicion();
        }
    }

    @FXML
    protected void nuevoEditar(ActionEvent actionEvent) {
        resetError();
        Button boton = (Button) actionEvent.getSource();

        if (boton.getText().equals("Guardar") || boton.getText().equals("Crear")) {
            if (!checkCampos()) return;

            try (Crud<T> dao = DAOFactory.obtener(claseGenerica)) {

                if (objeto == null) {

                    // Creamos el objeto en la base de datos y lo añadimos a la lista
                    T objetoCreado = dao.read(dao.create(crearObjeto()));
                    ObservableList<T> listaObs = (ObservableList<T>) listaFiltrable.getSource();

                    listaObs.add(objetoCreado);

                } else {

                    actualizarObjeto(objeto);
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
        try (ClienteDAO dao = new ClienteDAO()) {

            if (dao.delete((Cliente) objeto)) {
                listaFiltrable.getSource().remove(objeto);
                btnCancelar.fire();
            } else {

            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    protected abstract boolean checkCampos();
    protected abstract T crearObjeto();
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
