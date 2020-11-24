package Cochera.Models.Vehiculo;

import Cochera.DAO.VehiculoDAO;
import Cochera.Models.Modelo;
import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public abstract class Vehiculo extends Modelo {

    protected StringProperty potencia, marca, modelo;
    protected ObjectProperty<Date> fechaRegistro;
    protected IntegerProperty vehiculoID, concesionarioID;
    protected TipoVehiculo tipoVehiculo;
    protected int tipoID;

    public Vehiculo() {
        potencia = new SimpleStringProperty();
        marca = new SimpleStringProperty();
        modelo = new SimpleStringProperty();

        fechaRegistro = new SimpleObjectProperty<>();

        vehiculoID = new SimpleIntegerProperty();
        concesionarioID = new SimpleIntegerProperty();

        tipoVehiculo = new TipoVehiculo();
    }

    public Vehiculo(ResultSet rs) throws SQLException {
        String tabla = VehiculoDAO.TABLA;

        vehiculoID = new SimpleIntegerProperty(rs.getInt(tabla + ".id"));
        concesionarioID = new SimpleIntegerProperty(rs.getInt(tabla + ".concesionarioID"));

        tipoVehiculo = new TipoVehiculo(rs);
        tipoID = tipoVehiculo.getId();

        potencia = new SimpleStringProperty(rs.getString(tabla + ".potencia"));
        marca = new SimpleStringProperty(rs.getString(tabla + ".marca"));
        modelo = new SimpleStringProperty(rs.getString(tabla + ".modelo"));
        fechaRegistro = new SimpleObjectProperty<>(new Date(rs.getTimestamp(tabla + ".fechaRegistro").getTime()));
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

    public Date getFechaRegistro() {
        return fechaRegistro.get();
    }

    public ObjectProperty<Date> fechaRegistroProperty() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
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
        this.tipoID = tipoVehiculo.getId();
    }
}
