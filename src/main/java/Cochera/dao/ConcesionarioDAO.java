package Cochera.dao;

import Cochera.models.Concesionarios.Concesionario;
import Cochera.models.Vehiculo.TipoVehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConcesionarioDAO extends AbstractDAO<Concesionario> {

    public static final String TABLA = "concesionarios";

    public ConcesionarioDAO() throws SQLException {
        tabla = TABLA;
        campos = new String[]{"ciudad","provincia","direccion","cp"};
    }


    public ObservableList<Concesionario> read() {
        ObservableList<Concesionario> concesionarios = FXCollections.observableArrayList();

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelect())) {

            ResultSet rs = pst.executeQuery();
            while (rs.next())
                concesionarios.add(new Concesionario(rs));

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return concesionarios;
    }
}
