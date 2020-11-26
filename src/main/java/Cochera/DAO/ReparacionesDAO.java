package Cochera.DAO;

import Cochera.DAO.base.AbstractDAO;
import Cochera.DAO.base.Crud;
import Cochera.Models.Reparaciones.Reparacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReparacionesDAO extends AbstractDAO<Reparacion> implements Crud<Reparacion> {


    public static final String TABLA="reparaciones";

    public ReparacionesDAO() throws SQLException{
        tabla=TABLA;
        campos=new String[]{"mecanicoID","clienteID","vehiculoRepararID","tiempoEstimado","tiempoReal","presupuestoEstimado","presupuestoReal","componentes","estado"};
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

        final String SQL="SELECT reparaciones.*,cliente.*,mecanicos.*,vehiculos_reparar.*,usuarios.*,vehiculos.*,tipos_vehiculos.* \n" +
                " FROM reparaciones\n" +
                " LEFT JOIN cliente on reparaciones.clienteID=cliente.id\n" +
                " LEFT JOIN mecanicos on reparaciones.mecanicoID=mecanicos.id\n" +
                " LEFT JOIN vehiculos_reparar on reparaciones.vehiculoRepararID=vehiculos_reparar.id\n" +
                " LEFT JOIN usuarios on mecanicos.usuarioID=usuarios.id\n" +
                " LEFT JOIN vehiculos on vehiculos_reparar.vehiculoID=vehiculos.id\n" +
                " LEFT JOIN tipos_vehiculos on vehiculos.tipoID=tipos_vehiculos.id";

        ObservableList<Reparacion> reparaciones = FXCollections.observableArrayList();

        try (PreparedStatement pst = conexion.prepareStatement(SQL)) {

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

        final String SQL="SELECT reparaciones.*,cliente.*,mecanicos.*,vehiculos_reparar.*,usuarios.*,vehiculos.*,tipos_vehiculos.* \n" +
                "                FROM reparaciones\n" +
                "                left join cliente on reparaciones.clienteID=cliente.id\n" +
                "                left join mecanicos on reparaciones.mecanicoID=mecanicos.id\n" +
                "                left join vehiculos_reparar on reparaciones.vehiculoRepararID=vehiculos_reparar.id\n" +
                "                left join usuarios on mecanicos.usuarioID=usuarios.id\n" +
                "                left join vehiculos on vehiculos_reparar.vehiculoID=vehiculos.id\n" +
                "                left join tipos_vehiculos on vehiculos.tipoID=tipos_vehiculos.id where reparaciones.id=?";

        try (PreparedStatement pst = conexion.prepareStatement(SQL)) {

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

    public void comenzarTrabajo(Reparacion reparacion) {

        final String SQL = "UPDATE reparaciones R SET R.fechaInicio = CURRENT_TIMESTAMP()," +
                "R.estado='proceso' WHERE R.id = ?";

        try (PreparedStatement pst = conexion.prepareStatement(SQL)) {
            pst.setInt(1,reparacion.getId());
            pst.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public void finalizarTrabajo(Reparacion reparacion) {

        final String SQL = "UPDATE reparaciones R SET R.fechaFin = CURRENT_TIMESTAMP(), \n" +
                "R.estado='finalizado', R.tiempoReal = TIMEDIFF(R.fechaFin,R.fechaInicio) WHERE R.id = ?";

        try (PreparedStatement pst = conexion.prepareStatement(SQL)) {
            pst.setInt(1,reparacion.getId());
            pst.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
