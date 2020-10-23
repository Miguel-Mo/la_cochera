package Cochera;

public enum Configuracion {
    USER("root"),
    PASS("m1sql"),
    PORT("3306"),
    DB("la_cochera");

    public final String dato;

    Configuracion(String valor) {
        this.dato = valor;
    }
}
