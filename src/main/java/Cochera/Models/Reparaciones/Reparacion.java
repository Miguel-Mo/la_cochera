package Cochera.Models.Reparaciones;

import Cochera.DAO.ReparacionesDAO;
import Cochera.Models.Clientes.Cliente;
import Cochera.Models.Modelo;
import Cochera.Models.Usuario.Mecanico;
import Cochera.Models.Vehiculo.Vehiculo;
import Cochera.Models.Vehiculo.VehiculoReparar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;

public class Reparacion extends Modelo {

    private int id,mecanicoID,clienteID,vehiculoRepararID;

    private Time tiempoEstimado,tiempoReal;

    private IntegerProperty presupuestoEstimado,presupuestoReal; //Todo cambiar a float en todos lados


    Mecanico mecanico;
    Cliente cliente;
    VehiculoReparar vehiculoReparar;

    public static final String PENDIENTE="Pendiente";
    public static final String ENPROCESO="En proceso";
    public static final String FINALIZADO="Finalizado";

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


        tiempoEstimado=rs.getTime(tabla+".tiempoEstimado");
        tiempoReal=rs.getTime(tabla+".tiempoReal");

        presupuestoEstimado=new SimpleIntegerProperty(rs.getInt(tabla+".presupuestoEstimado"));
        presupuestoReal=new SimpleIntegerProperty(rs.getInt(tabla+".presupuestoReal"));

    }

    public Reparacion(HashMap<String,String> datos){

        tiempoEstimado= new Time(datos.get("tiempoEstimado"));;
        tiempoReal=new Time(datos.get("tiempoReal"));

        presupuestoEstimado=new SimpleIntegerProperty(Integer.parseInt(datos.get("clienteID")) );
        //new SimpleFloatProperty(Float.parseFloat(datos.get("presupuesto")));
        presupuestoReal=new SimpleIntegerProperty(Integer.parseInt(datos.get("presupuesto")));


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

    public Time getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Time tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public Time getTiempoReal() {
        return tiempoReal;
    }

    public void setTiempoReal(Time tiempoReal) {
        this.tiempoReal = tiempoReal;
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

}

//guardar mecanico como clase
//guaradar vehiuclorepara como clase
//guaradar cliente como clase
