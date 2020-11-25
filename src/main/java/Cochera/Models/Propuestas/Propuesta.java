package Cochera.Models.Propuestas;

import Cochera.DAO.PropuestaDAO;
import Cochera.Models.Modelo;
import javafx.beans.property.*;
import Cochera.Utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Propuesta extends Modelo {

//"cocheVendido","fechaLimite","cliente","precio","status"
    private FloatProperty presupuesto;
    private ObjectProperty<Date> fechaLimite,fechaInicio,fechaFin;
    private StringProperty cliente,status;
    private IntegerProperty clienteID,vendedorID,vehiculoVenderID;
    private int id;




    public Propuesta(){    }

    public Propuesta(ResultSet rs) throws SQLException {

        String tabla = PropuestaDAO.TABLA;

        presupuesto = new SimpleFloatProperty(rs.getFloat(tabla + ".presupuesto"));
        fechaLimite = new SimpleObjectProperty<>(new Date(rs.getTimestamp(tabla + ".fechaLimite").getTime()));
        fechaInicio = new SimpleObjectProperty<>(new Date(rs.getTimestamp(tabla + ".fechaInicio").getTime()));
        fechaFin = new SimpleObjectProperty<>(new Date(rs.getTimestamp(tabla + ".fechaFin").getTime()));
        cliente = new SimpleStringProperty(rs.getString(tabla + ".cliente"));
        status = new SimpleStringProperty(rs.getString(tabla + ".status"));
        clienteID = new SimpleIntegerProperty(rs.getInt(tabla + ".clienteID"));
        vendedorID = new SimpleIntegerProperty(rs.getInt(tabla + ".vendedorID"));
        vehiculoVenderID = new SimpleIntegerProperty(rs.getInt(tabla + ".vehiculoVenderID"));
    }

    public Propuesta(HashMap<String,String> datos){

        presupuesto = new SimpleFloatProperty(Float.parseFloat(datos.get(".presupuesto")));
        fechaFin = new SimpleObjectProperty<>(Conversor.stringToDate(datos.get(".fechaFin")));
        fechaLimite = new SimpleObjectProperty<>(Conversor.stringToDate(datos.get(".fechaLimite")));
        cliente = new SimpleStringProperty(datos.get(".cliente") );
        status = new SimpleStringProperty(datos.get(".estado"));
        clienteID = new SimpleIntegerProperty(Integer.parseInt(datos.get("clienteID")) );
        vendedorID = new SimpleIntegerProperty(Integer.parseInt( datos.get("vendedorID")));
        vehiculoVenderID = new SimpleIntegerProperty(Integer.parseInt( datos.get("vehiculoVenderID")));
    }

    public float getPresupuesto() {
        return presupuesto.get();
    }

    public FloatProperty presupuestoProperty() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto.set(presupuesto);
    }

    public Date getFechaLimite() {
        return fechaLimite.get();
    }

    public ObjectProperty<Date> fechaLimiteProperty() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite.set(fechaLimite);
    }

    public Date getFechaInicio() {
        return fechaInicio.get();
    }

    public ObjectProperty<Date> fechaInicioProperty() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio.set(fechaInicio);
    }

    public Date getFechaFin() {
        return fechaFin.get();
    }

    public ObjectProperty<Date> fechaFinProperty() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin.set(fechaFin);
    }

    public String getCliente() {
        return cliente.get();
    }

    public StringProperty clienteProperty() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public int getClienteID() {
        return clienteID.get();
    }

    public IntegerProperty clienteIDProperty() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID.set(clienteID);
    }

    public int getVendedorID() {
        return vendedorID.get();
    }

    public IntegerProperty vendedorIDProperty() {
        return vendedorID;
    }

    public void setVendedorID(int vendedorID) {
        this.vendedorID.set(vendedorID);
    }

    public int getVehiculoVenderID() {
        return vehiculoVenderID.get();
    }

    public IntegerProperty vehiculoVenderIDProperty() {
        return vehiculoVenderID;
    }

    public void setVehiculoVenderID(int vehiculoVenderID) {
        this.vehiculoVenderID.set(vehiculoVenderID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
