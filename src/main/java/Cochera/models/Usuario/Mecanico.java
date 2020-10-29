package Cochera.models.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mecanico extends Usuario {


    public Mecanico(ResultSet datos) throws SQLException {
        super(datos);
    }
}
