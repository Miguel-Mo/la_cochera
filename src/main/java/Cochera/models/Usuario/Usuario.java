package Cochera.models.Usuario;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Usuario {

    private StringProperty login, tipo, nombre, apellidos, dni, email, telefono;
    private IntegerProperty salario;
    private int id, concesionarioID;

    public static final String VENDEDOR = "vendedor";
    public static final String MECANICO = "mecanico";
    public static final String JEFE = "jefe";

    public Usuario(ResultSet datos) throws SQLException {
        id = datos.getInt("id");
        login = new SimpleStringProperty(datos.getString("login"));
        tipo = new SimpleStringProperty(datos.getString("tipo"));
        nombre = new SimpleStringProperty(datos.getString("nombre"));
        apellidos = new SimpleStringProperty(datos.getString("apellidos"));
        dni = new SimpleStringProperty(datos.getString("dni"));
        email = new SimpleStringProperty(datos.getString("email"));
        telefono = new SimpleStringProperty(datos.getString("telefono"));
        salario = new SimpleIntegerProperty(datos.getInt("salario"));
        concesionarioID = datos.getInt("concesionario_id");
    }

    public static Usuario obtener(ResultSet datos) throws SQLException {
        switch (datos.getString(4)) {
            case VENDEDOR: return new Vendedor(datos);
            case MECANICO: return new Mecanico(datos);
            case JEFE: return new Jefe(datos);
            default: return null;
        }
    }
}
