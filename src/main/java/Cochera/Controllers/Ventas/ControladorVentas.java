package Cochera.Controllers.Ventas;

import Cochera.Controllers.DataTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class ControladorVentas{

    private Parent root;

    @FXML private Pane contenido;

    @FXML
    private void initialize() { }

    @FXML
    private void cambiarVentana(ActionEvent e) throws IOException {
        String ruta;
        Button botonPulsado = (Button) e.getSource();

        switch (botonPulsado.getId()) {
            case "vehiculos" :
                ruta = "/Ventas/tablaVehiculos.fxml";
                break;
            case "clientes":
                ruta = "/Ventas/tablaClientes.fxml";
                break;
            case "propuestas":
                ruta = "/Ventas/tablaHistorialVentas.fxml";
                break;
            default:
                throw new IllegalStateException("No existe acción para: " + botonPulsado.getId());
        }

        FXMLLoader tablaFX = new FXMLLoader(getClass().getResource(ruta));
        contenido.getChildren().add(tablaFX.load()); // TODO: ¿Elimina la anterior tabla y la sustituye? Probablemente no. ARREGLAR!
        ((DataTable) tablaFX.getController()).setRoot(root);
    }

    public void setRoot(Parent root){
        this.root = root;

        // Tenemos que asegurarnos que root está disponible
        try {
            FXMLLoader vehiculosFX = new FXMLLoader(getClass().getResource("/Ventas/tablaVehiculos.fxml"));
            contenido.getChildren().add(vehiculosFX.load());
            ((DataTable) vehiculosFX.getController()).setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
