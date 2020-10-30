package Cochera.models.Vehiculo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class Vehiculo {

    private StringProperty potencia, marca, modelo, matricula;
    private Timestamp fechaRegistro;
    private int id, tipoVehiculoID, concesionarioID;

    public Vehiculo(ResultSet rs) throws SQLException {
        potencia = new SimpleStringProperty(rs.getString("potencia"));
        marca = new SimpleStringProperty(rs.getString("marca"));
        modelo = new SimpleStringProperty(rs.getString("modelo"));
        matricula = new SimpleStringProperty(rs.getString("matricula"));

        fechaRegistro = rs.getTimestamp("fecha_registro");
        id = rs.getInt("id");
        tipoVehiculoID = rs.getInt("tipos_vehiculos_id");
        concesionarioID = rs.getInt("concesionarios_id");
    }

}
