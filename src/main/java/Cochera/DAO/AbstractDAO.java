package Cochera.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Cochera.Configuracion;

public abstract class AbstractDAO implements AutoCloseable {
    private static final String URL = "jdbc:mysql://localhost:" + Configuracion.PORT.dato + "/" + Configuracion.DB.dato
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    protected Connection conexion;
    protected String tabla;

    public AbstractDAO(String tabla) throws SQLException {
        this.tabla = tabla;
        openDB();
    }

    private void openDB() throws SQLException {
        conexion = DriverManager.getConnection(URL, Configuracion.USER.dato, Configuracion.PASS.dato);
        System.out.println("Conexion ok");
    }

    @Override
    public void close() {
        try {
            conexion.close();
            System.out.println("Conexion cerrar");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTabla() {
        return tabla;
    }
}
