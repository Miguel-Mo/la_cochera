package Cochera.models.Vehiculo;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoReparar extends Vehiculo{

    private StringProperty descripcion;
    private int vehiculoID;

    public VehiculoReparar(ResultSet rs) throws SQLException {
        super(rs);
        descripcion = new SimpleStringProperty(rs.getString("descripcion"));
        vehiculoID = rs.getInt("vehiculos_id");
    }
}
