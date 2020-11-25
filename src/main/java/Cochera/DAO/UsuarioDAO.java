package Cochera.DAO;

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
        //String sql="SELECT * FROM usuarios left join mecanicos on usuarios.id=mecanicos.usuarioID where usuarios.tipo=mecanico and usuarios.id="+datos.getInt("id");
        //String sql="SELECT * FROM usuarios left join mecanicos on usuarios.id=mecanicos.usuarioID where usuarios.id=?");
        try {
            if (rs.getString("tipo").equals(Usuario.MECANICO)) {
                PreparedStatement pst = conexion.prepareStatement("SELECT * FROM usuarios left join mecanicos on usuarios.id=mecanicos.usuarioID where usuarios.id=?");
                pst.setInt(1, rs.getInt("id"));

                ResultSet resultSet= pst.executeQuery();
                resultSet.next();

                return resultSet;

            }else if(rs.getString("tipo").equals(Usuario.VENDEDOR)){
                PreparedStatement pst = conexion.prepareStatement("SELECT * FROM usuarios where usuarios.id=?");
                pst.setInt(1, rs.getInt("id"));

                ResultSet resultSet= pst.executeQuery();
                resultSet.next();

                return resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return  null;

    }
}
