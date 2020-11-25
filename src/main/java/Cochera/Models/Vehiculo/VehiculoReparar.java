package Cochera.Models.Vehiculo;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoReparar extends Vehiculo{

    private StringProperty descripcion;
    private int vehiculoID;

    public VehiculoReparar(ResultSet rs) throws SQLException {
        super(rs);
        String tabla="vehiculos_reparar";
        descripcion = new SimpleStringProperty(rs.getString(tabla+ ".descripcion"));
        vehiculoID = rs.getInt(tabla+ ".vehiculoId");
    }
}
