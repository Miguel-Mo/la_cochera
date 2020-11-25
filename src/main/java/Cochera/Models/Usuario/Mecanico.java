package Cochera.Models.Usuario;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mecanico extends Usuario {

    private BooleanProperty esJefe;
    private int usuarioID;
    public Mecanico(ResultSet datos) throws SQLException {
        super(datos);
        esJefe =new SimpleBooleanProperty(datos.getBoolean("esJefe"));
        usuarioID = datos.getInt("usuarioID");
    }

    public void setEsJefe(boolean esJefe) {
        this.esJefe.set(esJefe);
    }
}
