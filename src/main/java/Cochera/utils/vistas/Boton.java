package Cochera.utils.vistas;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Boton extends Button {

    private ImageView imagen;

    public Boton() {
        super();
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> setCursor(Cursor.HAND));
        this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> setCursor(Cursor.DEFAULT));
    }

    public Boton(ImageView imagen) {
        this();
        this.setStyle("-fx-background-color: white; -fx-background-insets: 0;");

        this.imagen = imagen;
        this.imagen.setPreserveRatio(true);
        this.setGraphic(imagen);
    }

    public void ajustarImg(int ajuste) {
        imagen.setFitWidth(ajuste);
    }

    public void establecerTooltip(String texto) {
        Tooltip tooltip = new Tooltip(texto);
        tooltip.setShowDelay(Duration.seconds(0.5));
        tooltip.setStyle("-fx-background-color: white; -fx-text-fill: #E76F51;");
        this.setTooltip(tooltip);
    }
}
