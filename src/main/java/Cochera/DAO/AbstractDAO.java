package Cochera.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Cochera.Configuracion;

public abstract class AbstractDAO {
    private static final String URL = "jdbc:mysql://localhost:" + Configuracion.PORT.dato + "/" + Configuracion.DB.dato
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    protected Connection conexion;

    public AbstractDAO() { }

    protected boolean openDB() {
        try {
            conexion = DriverManager.getConnection(URL, Configuracion.USER.dato, Configuracion.PASS.dato);
            System.out.println("Conexion ok");
            return true;
        } catch (SQLException e) {
            System.out.println("Sin conexión a Internet");
            return false;
        }
    }

    protected void closeDB() {
        try {
            conexion.close();
            System.out.println("Conexion cerrar");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
