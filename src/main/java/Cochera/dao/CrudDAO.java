package Cochera.dao;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

// TODO: Hay que darle un repaso a esta clase
public abstract class CrudDAO<T> extends AbstractDAO {

    public static final String LEFT_JOIN = " LEFT JOIN ";
    public static final String RIGHT_JOIN = " RIGHT JOIN ";

    protected String[] campos;
    protected HashMap<String, String> relaciones;

    public CrudDAO() throws SQLException {
        super();
        relaciones = new HashMap<>();
    }

    protected abstract int create(T objeto);
    protected abstract ObservableList<T> read();
    protected abstract boolean update(T objeto);
    protected abstract boolean delete(T objeto);

    protected PreparedStatement executeInsert(PreparedStatement ps, T objeto) throws Exception {
        setPropertiesInStatement(ps, objeto);
        ps.executeUpdate();
        return ps;
    }

    protected boolean executeUpdate(PreparedStatement ps, T objeto, int id) throws Exception {
        int parameterIndex = setPropertiesInStatement(ps, objeto);
        ps.setInt(parameterIndex, id);
        int rows = ps.executeUpdate();
        return rows > 0;
    }

    private int setPropertiesInStatement(PreparedStatement ps,T objeto) throws Exception {
        int parameterIndex = 1;

        for (String campo : campos) {
            Field variable;

            try {
                variable = objeto.getClass().getDeclaredField(campo);
            } catch (NoSuchFieldException e) {
                variable = objeto.getClass().getSuperclass().getDeclaredField(campo);
            }

            variable.setAccessible(true);
            Object estado = variable.get(objeto);
            String tipo = Arrays.stream(estado.getClass().getName().split("\\.")).reduce((primero, ultimo) -> ultimo).get();

            switch (tipo) {
                case "SimpleStringProperty": ps.setString(parameterIndex,((StringProperty) estado).getValue());break;
                case "SimpleBooleanProperty": ps.setBoolean(parameterIndex,((BooleanProperty) estado).getValue());break;
                case "SimpleFloatProperty": ps.setFloat(parameterIndex,((FloatProperty) estado).getValue());break;
                case "Integer": ps.setInt(parameterIndex,((Integer) estado));break;
            }

            parameterIndex++;
        }

        return parameterIndex;
    }

    /* ZONA DE QUERIES */

    protected String queryInsert() {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(tabla).append(" (").append(String.join(",", campos)).append(") VALUES (");

        for (int i = 0; i < campos.length; i++) {
            if (i == campos.length -1) {
                sb.append("?)");
                break;
            }
            sb.append("?,");
        }

        return sb.toString();
    }

    protected String querySelect() {
        return "SELECT * FROM " + tabla;
    }

    protected String querySelect(String tipoJoin, String tablaJoin) {
        return querySelect() + tipoJoin + tablaJoin + " ON " + tabla + "." + relaciones.get(tablaJoin) + "=" + tablaJoin + ".id";
    }

    protected String querySelectOne() {
        return querySelect() + " WHERE " + tabla + ".id=?" ;
    }

    protected String querySelectOne(String tipoJoin, String tablaJoin) {
        return querySelect(tipoJoin, tablaJoin) + " WHERE " + tabla + ".id=?" ;
    }

    protected String queryUpdate() {
        StringBuilder sb = new StringBuilder("UPDATE " + tabla + " SET ");

        for(String campo : campos) {
            sb.append(campo).append("=?,");
        }
        sb.deleteCharAt(sb.length()-1).append(" WHERE ").append(tabla).append(".id=?");

        return sb.toString();
    }

    protected String queryDelete() {
        return "DELETE FROM " + tabla + " WHERE id = ?";
    }
}