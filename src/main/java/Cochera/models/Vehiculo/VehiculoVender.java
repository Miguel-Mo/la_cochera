package Cochera.models.Vehiculo;

import Cochera.dao.VehiculoVenderDAO;
import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class VehiculoVender extends Vehiculo {

    private FloatProperty precio;
    private BooleanProperty vendido, segundaMano;
    private StringProperty tiempoUsado, imagen; // En caso de que guardemos la ruta o codificada en base64
    private int id, vehiculoID;

    public VehiculoVender() { }

    public VehiculoVender(ResultSet rs) throws SQLException {
        super(rs);

        String tabla = VehiculoVenderDAO.TABLA;

        precio = new SimpleFloatProperty(rs.getFloat(tabla + ".precio"));

        vendido = new SimpleBooleanProperty(rs.getBoolean(tabla + ".vendido"));
        segundaMano = new SimpleBooleanProperty(rs.getBoolean(tabla + ".segundaMano"));

        tiempoUsado = new SimpleStringProperty(rs.getString(tabla + ".tiempoUsado"));
        imagen = new SimpleStringProperty(rs.getString(tabla + ".imagen"));

        id = rs.getInt(tabla + ".id");
        vehiculoID = rs.getInt(tabla + ".vehiculoID");
    }

    public VehiculoVender(HashMap<String,Object> datos) {
        super(datos);

        precio = new SimpleFloatProperty((Float) datos.get("precio"));

        vendido = datos.containsKey("vendido") ?
                new SimpleBooleanProperty((Boolean) datos.get("vendido")) : new SimpleBooleanProperty(false);
        segundaMano = datos.containsKey("vendido") ?
                new SimpleBooleanProperty((Boolean) datos.get("segundaMano")) : new SimpleBooleanProperty(false);

        tiempoUsado = datos.containsKey("tiempoUsado") ?
                new SimpleStringProperty((String) datos.get("tiempoUsado")) : new SimpleStringProperty(null);
        imagen = datos.containsKey("imagen") ?
                new SimpleStringProperty((String) datos.get("imagen")) : new SimpleStringProperty(null);
    }

    public float getPrecio() {
        return precio.get();
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    public boolean isVendido() {
        return vendido.get();
    }

    public BooleanProperty vendidoProperty() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido.set(vendido);
    }

    public boolean isSegundaMano() {
        return segundaMano.get();
    }

    public BooleanProperty segundaManoProperty() {
        return segundaMano;
    }

    public void setSegundaMano(boolean segundaMano) {
        this.segundaMano.set(segundaMano);
    }

    public String getTiempoUsado() {
        return tiempoUsado.get();
    }

    public StringProperty tiempoUsadoProperty() {
        return tiempoUsado;
    }

    public void setTiempoUsado(String tiempoUsado) {
        this.tiempoUsado.set(tiempoUsado);
    }

    public String getImagen() {
        return imagen.get();
    }

    public StringProperty imagenProperty() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen.set(imagen);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehiculoID() {
        return vehiculoID;
    }

    public void setVehiculoID(int vehiculoID) {
        this.vehiculoID = vehiculoID;
    }

    @Override
    public String toString() {
        return "VehiculoVender{" +
                "precio=" + precio.getValue() +
                ", vendido=" + vendido.getValue() +
                ", segundaMano=" + segundaMano.getValue() +
                ", tiempoUsado=" + tiempoUsado.getValue() +
                ", imagen=" + imagen.getValue() +
                ", id=" + id +
                ", vehiculoID=" + vehiculoID +
                ", vehiculoID=" + vehiculoID +
                ", concesionarioID=" + concesionarioID +
                ", potencia=" + potencia.getValue() +
                ", marca=" + marca.getValue() +
                ", modelo=" + modelo.getValue() +
                ", fechaRegistro=" + fechaRegistro.toString() +
                '}';
    }
}
