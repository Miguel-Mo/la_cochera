package Cochera.Controllers.Ventas.Propuestas;

import Cochera.Controllers.Base.DataTable;
import Cochera.Models.Propuestas.Propuesta;
import javafx.scene.control.*;

public class ControladorPropuestas extends DataTable<Propuesta> {

    // Columnas
    public TableColumn<Propuesta,String> cocheVendido;
    public TableColumn<Propuesta,String> cliente;
    public TableColumn<Propuesta,String> fechaLimite;
    public TableColumn<Propuesta,String> precio;
    public TableColumn<Propuesta,String> estado;
    public TableColumn<Propuesta,String> acciones;


    public ControladorPropuestas() {

    }

    @Override
    protected void initialize() {
        super.initialize();
    }
}
