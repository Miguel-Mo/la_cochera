package Cochera.dao;

import Cochera.models.Vehiculo.VehiculoVender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoVenderDAO extends CrudDAO<VehiculoVender> {

    public static final String TABLA = "vehiculos_vender";

    public VehiculoVenderDAO() throws SQLException {
        super();
        tabla = TABLA;
        campos = new String[]{"precio","vendido","segundaMano","tiempoUsado","imagen","vehiculoID"};
        relaciones.put("vehiculos", "vehiculoID");
    }


    @Override
    public int create(VehiculoVender vehiculo) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryInsert(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            try (VehiculoDAO dao = new VehiculoDAO()) {

                // Creamos primero un Vehiculo en la tabla vehiculos
                int vehiculoID = dao.create(vehiculo);

                assert vehiculoID != 0; // Comprobamos que se ha creado el registro adecuadamente

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

    @Override
    public ObservableList<VehiculoVender> read() {
        ObservableList<VehiculoVender> vehiculos = FXCollections.observableArrayList();

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelect(LEFT_JOIN,"vehiculos"))) {

            ResultSet rs = pst.executeQuery();
            while (rs.next())
                vehiculos.add(new VehiculoVender(rs));

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return vehiculos;
    }

    public VehiculoVender read(int id) {
        VehiculoVender vehiculo = null;

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelectOne(LEFT_JOIN,"vehiculos"))) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                vehiculo = new VehiculoVender(rs);
            }

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return vehiculo;
    }

    @Override
    public boolean update(VehiculoVender vehiculo) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryUpdate())) {

            try (VehiculoDAO dao = new VehiculoDAO()) {
                if (!dao.update(vehiculo)) return false;
                return super.executeUpdate(pst,vehiculo,vehiculo.getId());
            }

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean delete(VehiculoVender vehiculo) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryDelete())) {

            pst.setInt(1, vehiculo.getId());
            pst.executeUpdate(); // Eliminamos de la tabla vehiculos_vender

            try(VehiculoDAO dao = new VehiculoDAO()) {
                dao.delete(vehiculo); // Eliminamos de la tabla vehiculos
            }

            return true;

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return false;

        }
    }
}