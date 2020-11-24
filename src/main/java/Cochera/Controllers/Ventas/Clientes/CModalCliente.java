package Cochera.Controllers.Ventas.Clientes;

import Cochera.Controllers.Base.CMNuevoEditar;
import Cochera.Models.Clientes.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CModalCliente extends CMNuevoEditar<Cliente> {

    // Campos del formulario nuevoEditar
    @FXML private TextField nombre;
    @FXML private TextField apellidos;
    @FXML private TextField telefono;
    @FXML private TextField dni;
    @FXML private TextField presupuesto;
    @FXML private TextArea descripcion;
    @FXML private TextField email;

    private final List<TextInputControl> campos = new ArrayList<>() {
        {
            add(nombre); add(apellidos); add(telefono); add(dni); add(presupuesto); add(descripcion); add(email);
        }
    };

    public CModalCliente(Cliente cliente, boolean eliminar) {
        super(cliente,eliminar);
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
        AtomicBoolean resultado = new AtomicBoolean(true);

        campos.forEach(campo -> {
            if (campo.getText().trim().length() == 0) {
                resultado.set(false);
                campo.setStyle("-fx-border-color: RED");
            }
        });

        return resultado.get();
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
