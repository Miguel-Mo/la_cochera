package Cochera.dao;

import Cochera.models.Usuario.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO extends AbstractDAO {

    public UsuarioDAO() { }

    public Usuario login(String username, String password) {
        if (!super.openDB()) return null;
        /* TODO: Averiguar cómo se puede hacer que primero se detecte que hay conexión para todos los métodos y
        *   evitar ejecutar la consulta, además de enviar mensje de error al usuario. Por el momento booleano al openDB. */

        try (PreparedStatement pst = conexion.prepareStatement("SELECT * FROM usuarios WHERE BINARY login = ? AND password = ?")) {
            pst.setString(1, username);
            pst.setString(2,password);
            ResultSet rs = pst.executeQuery();

            if (rs.isBeforeFirst()) { // Si existe una línea que coincida en la BD, prodremos pasar a crear el Usuario
                rs.next();
                return Usuario.obtener(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.closeDB();
        }

        return null;
    }
}
