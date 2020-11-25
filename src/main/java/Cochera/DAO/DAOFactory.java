package Cochera.DAO;

public class DAOFactory {

    public static <T> Crud<T> obtener(String clase) throws Exception {
        switch (clase) {
            case "Cliente" : return (Crud<T>) new ClienteDAO();
            case "VehiculoVender" : return (Crud<T>) new VehiculoVenderDAO();
            case "Reparacion" : return (Crud<T>) new ReparacionesDAO();
            default: throw new Exception("No existe un DAO marcado para " + clase);
        }
    }
}
