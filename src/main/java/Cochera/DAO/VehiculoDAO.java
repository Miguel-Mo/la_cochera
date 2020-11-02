package Cochera.dao;

import Cochera.models.Vehiculo.Vehiculo;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehiculoDAO extends CrudDAO<Vehiculo> {

    public static final String TABLA = "vehiculos";

    public VehiculoDAO() throws SQLException {
        super(TABLA);
        campos = new String[]{"potencia","marca","modelo","tipoID","concesionarioID"};
    }

    @Override
    public int create(Vehiculo vehiculo) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryInsert(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            return super.executeInsert(pst, vehiculo);
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
        return false;
    }

    @Override
    public boolean delete(Vehiculo vehiculo) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryDelete(vehiculo.getVehiculoID()))) {
            pst.executeUpdate();
            return true;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
