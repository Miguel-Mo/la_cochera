package Cochera.Models.Usuario;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vendedor extends  Usuario {

    private final IntegerProperty numVentas;
    private final int id, usuarioID;


    public Vendedor(ResultSet datos) throws SQLException {
        super(datos);

        numVentas = new SimpleIntegerProperty(datos.getInt("vendedores.numVentas"));
        usuarioID = datos.getInt("vendedores.usuarioID");
        id = datos.getInt("vendedores.id");
    }

    public int getNumVentas() {
        return numVentas.get();
    }

    public IntegerProperty numVentasProperty() {
        return numVentas;
    }

    public void setNumVentas(int numVentas) {
        this.numVentas.set(numVentas);
    }

    @Override
    public int getId() {
        return id;
    }

    public int getUsuarioID() {
        return usuarioID;
    }
}
