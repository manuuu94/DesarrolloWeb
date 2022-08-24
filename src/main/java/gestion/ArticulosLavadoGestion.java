package gestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.ArticulosLavado;

public class ArticulosLavadoGestion {
    
    public static boolean insertar(ArticulosLavado articulos){
       
        String consulta = "insert into inventarioLavado" +
"(departamento, descripcion, cantidad ,precio) " +
"values (?,?,?,?)";
        try {         
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, articulos.getDepartamento());
            sentencia.setString(2, articulos.getDescripcion());
            sentencia.setInt(3, articulos.getCantidad());
            sentencia.setString(4, articulos.getPrecio());

            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosLavadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return false;
    }

    public static boolean modificar(ArticulosLavado articulos){
        String consulta = "update inventarioLavado " +
"set departamento = ?, cantidad = ?, precio = ? " +
"where descripcion = ?";
        try {
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, articulos.getDepartamento());
            sentencia.setInt(2, articulos.getCantidad());
            sentencia.setString(3, articulos.getPrecio());
            sentencia.setString(4, articulos.getDescripcion()); 
                     
                    return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosLavadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return false;
    }    
    
    public static boolean eliminar(ArticulosLavado articulos){
        String consulta = "delete from inventarioLavado where descripcion = ?";
        try {
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, articulos.getDescripcion());
           
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosLavadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return false;
    }  
    
    public static ArticulosLavado getArticulosLavado(String descripcion){
        ArticulosLavado articulos = null;
        String consulta = "select * from inventarioLavado where descripcion = ?";       
        try {
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            
            sentencia.setString(1, descripcion);
            
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()){ 
                articulos = new ArticulosLavado(                       
                        datos.getString(2),
                        datos.getString(3),
                        datos.getInt(4),
                        datos.getString(5)
                );      
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosLavadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return articulos;       
    }

    
    public static ArrayList<ArticulosLavado> getArticulosLavado(){
        ArrayList<ArticulosLavado> lista = new ArrayList<>();       
        String consulta = "select * from inventarioLavado";
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()){ 
                lista.add(new ArticulosLavado(
                        datos.getString(2),
                        datos.getString(3),
                        datos.getInt(4),
                        datos.getString(5)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosLavadoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return lista; 
    }
}