package Cochera.DAO;

import Cochera.Models.Clientes.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO extends AbstractDAO<Cliente> implements Crud<Cliente> {

    public static final String TABLA = "cliente";

    public ClienteDAO() throws SQLException {
        tabla = TABLA;
        campos = new String[]{"nombre","apellidos","telefono","dni","email","presupuesto","descripcionVehiculo"};
    }

    @Override
    public int create(Cliente cliente) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryInsert(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            ResultSet rs = super.executeInsert(pst, cliente).getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return 0;

        }
    }

    @Override
    public ObservableList<Cliente> read() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelect())) {

            ResultSet rs = pst.executeQuery();
            while (rs.next())
                clientes.add(new Cliente(rs));

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return clientes;
    }

    public Cliente read(int id) {
        Cliente cliente = null;

        try (PreparedStatement pst = conexion.prepareStatement(super.querySelectOne())) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                    cliente = new Cliente(rs);
            }

        } catch (Exception throwables) {

            throwables.printStackTrace();

        }

        return cliente;
    }

    @Override
    public boolean update(Cliente cliente) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryUpdate())) {

            return super.executeUpdate(pst, cliente, cliente.getId());

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean delete(Cliente cliente) {
        try (PreparedStatement pst = conexion.prepareStatement(super.queryDelete())) {

            pst.setInt(1,cliente.getId());
            pst.executeUpdate();
            return true;

        } catch (Exception throwables) {

            throwables.printStackTrace();
            return false;

        }
    }
}
