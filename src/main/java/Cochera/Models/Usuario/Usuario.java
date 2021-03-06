package Cochera.Models.Usuario;

import Cochera.Models.Modelo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Usuario extends Modelo {

    protected final StringProperty login, tipo, nombre, apellidos, dni, email, telefono;
    protected final IntegerProperty salario;
    protected int concesionarioID;
    private int id;

    public static final String VENDEDOR = "vendedor";
    public static final String MECANICO = "mecanico";
    public static final String JEFE = "jefe";

    public Usuario(ResultSet datos) throws SQLException {

        id = datos.getInt("id");
        concesionarioID = datos.getInt("concesionarioID");

        login = new SimpleStringProperty(datos.getString("usuarios.login"));
        tipo = new SimpleStringProperty(datos.getString("usuarios.tipo"));
        nombre = new SimpleStringProperty(datos.getString("usuarios.nombre"));
        apellidos = new SimpleStringProperty(datos.getString("usuarios.apellidos"));
        dni = new SimpleStringProperty(datos.getString("usuarios.dni"));
        email = new SimpleStringProperty(datos.getString("usuarios.email"));
        telefono = new SimpleStringProperty(datos.getString("usuarios.telefono"));

        salario = new SimpleIntegerProperty(datos.getInt("usuarios.salario"));

    }

    public static Usuario obtener(ResultSet datos) throws SQLException {
        switch (datos.getString("tipo")) {
            case VENDEDOR: return new Vendedor(datos);
            case MECANICO: return new Mecanico(datos);
            case JEFE: return new Jefe(datos);
            default: return null;
        }
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getTipo() {
        return tipo.get();
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
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

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public int getSalario() {
        return salario.get();
    }

    public IntegerProperty salarioProperty() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario.set(salario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConcesionarioID() {
        return concesionarioID;
    }

    public void setConcesionarioID(int concesionarioID) {
        this.concesionarioID = concesionarioID;
    }

    public String getNombreCompleto() {
        return nombre.get() + " " + apellidos.get();
    }

    public SimpleStringProperty nombreCompletoProperty() {
        return new SimpleStringProperty(nombre.get() + " " + apellidos.get());
    }
}
