package Cochera.controllers;

import Cochera.utils.vistas.VentanaCustom;
import javafx.fxml.FXML;

public class ControladorPanel extends VentanaCustom {

    //Constructor. Es lo primero que se realiza. Antes que initialize()
    public ControladorPanel() { }

    @FXML
    //Inicializa después de que se haya cargado el fxml (la vista) a la que está conectado
    private void initialize() {
        super.moverVentana();
    }
}
