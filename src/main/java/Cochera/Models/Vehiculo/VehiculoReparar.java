package Cochera.Models.Vehiculo;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoReparar extends Vehiculo {

    private final StringProperty descripcion, matricula;
    private int id;
    private int vehiculoID;

    public VehiculoReparar() {
        super();
        descripcion = new SimpleStringProperty();
        matricula = new SimpleStringProperty();
    }


    public VehiculoReparar(ResultSet rs) throws SQLException {
        super(rs);
        String tabla="vehiculos_reparar";

        descripcion = new SimpleStringProperty(rs.getString(tabla+ ".descripcion"));
        matricula = new SimpleStringProperty(rs.getString(tabla+ ".matricula"));

        vehiculoID = rs.getInt(tabla+ ".vehiculoId");
        id = rs.getInt(tabla+ ".id");
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setVehiculoID(int vehiculoID) {
        this.vehiculoID = vehiculoID;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public String getMatricula() {
        return matricula.get();
    }

    public StringProperty matriculaProperty() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula.set(matricula);
    }

    @Override
    public int getVehiculoID() {
        return vehiculoID;
    }

    @Override
    public int getId() {
        return id;
    }
}
