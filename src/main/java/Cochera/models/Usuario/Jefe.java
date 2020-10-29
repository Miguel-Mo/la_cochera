package Cochera.models.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Jefe  extends Usuario {

    public Jefe(ResultSet datos) throws SQLException {
        super(datos);
    }
}
