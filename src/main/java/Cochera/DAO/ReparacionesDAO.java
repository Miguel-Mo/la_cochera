package Cochera.DAO;

import Cochera.Models.Clientes.Cliente;
import Cochera.Models.Reparaciones.Reparacion;
import Cochera.Models.Vehiculo.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReparacionesDAO extends AbstractDAO<Reparacion> implements Crud<Reparacion> {


    public static final String TABLA="reparaciones";

    public ReparacionesDAO() throws SQLException{
        tabla=TABLA;
        campos=new String[]{"mecanicoID","clienteID","vehiculoRepararID","tiempoEstimado","tiempoReal","presupuestoEstimado","presupuestoReal","componentes","fechaInicio","fechaFin","estado"};
    }


    public int create(Reparacion reparacion){
        try (PreparedStatement pst = conexion.prepareStatement(super.queryInsert(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            ResultSet rs = super.executeInsert(pst, reparacion).getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return 0;

        }

    }

    @Override
    public ObservableList<Reparacion> read() {
        ObservableList<Reparacion> reparaciones = FXCollections.observableArrayList();

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelect())) {

            ResultSet rs = pst.executeQuery();
            while (rs.next())
                reparaciones.add(new Reparacion(rs));

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return reparaciones;
    }

    @Override
    public Reparacion read(int id) {
        Reparacion reparacion = null;

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelectOne())) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                reparacion = new Reparacion(rs);
            }

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return reparacion;
    }

    public boolean update(Reparacion reparacion) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryUpdate())) {

            return super.executeUpdate(pst, reparacion, reparacion.getId());

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return false;

        }
    }


    public boolean delete(Reparacion reparacion) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryDelete())) {
            pst.setInt(1,reparacion.getId());
            pst.executeUpdate();
            return true;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return false;
        }
    }







}
