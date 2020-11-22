package Cochera.Controllers.Ventas.Clientes;

import Cochera.Controllers.ControladorModal;
import Cochera.DAO.ClienteDAO;
import Cochera.Models.Clientes.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;

public class ControladorModalCliente extends ControladorModal<Cliente> {

    // Campos del formulario nuevoEditar
    @FXML private TextField nombre;
    @FXML private TextField apellidos;
    @FXML private TextField telefono;
    @FXML private TextField dni;
    @FXML private TextField presupuesto;
    @FXML private TextArea descripcion;
    @FXML private TextField email;

    // Campos modal eliminar

    @FXML
    private void initialize() {

    }

    @Override
    public void prohibirEdicion() {
        nombre.setDisable(true);
        apellidos.setDisable(true);
        telefono.setDisable(true);
        dni.setDisable(true);
        presupuesto.setDisable(true);
        descripcion.setDisable(true);
        email.setDisable(true);

        nombre.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        apellidos.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        telefono.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        dni.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        presupuesto.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        descripcion.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
        email.setStyle(" -fx-background-color: white , white , white;-fx-background-insets: 0 0 0 0, 0 0 0 0, 0 0 3 0;");
    }

    @Override
    public void permitirEdicion() {
        nombre.setDisable(false);
        apellidos.setDisable(false);
        telefono.setDisable(false);
        dni.setDisable(false);
        presupuesto.setDisable(false);
        descripcion.setDisable(false);
        email.setDisable(false);
    }

    @Override
    public boolean checkCampos() {
        boolean resultado = true;

        if (nombre.getText().trim().length() == 0) {
            resultado = false;
            nombre.setStyle("-fx-border-color: RED");
        }

        if (apellidos.getText().trim().length() == 0) {
            resultado = false;
            apellidos.setStyle("-fx-border-color: RED");
        }

        if (telefono.getText().trim().length() == 0) {
            resultado = false;
            telefono.setStyle("-fx-border-color: RED");
        }

        if (dni.getText().trim().length() == 0) {
            resultado = false;
            dni.setStyle("-fx-border-color: RED");
        }

        if (presupuesto.getText().trim().length() == 0) {
            resultado = false;
            presupuesto.setStyle("-fx-border-color: RED");
        }

        if (descripcion.getText().length() == 0) {
            resultado = false;
            descripcion.setStyle("-fx-border-color: RED");
        }

        if (email.getText().length() == 0) {
            resultado = false;
            email.setStyle("-fx-border-color: RED");
        }

        return resultado;
    }

    @Override
    public void resetError() {

        nombre.setStyle("-fx-border-color: transparent");
        apellidos.setStyle("-fx-border-color: transparent");
        telefono.setStyle("-fx-border-color: transparent");
        dni.setStyle("-fx-border-color: transparent");
        presupuesto.setStyle("-fx-border-color: transparent");
        descripcion.setStyle("-fx-border-color: transparent");
        email.setStyle("-fx-border-color: transparent");

    }

    @Override
    public Cliente crearObjeto() {
        HashMap<String, String> datos = new HashMap<>();

        datos.put("nombre", nombre.getText());
        datos.put("apellidos", apellidos.getText());
        datos.put("telefono", telefono.getText());
        datos.put("dni", dni.getText());
        datos.put("presupuesto", presupuesto.getText());
        datos.put("descripcion", descripcion.getText());
        datos.put("email", email.getText());

        return new Cliente(datos);
    }

    @Override
    public void actualizarObjeto(Cliente cliente) {
        cliente.setApellidos(apellidos.getText());
        cliente.setDni(dni.getText());
        cliente.setEmail(email.getText());
        cliente.setNombre(nombre.getText());
        cliente.setPresupuesto(Float.parseFloat(presupuesto.getText()));
        cliente.setTelefono(telefono.getText());
        cliente.setDescripcionVehiculo(descripcion.getText());
    }

    @Override
    public void establecerObjeto(Cliente cliente) {
        nombre.setText(cliente.getNombre());
        apellidos.setText(cliente.getApellidos());
        telefono.setText(cliente.getTelefono());
        dni.setText(cliente.getDni());
        presupuesto.setText(String.valueOf(cliente.getPresupuesto()));
        descripcion.setText(cliente.getDescripcionVehiculo());
        email.setText(cliente.getEmail());
    }

    public void eliminar() {
        try (ClienteDAO dao = new ClienteDAO()) {

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
