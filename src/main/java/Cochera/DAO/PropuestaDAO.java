package Cochera.DAO;

import Cochera.Models.Propuestas.Propuesta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropuestaDAO extends AbstractDAO<Propuesta> implements Crud<Propuesta>{

    public static final String TABLA = "propuesta_venta";

    public PropuestaDAO() throws SQLException {
        tabla = TABLA;
        campos = new String[]{"clienteID","vendedorID","vehiculoVenderID","estado","fechaFin","fechaLimite","presupuesto"};
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

        // TODO: Esto está muy mal
        final String SQL = "SELECT propuesta_venta.*, cliente.*, vendedores.`*`,usuarios.*,vehiculos_vender.*,vehiculos.*,tipos_vehiculos.*,combustible_vehiculos.*\n" +
                "FROM propuesta_venta\n" +
                "LEFT JOIN cliente ON propuesta_venta.clienteID = cliente.id\n" +
                "LEFT JOIN vendedores ON propuesta_venta.vendedorID = vendedores.id\n" +
                "LEFT JOIN usuarios ON vendedores.usuarioID = usuarios.id\n" +
                "LEFT JOIN vehiculos_vender ON propuesta_venta.vehiculoVenderID = vehiculos_vender.id\n" +
                "LEFT JOIN vehiculos ON vehiculos_vender.vehiculoID = vehiculos.id\n" +
                "LEFT JOIN tipos_vehiculos ON vehiculos.tipoID = tipos_vehiculos.id " +
                "LEFT JOIN combustible_vehiculos ON vehiculos.combustibleID = combustible_vehiculos.id";

        try (PreparedStatement pst = conexion.prepareStatement(SQL)) {

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
