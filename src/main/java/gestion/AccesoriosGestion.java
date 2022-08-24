package gestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Accesorios;

public class AccesoriosGestion {
    
    public static boolean insertar(Accesorios accesorios){
       
        String consulta = "insert into inventarioAccesorios " +
"(departamento, descripcion, cantidad ,precio) " +
"values (?,?,?,?)";

        try {
           
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, accesorios.getDepartamento());
            sentencia.setString(2, accesorios.getDescripcion());
            sentencia.setInt(3, accesorios.getCantidad());
            sentencia.setString(4, accesorios.getPrecio());

            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoriosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return false;
    }

    public static boolean modificar(Accesorios accesorios){
        String consulta = "update inventarioAccesorios " +
"set departamento = ?, cantidad = ?, precio = ? " +
"where descripcion = ?";
        try {
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, accesorios.getDepartamento());
            sentencia.setInt(2, accesorios.getCantidad());
            sentencia.setString(3, accesorios.getPrecio());
            sentencia.setString(4, accesorios.getDescripcion());                     
                    return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccesoriosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return false;
    }    
    
    public static boolean eliminar(Accesorios accesorios){
        String consulta = "delete from inventarioAccesorios where descripcion = ?";
        try {
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, accesorios.getDescripcion());
           
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoriosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return false;
    }  
    
    public static Accesorios getAccesorios(String descripcion){
        Accesorios accesorios = null;
        String consulta = "select * from inventarioAccesorios where descripcion = ?";       
        try {
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            
            sentencia.setString(1, descripcion);
            
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()){ 
                accesorios = new Accesorios(
                        datos.getString(2),
                        datos.getString(3),
                        datos.getInt(4),
                        datos.getString(5)
                );      
            }            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoriosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return accesorios;       
    }

    
    public static ArrayList<Accesorios> getAccesorio(){
        ArrayList<Accesorios> lista = new ArrayList<>();       
        String consulta = "select * from inventarioAccesorios";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()){ 
                lista.add(new Accesorios(
                        datos.getString(2),
                        datos.getString(3),
                        datos.getInt(4),
                        datos.getString(5)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoriosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return lista;       
    }
}