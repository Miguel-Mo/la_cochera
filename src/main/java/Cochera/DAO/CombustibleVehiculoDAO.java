package Cochera.DAO;

import Cochera.Models.Vehiculo.CombustibleVehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CombustibleVehiculoDAO extends AbstractDAO<CombustibleVehiculo> {

    public static final String TABLA = "combustible_vehiculos";

    public CombustibleVehiculoDAO() throws SQLException {
        tabla = TABLA;
        campos = new String[]{"descripcion"};
    }


    public ObservableList<CombustibleVehiculo> read() {
        ObservableList<CombustibleVehiculo> combustibleVehiculos = FXCollections.observableArrayList();

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelect())) {

            ResultSet rs = pst.executeQuery();
            while (rs.next())
                combustibleVehiculos.add(new CombustibleVehiculo(rs));

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return combustibleVehiculos;
    }




}