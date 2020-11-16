package Cochera.Controllers;

import Cochera.DAO.Crud;
import Cochera.Models.Clientes.Cliente;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.SQLException;

public abstract class Modal<T> implements AutoRoot {

    private String claseGenerica;

    protected Parent root;
    protected T objeto;
    protected FilteredList<T> listaFiltrable;

    @FXML protected Button btnCancelar;

    public Modal() {
        claseGenerica = ;
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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else if (boton.getText().contentEquals("Editar")) {

            permitirEdicion();
            boton.setText("Guardar");

        }
    }


    public void setObjeto(T objeto) {
        this.objeto = objeto;
        establecerObjeto(objeto);
        prohibirEdicion();
    }

    protected abstract void resetError();
    protected abstract boolean checkCampos();

    protected abstract T crearObjeto();
    protected abstract void actualizarObjeto(T objeto);
    protected abstract void establecerObjeto(T objeto);

    protected abstract void prohibirEdicion();
    protected abstract void permitirEdicion();


    protected void cerrar(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        root.setStyle("-fx-opacity: 1");
        stage.close();
    }

    @Override
    public void setRoot(Parent root) {
        this.root = root;
    }

    protected void setLista(FilteredList<T> lista) {
        this.listaFiltrable = lista;
    }
}
