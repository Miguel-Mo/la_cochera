package Cochera.Models.Vehiculo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CombustibleVehiculo {

    private int id;
    private StringProperty descripcion;

    private static final String TABLA = "combustible_vehiculos";

    public CombustibleVehiculo(ResultSet rs) throws SQLException {
        id = rs.getInt(TABLA + ".id");
        descripcion = new SimpleStringProperty(rs.getString(TABLA + ".descripcion"));
    }

    public CombustibleVehiculo() {
        descripcion = new SimpleStringProperty();
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion.getValue();
    }
}