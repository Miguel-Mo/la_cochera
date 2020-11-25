package Cochera.DAO.base;

import Cochera.DAO.ClienteDAO;
import Cochera.DAO.PropuestaDAO;
import Cochera.DAO.ReparacionesDAO;
import Cochera.DAO.base.Crud;
import Cochera.DAO.vehiculos.VehiculoVenderDAO;

public class DAOFactory {

    public static <T> Crud<T> obtener(String clase) throws Exception {
        switch (clase) {
            case "Cliente" : return (Crud<T>) new ClienteDAO();
            case "VehiculoVender" : return (Crud<T>) new VehiculoVenderDAO();
            case "Reparacion" : return (Crud<T>) new ReparacionesDAO();
            case "Propuesta" : return (Crud<T>) new PropuestaDAO();
            default: throw new Exception("No existe un DAO marcado para " + clase);
        }
    }
}
