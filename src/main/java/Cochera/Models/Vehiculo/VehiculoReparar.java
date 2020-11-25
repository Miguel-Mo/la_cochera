package Cochera.Models.Vehiculo;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoReparar extends Vehiculo{

    private final StringProperty descripcion;
    private final int id;
    private final int vehiculoID;

    public VehiculoReparar(ResultSet rs) throws SQLException {
        super(rs);
        String tabla="vehiculos_reparar";
        descripcion = new SimpleStringProperty(rs.getString(tabla+ ".descripcion"));
        vehiculoID = rs.getInt(tabla+ ".vehiculoId");
        id = rs.getInt(tabla+ ".id");
    }

    @Override
    public int getId() {
        return id;
    }
}
