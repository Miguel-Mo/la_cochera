package Cochera.Controllers;

import Cochera.DAO.Crud;
import Cochera.DAO.DAOFactory;
import Cochera.Models.Modelo;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class CMNuevoEditar<T extends Modelo> extends CModal<T> {

    protected T objeto;
    protected FilteredList<T> listaFiltrable;

    public CMNuevoEditar(T objeto) {
        super();
        this.objeto = objeto;
    }

    public CMNuevoEditar() {
        this(null);
    }

    protected void initialize() {
        if (objeto != null) {
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

    protected abstract void resetError();
    protected abstract boolean checkCampos();

    protected abstract T crearObjeto();
    protected abstract void actualizarObjeto(T objeto);
    protected abstract void establecerObjeto(T objeto);

    protected abstract void prohibirEdicion();
    protected abstract void permitirEdicion();

    public void setLista(FilteredList<T> lista) {
        this.listaFiltrable = lista;
    }
}
