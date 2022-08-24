package gestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.TipoLavados;

public class LavadosGestion {
    
    public static boolean insertar(TipoLavados lavados){
        String consulta = "insert into Lavados " +
"(descripcion, precio) " +
"values (?,?)";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            //set String el 1 primer ? pongale el idUsuario y al 2 segundo ? pongale el pwUsuario
            sentencia.setString(1, lavados.getDescripcion());
            sentencia.setString(2, lavados.getPrecio());

            //Returns true si lo logra insertar y falso si no lo logra...
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(LavadosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }

    public static boolean modificar(TipoLavados lavados){
        //verdadero si logra insertar el estudiante y falso si no lo logra.
        String consulta = "update Lavados " +
"set precio = ?" +
"where descripcion = ?";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, lavados.getPrecio());
            sentencia.setString(2, lavados.getDescripcion());         
            //Returns true (1) si lo logra insertar y falso (0) si no lo logra...
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(LavadosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }    
    
    public static boolean eliminar(TipoLavados lavados){
        //verdadero si logra insertar el estudiante y falso si no lo logra.
        String consulta = "delete from Lavados where descripcion = ?";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, lavados.getDescripcion());
           
            //Returns true (1) si lo logra insertar y falso (0) si no lo logra...
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(LavadosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }  
    
    public static TipoLavados getLavados(String descripcion){
        TipoLavados lavados = null;
        String consulta = "select * from Lavados where descripcion = ?";       
        try {
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            
            sentencia.setString(1, descripcion);
            
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()){ 
                //llenar la tabla con los datos de estudiante
                lavados = new TipoLavados(
                        datos.getString(2), 
                        datos.getString(3)
                );      
            }            
        } catch (SQLException ex) {
            Logger.getLogger(LavadosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return lavados;       
    }

    
    public static ArrayList<TipoLavados> getLavado(){
        ArrayList<TipoLavados> lista = new ArrayList<>();       
        String consulta = "select * from Lavados";
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()){ 
                //llenar la tabla con los datos
                lista.add(new TipoLavados(
                        datos.getString(2), 
                        datos.getString(3)
                ));      
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LavadosGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return lista;       
    }
}

