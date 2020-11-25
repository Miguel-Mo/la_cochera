package Cochera.Models.Propuestas;

import Cochera.DAO.PropuestaDAO;
import Cochera.Models.Clientes.Cliente;
import Cochera.Models.Modelo;
import Cochera.Models.Usuario.Vendedor;
import Cochera.Models.Vehiculo.VehiculoVender;
import javafx.beans.property.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Propuesta extends Modelo {

    private final FloatProperty presupuesto;
    private final ObjectProperty<Date> fechaLimite,fechaInicio,fechaFin;
    private final StringProperty estado;
    private final IntegerProperty clienteID,vendedorID,vehiculoVenderID;

    private VehiculoVender vehiculoVender;
    private Cliente cliente;
    private Vendedor vendedor;

    private int id;

    public static final String PENDIENTE = "pendiente";
    public static final String ACEPTADA = "pendiente";
    public static final String RECHAZADA = "pendiente";

    public Propuesta() {

        presupuesto = new SimpleFloatProperty();

        fechaLimite = new SimpleObjectProperty<>();
        fechaInicio = new SimpleObjectProperty<>();
        fechaFin = new SimpleObjectProperty<>();

        estado = new SimpleStringProperty();

        clienteID = new SimpleIntegerProperty();
        vendedorID = new SimpleIntegerProperty();
        vehiculoVenderID = new SimpleIntegerProperty();

    }

    public Propuesta(ResultSet rs) throws SQLException {

        String tabla = PropuestaDAO.TABLA;

        presupuesto = new SimpleFloatProperty(rs.getFloat(tabla + ".presupuesto"));

        fechaLimite = new SimpleObjectProperty<>(new Date(rs.getTimestamp(tabla + ".fechaLimite").getTime()));
        fechaInicio = new SimpleObjectProperty<>(new Date(rs.getTimestamp(tabla + ".fechaInicio").getTime()));

        Timestamp tiempo = rs.getTimestamp(tabla + ".fechaFin");
        fechaFin = tiempo == null ? new SimpleObjectProperty<>():
            new SimpleObjectProperty<>(new Date(tiempo.getTime()));

        estado = new SimpleStringProperty(rs.getString(tabla + ".estado"));

        clienteID = new SimpleIntegerProperty(rs.getInt(tabla + ".clienteID"));
        vendedorID = new SimpleIntegerProperty(rs.getInt(tabla + ".vendedorID"));
        vehiculoVenderID = new SimpleIntegerProperty(rs.getInt(tabla + ".vehiculoVenderID"));

        cliente = new Cliente(rs);
        vendedor = new Vendedor(rs);
        vehiculoVender = new VehiculoVender(rs);

        id = rs.getInt(tabla + ".id");

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

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
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

    public VehiculoVender getVehiculoVender() {
        return vehiculoVender;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
}
