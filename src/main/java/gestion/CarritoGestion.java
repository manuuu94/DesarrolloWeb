package gestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Carrito;
import model.Conexion;

public class CarritoGestion {
    
    public static boolean insertarCarrito(String producto){
        String consulta = "insert into Carrito" +
"(producto) " +
"values (?)";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            //set String el 1 primer ? pongale el idUsuario y al 2 segundo ? pongale el pwUsuario
            sentencia.setString(1, producto);
            //Returns true si lo logra insertar y falso si no lo logra...
            return sentencia.executeUpdate() > 0;            
        } catch (SQLException ex) {
            Logger.getLogger(CarritoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }      
        
    public static boolean eliminar(String producto){
        //verdadero si logra insertar el estudiante y falso si no lo logra.
        String consulta = "delete from Carrito where producto = ?";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, producto);
           
            //Returns true (1) si lo logra insertar y falso (0) si no lo logra...
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(CarritoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }                
                      
    public static ArrayList <Carrito> getCarrito(){
        ArrayList<Carrito> lista = new ArrayList<>();       
        String consulta = "select * from Carrito";
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()){ 
                //llenar la tabla con los datos
                lista.add(new Carrito(
                        datos.getString(2)
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CarritoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return lista;       
    }
}


