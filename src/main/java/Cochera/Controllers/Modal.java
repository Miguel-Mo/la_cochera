package Cochera.Controllers;

import Cochera.DAO.Crud;
import Cochera.DAO.DAOFactory;
import Cochera.Models.Modelo;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Arrays;

public abstract class Modal<T extends Modelo> {

    public static final String ELIMINAR = "eleminar";
    public static final String CREAR = "crear";
    public static final String EDITAR = "editar";

    private final String claseGenerica;
    private String tipo;

    protected Parent root;
    protected T objeto;
    protected FilteredList<T> listaFiltrable;

    @FXML protected Button btnCancelar;


    public Modal() {
        String rutaClase = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        claseGenerica = Arrays.stream(rutaClase.split("\\.")).reduce((primero, ultimo) -> ultimo).get();
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
        try (Crud<T> dao = DAOFactory.obtener(claseGenerica)) {

            if (dao.delete(objeto)) {
                listaFiltrable.getSource().remove(objeto);
                btnCancelar.fire();
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    protected abstract void resetError();
    protected abstract boolean checkCampos();

    protected abstract T crearObjeto();
    protected abstract void actualizarObjeto(T objeto);
    protected abstract void establecerObjeto(T objeto);

    protected abstract void prohibirEdicion();
    protected abstract void permitirEdicion();

    @FXML
    protected void cerrar(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        root.setStyle("-fx-opacity: 1");
        stage.close();
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;

        if (tipo == null || !tipo.equals(ELIMINAR)) {
            establecerObjeto(objeto);
            prohibirEdicion();
        }
    }

    public void setLista(FilteredList<T> lista) {
        this.listaFiltrable = lista;
    }
}
