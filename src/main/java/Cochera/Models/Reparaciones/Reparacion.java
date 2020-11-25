package Cochera.Models.Reparaciones;

import Cochera.DAO.ReparacionesDAO;
import Cochera.Models.Clientes.Cliente;
import Cochera.Models.Modelo;
import Cochera.Models.Usuario.Mecanico;
import Cochera.Models.Vehiculo.Vehiculo;
import Cochera.Models.Vehiculo.VehiculoReparar;
import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;

public class Reparacion extends Modelo {

    private int id,mecanicoID,clienteID,vehiculoRepararID;

    private ObjectProperty<Time> tiempoEstimado,tiempoReal;

    private IntegerProperty presupuestoEstimado,presupuestoReal; //Todo cambiar a float en todos lados

    private StringProperty estado;


    Mecanico mecanico;
    Cliente cliente;
    VehiculoReparar vehiculoReparar;

    public static final String PENDIENTE="pendiente";
    public static final String ENPROCESO="en proceso";
    public static final String FINALIZADO="finalizado";

    public Reparacion(){  }

    public Reparacion(ResultSet rs) throws SQLException {

        String tabla= ReparacionesDAO.TABLA;

        id=rs.getInt(tabla+".id");

        mecanico=new Mecanico(rs);
        cliente=new Cliente(rs);
        vehiculoReparar=new VehiculoReparar(rs);
       /* mecanicoID=rs.getInt(tabla+".mecanicoID");
        clienteID=rs.getInt(tabla+".clienteID");
        vehiculoRepararID=rs.getInt(tabla+".vehiculoRepararID");*/

        tiempoEstimado= new SimpleObjectProperty<Time>(rs.getTime(tabla+".tiempoEstimado"));
        tiempoReal= new SimpleObjectProperty<Time>(rs.getTime(tabla+".tiempoReal"));

        estado=new SimpleStringProperty(rs.getString(tabla+".estado"));

        presupuestoEstimado=new SimpleIntegerProperty(rs.getInt(tabla+".presupuestoEstimado"));
        presupuestoReal=new SimpleIntegerProperty(rs.getInt(tabla+".presupuestoReal"));
    }



    public Time getTiempoEstimado() {
        return tiempoEstimado.get();
    }

    public ObjectProperty<Time> tiempoEstimadoProperty() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Time tiempoEstimado) {
        this.tiempoEstimado.set(tiempoEstimado);
    }

    public Time getTiempoReal() {
        return tiempoReal.get();
    }

    public ObjectProperty<Time> tiempoRealProperty() {
        return tiempoReal;
    }

    public void setTiempoReal(Time tiempoReal) {
        this.tiempoReal.set(tiempoReal);
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public VehiculoReparar getVehiculoReparar() {
        return vehiculoReparar;
    }

    public void setVehiculoReparar(VehiculoReparar vehiculoReparar) {
        this.vehiculoReparar = vehiculoReparar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMecanicoID() {
        return mecanicoID;
    }

    public void setMecanicoID(int mecanicoID) {
        this.mecanicoID = mecanicoID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getVehiculoRepararID() {
        return vehiculoRepararID;
    }

    public void setVehiculoRepararID(int vehiculoRepararID) {
        this.vehiculoRepararID = vehiculoRepararID;
    }


    public int getPresupuestoEstimado() {
        return presupuestoEstimado.get();
    }

    public IntegerProperty presupuestoEstimadoProperty() {
        return presupuestoEstimado;
    }

    public void setPresupuestoEstimado(int presupuestoEstimado) {
        this.presupuestoEstimado.set(presupuestoEstimado);
    }

    public int getPresupuestoReal() {
        return presupuestoReal.get();
    }

    public IntegerProperty presupuestoRealProperty() {
        return presupuestoReal;
    }

    public void setPresupuestoReal(int presupuestoReal) {
        this.presupuestoReal.set(presupuestoReal);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }
}

//guardar mecanico como clase
//guaradar vehiuclorepara como clase
//guaradar cliente como clase
