package Cochera.DAO;

import Cochera.Models.Vehiculo.TipoVehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoVehiculosDAO extends AbstractDAO<TipoVehiculo> {

    public static final String TABLA = "tipos_vehiculos";

    public TipoVehiculosDAO() throws SQLException {
        tabla = TABLA;
        campos = new String[]{"descripcion"};
    }


    public ObservableList<TipoVehiculo> read() {
        ObservableList<TipoVehiculo> tiposVehiculos = FXCollections.observableArrayList();

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelect())) {

            ResultSet rs = pst.executeQuery();
            while (rs.next())
                tiposVehiculos.add(new TipoVehiculo(rs));

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return tiposVehiculos;
    }




}
