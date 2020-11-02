package Cochera.models.Vehiculo;

import Cochera.dao.VehiculoDAO;
import Cochera.dao.VehiculoVenderDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

public abstract class Vehiculo {


    protected int vehiculoID, tipoID, concesionarioID;
    public StringProperty potencia, marca, modelo;
    protected Timestamp fechaRegistro;

    public Vehiculo() {  }

    public Vehiculo(ResultSet rs) throws SQLException {
        String tabla = VehiculoDAO.TABLA;

        vehiculoID = rs.getInt(tabla + ".id");
        tipoID = rs.getInt(tabla + ".tipoID");
        concesionarioID = rs.getInt(tabla + ".concesionarioID");

        potencia = new SimpleStringProperty(rs.getString(tabla + ".potencia"));
        marca = new SimpleStringProperty(rs.getString(tabla + ".marca"));
        modelo = new SimpleStringProperty(rs.getString(tabla + ".modelo"));

        fechaRegistro = rs.getTimestamp(tabla + ".fechaRegistro");
    }

    public Vehiculo(HashMap<String,Object> datos) {

        tipoID = (int) datos.get("tipoID");
        concesionarioID = (int) datos.get("concesionarioID");

        potencia = new SimpleStringProperty((String) datos.get("potencia"));
        marca = new SimpleStringProperty((String) datos.get("marca"));
        modelo = new SimpleStringProperty((String) datos.get("modelo"));
    }

    public int getVehiculoID() {
        return vehiculoID;
    }

    public void setVehiculoID(int vehiculoID) {
        this.vehiculoID = vehiculoID;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getTipoID() {
        return tipoID;
    }

    public void setTipoID(int tipoID) {
        this.tipoID = tipoID;
    }

    public int getConcesionarioID() {
        return concesionarioID;
    }

    public void setConcesionarioID(int concesionarioID) {
        this.concesionarioID = concesionarioID;
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

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }
}
