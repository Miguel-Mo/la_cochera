package Cochera.models.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vendedor extends  Usuario {


    public Vendedor(ResultSet datos) throws SQLException {
        super(datos);
    }
}
