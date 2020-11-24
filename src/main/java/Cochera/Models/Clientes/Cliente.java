package Cochera.Models.Clientes;

import Cochera.DAO.ClienteDAO;
import Cochera.Models.Modelo;
import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Cliente extends Modelo {

    private final FloatProperty presupuesto;
    private final StringProperty nombre,apellidos,telefono,dni,email,descripcionVehiculo;
    private SimpleObjectProperty<Date> fechaRegistro;
    private int id;

    public Cliente() {

        presupuesto = new SimpleFloatProperty();

        nombre = new SimpleStringProperty();
        apellidos = new SimpleStringProperty();
        telefono = new SimpleStringProperty();
        dni = new SimpleStringProperty();
        email = new SimpleStringProperty();
        descripcionVehiculo = new SimpleStringProperty();

    }

    public Cliente(ResultSet rs) throws SQLException {

        String tabla = ClienteDAO.TABLA;

        presupuesto = new SimpleFloatProperty(rs.getFloat(tabla + ".presupuesto"));

        nombre = new SimpleStringProperty(rs.getString(tabla + ".nombre"));
        apellidos = new SimpleStringProperty(rs.getString(tabla + ".apellidos"));
        telefono = new SimpleStringProperty(rs.getString(tabla + ".telefono"));
        dni = new SimpleStringProperty(rs.getString(tabla + ".dni"));
        email = new SimpleStringProperty(rs.getString(tabla + ".email"));
        descripcionVehiculo = new SimpleStringProperty(rs.getString(tabla + ".descripcionVehiculo"));

        fechaRegistro = new SimpleObjectProperty<>(new Date(rs.getTimestamp(tabla + ".fechaRegistro").getTime()));

        id = rs.getInt(tabla + ".id");
    }

    public float getPresupuesto() {
        return presupuesto.get();
    }

    public FloatProperty presupuestoProperty() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto.set(presupuesto);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public Date getFechaRegistro() {
        return fechaRegistro.get();
    }

    public ObjectProperty<Date> fechaRegistroProperty() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro.set(fechaRegistro);
    }

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionVehiculo() {
        return descripcionVehiculo.get();
    }

    public StringProperty descripcionVehiculoProperty() {
        return descripcionVehiculo;
    }

    public void setDescripcionVehiculo(String descripcionVehiculo) {
        this.descripcionVehiculo.set(descripcionVehiculo);
    }

    public StringProperty clienteProperty() {
        return new SimpleStringProperty(nombre.getValue() + " "+apellidos.getValue());
    }
}
