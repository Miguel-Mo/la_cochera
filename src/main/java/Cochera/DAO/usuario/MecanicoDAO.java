package Cochera.DAO.usuario;

import Cochera.DAO.base.AbstractDAO;
import Cochera.Models.Usuario.Mecanico;
import Cochera.Models.Vehiculo.VehiculoVender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MecanicoDAO extends AbstractDAO<Mecanico> {

    public MecanicoDAO() throws SQLException {
        super();
    }

    public ObservableList<Mecanico> read() {
        ObservableList<Mecanico> mecanicos = FXCollections.observableArrayList();

        String sql = "SELECT mecanicos.*, usuarios.*" +
                "FROM mecanicos \n" +
                "LEFT JOIN usuarios  ON mecanicos.usuarioID = usuarios.id \n";

        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Mecanico mecanico = new Mecanico(rs);
                mecanico.setEspecialidades(obtenerEspecialidades(mecanico.getId()));
                mecanicos.add(mecanico);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return mecanicos;
    }

    private List<String> obtenerEspecialidades(int mecanicoId) {
        final String SQL = "SELECT T.descripcion FROM especialidades E\n" +
                "LEFT JOIN tipos_vehiculos T ON E.tipoID = T.id\n" +
                "WHERE E.mecanicoID = ?";

        List<String> especialidades = new ArrayList<>();

        try (PreparedStatement pst = conexion.prepareStatement(SQL)) {
            pst.setInt(1,mecanicoId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) especialidades.add(rs.getString(1));
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return especialidades;
    }
}
