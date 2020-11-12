package Cochera.Models.Concesionarios;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Concesionario {

    private StringProperty ciudad, provincia,direccion,cp;
    private int id;

    private final String TABLA="Concesionarios";

    public Concesionario() { }

    public Concesionario(ResultSet rs) throws SQLException {

        ciudad = new SimpleStringProperty(rs.getString(TABLA + ".ciudad"));
        provincia = new SimpleStringProperty(rs.getString(TABLA + ".provincia"));
        direccion = new SimpleStringProperty(rs.getString(TABLA + ".direccion"));
        cp = new SimpleStringProperty(rs.getString(TABLA + ".cp"));

        id = rs.getInt(TABLA + ".id");
    }

    public Concesionario(HashMap<String,Object> datos) {

        ciudad = datos.containsKey("ciudad") ?
                new SimpleStringProperty((String) datos.get("ciudad")) : new SimpleStringProperty(null);
        provincia = datos.containsKey("provincia") ?
                new SimpleStringProperty((String) datos.get("provincia")) : new SimpleStringProperty(null);
        direccion = datos.containsKey("direccion") ?
                new SimpleStringProperty((String) datos.get("direccion")) : new SimpleStringProperty(null);
        cp = datos.containsKey("cp") ?
                new SimpleStringProperty((String) datos.get("cp")) : new SimpleStringProperty(null);
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public String getProvincia() {
        return provincia.get();
    }

    public StringProperty provinciaProperty() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia.set(provincia);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getCp() {
        return cp.get();
    }

    public StringProperty cpProperty() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp.set(cp);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTABLA() {
        return TABLA;
    }

    @Override
    public String toString() {
        return ciudad.getValue();
    }
}
