package Cochera.dao;

import Cochera.models.Vehiculo.Vehiculo;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoDAO extends CrudDAO<Vehiculo> {

    public static final String TABLA = "vehiculos";

    public VehiculoDAO() throws SQLException {
        super();
        tabla = TABLA;
        campos = new String[]{"potencia","marca","modelo","tipoID","concesionarioID"};
    }

    @Override
    public int create(Vehiculo vehiculo) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryInsert(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            ResultSet rs = super.executeInsert(pst, vehiculo).getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return 0;

        }
    }

    @Override
    public ObservableList<Vehiculo> read() {
        return null;
    }

    @Override
    public boolean update(Vehiculo vehiculo) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryUpdate())) {

            return super.executeUpdate(pst, vehiculo, vehiculo.getVehiculoID());

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean delete(Vehiculo vehiculo) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryDelete())) {

            pst.setInt(1,vehiculo.getVehiculoID());
            pst.executeUpdate();
            return true;

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return false;

        }
    }
}
