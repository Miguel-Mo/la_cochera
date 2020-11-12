package Cochera.dao;

import Cochera.models.Usuario.Usuario;
import Cochera.utils.Conversor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            if (rs.isBeforeFirst()) { // Si existe una línea que coincida en la BD, podremos pasar a crear el Usuario
                rs.next();
                usuario = Usuario.obtener(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usuario;
    }
}
