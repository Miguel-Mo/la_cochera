package Cochera.models.Vehiculo;

import Cochera.dao.VehiculoDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

public abstract class Vehiculo {

    protected StringProperty potencia, marca, modelo, fechaRegistro;
    protected IntegerProperty vehiculoID, concesionarioID;
    protected TipoVehiculo tipoVehiculo;

    public Vehiculo() {  }

    public Vehiculo(ResultSet rs) throws SQLException {
        String tabla = VehiculoDAO.TABLA;

        vehiculoID = new SimpleIntegerProperty(rs.getInt(tabla + ".id"));
        concesionarioID = new SimpleIntegerProperty(rs.getInt(tabla + ".concesionarioID"));

        tipoVehiculo = new TipoVehiculo(rs);

        potencia = new SimpleStringProperty(rs.getString(tabla + ".potencia"));
        marca = new SimpleStringProperty(rs.getString(tabla + ".marca"));
        modelo = new SimpleStringProperty(rs.getString(tabla + ".modelo"));
        fechaRegistro = new SimpleStringProperty(rs.getTimestamp(tabla + ".fechaRegistro").toLocalDateTime().toString());
    }

    public Vehiculo(HashMap<String,Object> datos) {
        tipoVehiculo = (TipoVehiculo) datos.get("tipo");

        concesionarioID = new SimpleIntegerProperty((int) datos.get("concesionarioID"));

        potencia = new SimpleStringProperty((String) datos.get("potencia"));
        marca = new SimpleStringProperty((String) datos.get("marca"));
        modelo = new SimpleStringProperty((String) datos.get("modelo"));
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getPotencia() {
        return potencia.get();
    }

    public StringProperty potenciaProperty() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia.set(potencia);
    }

    public String getMarca() {
        return marca.get();
    }

    public StringProperty marcaProperty() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getModelo() {
        return modelo.get();
    }

    public StringProperty modeloProperty() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getFechaRegistro() {
        return fechaRegistro.get();
    }

    public StringProperty fechaRegistroProperty() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro.set(fechaRegistro);
    }

    public int getVehiculoID() {
        return vehiculoID.get();
    }

    public IntegerProperty vehiculoIDProperty() {
        return vehiculoID;
    }

    public void setVehiculoID(int vehiculoID) {
        this.vehiculoID.set(vehiculoID);
    }

    public int getConcesionarioID() {
        return concesionarioID.get();
    }

    public IntegerProperty concesionarioIDProperty() {
        return concesionarioID;
    }

    public void setConcesionarioID(int concesionarioID) {
        this.concesionarioID.set(concesionarioID);
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
}
