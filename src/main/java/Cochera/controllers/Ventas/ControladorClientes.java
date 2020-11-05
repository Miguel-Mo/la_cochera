package Cochera.controllers.Ventas;

import Cochera.dao.ClienteDAO;
import Cochera.dao.VehiculoVenderDAO;
import Cochera.models.Clientes.Cliente;
import Cochera.models.Vehiculo.VehiculoVender;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;

public class ControladorClientes {

    @FXML
    private TableView<Cliente> tabla;

    @FXML
    public TableColumn<Cliente,String> cliente;
    @FXML
    public TableColumn <Cliente,Date>fechaRegistro;
    @FXML
    public TableColumn<Cliente,String> telefono;
    @FXML
    public TableColumn <Cliente,Cliente> acciones;

    FilteredList<Cliente> listaFiltrable;

    public void ContraladorClientes(){

    }


    @FXML
    private void initialize() {
        Preferences usuarioActivo = Preferences.userRoot();
        String clienteID = usuarioActivo.get("clienteID",null);

        try (ClienteDAO dao = new ClienteDAO()) {
            // Envolvemos los datos de la base de datos en una lista que nos permita filtrar
            // Lo mantenemos en el estado porque es este tipo de lista la que nos permitirá filtrar por campo en el método correcpondiente
            listaFiltrable = new FilteredList<>(dao.read(), mostrarTodoAlInicio -> true);

            // Volvemos a envolver para darle la capacidad de ordenarse
            SortedList<Cliente> listaClientes = new SortedList<>(listaFiltrable);
            listaClientes.comparatorProperty().bind(tabla.comparatorProperty());

            // Finalmente seteamos la lista para mostrarla en la tabla
            tabla.setItems(listaClientes);

            cliente.setCellValueFactory(dato -> dato.getValue().clienteProperty());
            telefono.setCellValueFactory(dato -> dato.getValue().telefonoProperty());

            fechaRegistro.setCellValueFactory(dato -> dato.getValue().fechaRegistroProperty());
            fechaRegistro.setCellFactory(dato -> new TableCell<>() {
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



            acciones.setCellValueFactory(dato -> new ReadOnlyObjectWrapper(dato.getValue()));
            acciones.setSortable(false);
            acciones.setCellFactory(dato -> new TableCell<>() {
                private final Button lupa = new Button("Lupa");

                @Override
                protected void updateItem(Cliente cliente, boolean empty) {
                    super.updateItem(cliente, empty);

                    if (empty) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(lupa);
                    lupa.setOnAction(event -> mostrarModal(cliente));
                }
            });

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @FXML
    private void filtrar(ActionEvent actionEvent) {

    }

    public void limpiar(ActionEvent actionEvent) {

    }

    private void mostrarModal(Cliente cliente) {
        System.out.println(cliente);
        // TODO : Mostrar modal
    }
}
