package Cochera.models.Vehiculo;

import Cochera.dao.VehiculoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoVehiculo {

    private int id;
    private StringProperty descripcion;

    private static final String TABLA = "tipos_vehiculos";

    public TipoVehiculo(ResultSet rs) throws SQLException {
        id = rs.getInt(TABLA + ".id");
        descripcion = new SimpleStringProperty(rs.getString(TABLA + ".descripcion"));
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
