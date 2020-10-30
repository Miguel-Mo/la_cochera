package Cochera.models.Vehiculo;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoVender extends Vehiculo {

    private FloatProperty precio;
    private BooleanProperty vendido, segundaMano;
    private StringProperty tiempoUsado, imagen; // En caso de que guardemos la ruta o codificada en base64
    private int vehiculoID;

    public VehiculoVender(ResultSet rs) throws SQLException {
        super(rs);
        precio = new SimpleFloatProperty(rs.getFloat("precio"));
        vendido = new SimpleBooleanProperty(rs.getBoolean("vendido"));
        segundaMano = new SimpleBooleanProperty(rs.getBoolean("es_segunda_mano"));
        tiempoUsado = new SimpleStringProperty(rs.getString("tiempo_usado"));
        imagen = new SimpleStringProperty(rs.getString("imagen"));
        vehiculoID = rs.getInt("vehiculos_id");
    }
}
