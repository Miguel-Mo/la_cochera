package Cochera.dao;

import Cochera.models.Clientes.Cliente;
import Cochera.models.Propuestas.Propuesta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropuestaDAO extends AbstractDAO<Propuesta> implements Crud<Propuesta>{

    public static final String TABLA = "propuesta";

    public PropuestaDAO() throws SQLException {
        tabla = TABLA;
        campos = new String[]{"cocheVendido","fechaLimite","cliente","precio","status"};
    }

    @Override
    public int create(Propuesta propuesta) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryInsert(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            ResultSet rs = super.executeInsert(pst, propuesta).getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return 0;

        }
    }

    @Override
    public ObservableList<Propuesta> read() {
        ObservableList<Propuesta> propuestas = FXCollections.observableArrayList();

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelect())) {

            ResultSet rs = pst.executeQuery();
            while (rs.next())
                propuestas.add(new Propuesta(rs));

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return propuestas;
    }

    public Propuesta read(int id) {
        Propuesta propuesta = null;

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelectOne())) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                propuesta = new Propuesta(rs);
            }

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return propuesta;
    }

    @Override
    public boolean update(Propuesta propuesta) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryUpdate())) {

            return super.executeUpdate(pst, propuesta, propuesta.getId());

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return false;

        }
    }


    @Override
    public boolean delete(Propuesta propuesta) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryDelete())) {

            pst.setInt(1,propuesta.getId());
            pst.executeUpdate();
            return true;

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return false;

        }
    }



}
