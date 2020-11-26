package Cochera.DAO.vehiculos;

import Cochera.DAO.base.AbstractDAO;
import Cochera.Models.Vehiculo.VehiculoReparar;
import Cochera.Models.Vehiculo.VehiculoVender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoRepararDAO extends AbstractDAO<VehiculoReparar> {

    public static final String TABLA = "vehiculos_reparar";

    public VehiculoRepararDAO() throws SQLException {
        tabla = TABLA;
        campos = new String[]{"descripcion","matricula","vehiculoID"};
        relaciones.put("vehiculos", "vehiculoID");
    }

    public int create(VehiculoReparar vehiculo) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryInsert(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            try (VehiculoDAO dao = new VehiculoDAO()) {

                // Creamos primero un Vehiculo en la tabla vehiculos
                int vehiculoID = dao.create(vehiculo);

                if (vehiculoID == 0) return 0; // Comprobamos que se ha creado el registro adecuadamente

                // Creamos en vehiculos_vender con FK el id obtenido del registro de vehiculos que acabamos de crear
                vehiculo.setVehiculoID(vehiculoID);
                ResultSet rs = super.executeInsert(pst, vehiculo).getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public VehiculoReparar read(int id) {
        VehiculoReparar vehiculo = null;

        String sql = "SELECT vehiculos_reparar.*, vehiculos.*, tipos_vehiculos.* " +
                "FROM vehiculos_reparar \n" +
                "LEFT JOIN vehiculos  ON vehiculos_reparar.vehiculoID = vehiculos.id \n" +
                "LEFT JOIN tipos_vehiculos  ON vehiculos.tipoID = tipos_vehiculos.id WHERE vehiculos_reparar.id=?";

        try (PreparedStatement pst = conexion.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                vehiculo = new VehiculoReparar(rs);
            }

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return vehiculo;
    }
}
