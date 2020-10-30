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
        id = datos.getInt(1);
        login = new SimpleStringProperty(datos.getString(2));
        tipo = new SimpleStringProperty(datos.getString(4));
        nombre = new SimpleStringProperty(datos.getString(5));
        apellidos = new SimpleStringProperty(datos.getString(6));
        dni = new SimpleStringProperty(datos.getString(7));
        email = new SimpleStringProperty(datos.getString(8));
        telefono = new SimpleStringProperty(datos.getString(9));
        salario = new SimpleIntegerProperty(datos.getInt(10));
        concesionarioID = datos.getInt(11);
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
