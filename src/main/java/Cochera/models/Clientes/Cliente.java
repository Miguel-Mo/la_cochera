package Cochera.models.Clientes;

import Cochera.dao.ClienteDAO;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class Cliente {

    private FloatProperty presupuesto;
    private StringProperty nombre,apellidos,telefono,dni,email;
    private int id;

    public Cliente() { }

    public Cliente(ResultSet rs) throws SQLException {

        String tabla = ClienteDAO.TABLA;

        presupuesto = new SimpleFloatProperty(rs.getFloat(tabla + ".presupuesto"));

        nombre = new SimpleStringProperty(rs.getString(tabla + ".nombre"));
        apellidos = new SimpleStringProperty(rs.getString(tabla + ".apellidos"));
        telefono = new SimpleStringProperty(rs.getString(tabla + ".telefono"));
        dni = new SimpleStringProperty(rs.getString(tabla + ".dni"));
        email = new SimpleStringProperty(rs.getString(tabla + ".email"));

        fechaRegistro = new SimpleObjectProperty<>(new Date(rs.getTimestamp(tabla + ".fechaRegistro").getTime()));

        id = rs.getInt(tabla + ".id");
    }

    public Cliente(HashMap<String,Object> datos) {

        presupuesto = new SimpleFloatProperty((Float) datos.get("presupuesto"));

        nombre = datos.containsKey("nombre") ?
                new SimpleStringProperty((String) datos.get("nombre")) : new SimpleStringProperty(null);
        apellidos = datos.containsKey("apellidos") ?
                new SimpleStringProperty((String) datos.get("apellidos")) : new SimpleStringProperty(null);
        telefono = datos.containsKey("telefono") ?
                new SimpleStringProperty((String) datos.get("telefono")) : new SimpleStringProperty(null);
        dni = datos.containsKey("dni") ?
                new SimpleStringProperty((String) datos.get("dni")) : new SimpleStringProperty(null);
        email = datos.containsKey("email") ?
                new SimpleStringProperty((String) datos.get("email")) : new SimpleStringProperty(null);

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

    protected ObjectProperty<Date> fechaRegistro;

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Cliente{" +
                "presupuesto=" + presupuesto +
                ", nombre=" + nombre +
                ", apellidos=" + apellidos +
                ", telefono=" + telefono +
                ", dni=" + dni +
                ", email=" + email +
                ", id=" + id +
                '}';
    }

    public StringProperty clienteProperty() {
        return new SimpleStringProperty(nombre.getValue() + " "+apellidos.getValue());

    }
}
