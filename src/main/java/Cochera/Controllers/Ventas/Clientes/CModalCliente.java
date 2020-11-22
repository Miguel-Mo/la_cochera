package Cochera.Controllers.Ventas.Clientes;

import Cochera.Controllers.CMNuevoEditar;
import Cochera.DAO.ClienteDAO;
import Cochera.Models.Clientes.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;

public class CModalCliente extends CMNuevoEditar<Cliente> {

    // Campos del formulario nuevoEditar
    @FXML private TextField nombre;
    @FXML private TextField apellidos;
    @FXML private TextField telefono;
    @FXML private TextField dni;
    @FXML private TextField presupuesto;
    @FXML private TextArea descripcion;
    @FXML private TextField email;

    public CModalCliente(Cliente cliente) {
        super(cliente);
    }

    public CModalCliente() {
        super();
    }

    @FXML
    protected void initialize() {
        super.initialize();
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
}
