package Cochera.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Cochera.Configuracion;

public abstract class AbstractDAO {
    private static final String URL = "jdbc:mysql://localhost:" + Configuracion.PORT.dato + "/" + Configuracion.DB.dato
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    protected Connection conexion;

    public AbstractDAO() {
        try {
            conexion = DriverManager.getConnection(URL, Configuracion.USER.dato, Configuracion.PASS.dato);
            System.out.println("Conexion ok");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            conexion.close();
            System.out.println("Conexion cerrar");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
