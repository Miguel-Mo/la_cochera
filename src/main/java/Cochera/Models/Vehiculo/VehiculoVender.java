package Cochera.Models.Vehiculo;

import Cochera.DAO.vehiculos.VehiculoVenderDAO;
import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoVender extends Vehiculo {

    private final FloatProperty precio;
    private final BooleanProperty vendido, segundaMano;
    private final StringProperty tiempoUsado;
    protected CombustibleVehiculo combustibleVehiculo;
    private int id, vehiculoID, combustibleID;
    private final IntegerProperty kmRecorridos;

    public VehiculoVender() {
        precio = new SimpleFloatProperty();

        vendido = new SimpleBooleanProperty();
        segundaMano = new SimpleBooleanProperty();

        tiempoUsado = new SimpleStringProperty();

        kmRecorridos= new SimpleIntegerProperty();
        combustibleVehiculo = new CombustibleVehiculo();
    }

    public VehiculoVender(ResultSet rs) throws SQLException {
        super(rs);

        String tabla = VehiculoVenderDAO.TABLA;

        precio = new SimpleFloatProperty(rs.getFloat(tabla + ".precio"));

        vendido = new SimpleBooleanProperty(rs.getBoolean(tabla + ".vendido"));
        segundaMano = new SimpleBooleanProperty(rs.getBoolean(tabla + ".segundaMano"));

        tiempoUsado = new SimpleStringProperty(rs.getString(tabla + ".tiempoUsado"));

        kmRecorridos = new SimpleIntegerProperty(rs.getInt(tabla + ".kmRecorridos"));

        id = rs.getInt(tabla + ".id");
        vehiculoID = rs.getInt(tabla + ".vehiculoID");

        combustibleVehiculo = new CombustibleVehiculo(rs);
        combustibleID = combustibleVehiculo.getId();
    }

    public int getCombustibleID() {
        return combustibleID;
    }

    public void setCombustibleID(int combustibleID) {
        this.combustibleID = combustibleID;
    }

    public CombustibleVehiculo getCombustibleVehiculo() {return combustibleVehiculo;}

    public void setCombustibleVehiculo(CombustibleVehiculo combustibleVehiculo) {
        this.combustibleID = combustibleVehiculo.getId();
        this.combustibleVehiculo = combustibleVehiculo;
    }

    public float getPrecio() {
        if (precio == null) return 0;
        return precio.get();
    }

    public int getKmRecorridos() {
        return kmRecorridos.get();
    }

    public IntegerProperty kmRecorridosProperty() {
        return kmRecorridos;
    }

    public void setKmRecorridos(int kmRecorridos) {
        this.kmRecorridos.set(kmRecorridos);
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
        return marca.get() + " " + modelo.get();
    }
}
