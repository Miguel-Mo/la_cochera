package Cochera.DAO;

import Cochera.DAO.base.AbstractDAO;
import Cochera.Models.Usuario.Usuario;
import Cochera.utils.Conversor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UsuarioDAO extends AbstractDAO<Usuario> {

    public static final String TABLA = "usuarios";

    public UsuarioDAO() throws SQLException {
        tabla = TABLA;
    }

    public Usuario login(String username, String password) {
        Usuario usuario = null;

        try (PreparedStatement pst = conexion.prepareStatement("SELECT * FROM usuarios WHERE BINARY login = ? AND BINARY password = ?")) {
            pst.setString(1, username);
            pst.setString(2, Conversor.sha256(password));
            ResultSet rs = pst.executeQuery();

            if (rs.next()){
                usuario = Usuario.obtener(Objects.requireNonNull(datosUsuario(rs)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usuario;
    }


    private ResultSet datosUsuario(ResultSet rs) {
        String SQL_VEN = "SELECT usuarios.*,vendedores.* " +
                "FROM usuarios left join vendedores on usuarios.id=vendedores.usuarioID where usuarios.id=?";

        String SQL_MEC = "SELECT usuarios.*,mecanicos.* " +
                "FROM usuarios left join mecanicos on usuarios.id=mecanicos.usuarioID where usuarios.id=?";

        try {
            final String SQL = rs.getString("tipo").equals(Usuario.MECANICO) ?
                    SQL_MEC : SQL_VEN;

            PreparedStatement pst = conexion.prepareStatement(SQL);
            pst.setInt(1, rs.getInt("id"));

            ResultSet resultSet= pst.executeQuery();
            resultSet.next();

            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }
}
