package Cochera.Models.Usuario;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Mecanico extends Usuario {

    private final BooleanProperty esJefe;
    private final int id, usuarioID;
    private List<String> especialidades;

    public Mecanico(ResultSet datos) throws SQLException {
        super(datos);

        esJefe =new SimpleBooleanProperty(datos.getBoolean("esJefe"));

        usuarioID = datos.getInt("mecanicos.usuarioID");
        id = datos.getInt("mecanicos.id");
    }

    public boolean isEsJefe() {
        return esJefe.get();
    }

    public BooleanProperty esJefeProperty() {
        return esJefe;
    }

    public void setEsJefe(boolean esJefe) {
        this.esJefe.set(esJefe);
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    @Override
    public String toString() {
        return nombre.get() + " " + apellidos.get();
    }
}
