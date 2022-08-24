package gestion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Pedido;

public class PedidoGestion {
    
        public static boolean insertar(Pedido pedido){
        String consulta = "insert into pedido" +
"(placa, fechaAtencion) " +
"values (?,?)";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            //set String el 1 primer ? pongale el idUsuario y al 2 segundo ? pongale el pwUsuario
            sentencia.setString(1, pedido.getPlaca());
            sentencia.setObject(2, pedido.getFechaAtencion());
            //Returns true si lo logra insertar y falso si no lo logra...
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }

        public static void insertarAtendido(Pedido pedido){
        //verdadero si logra insertar el prospecto y falso si no lo logra.
        String consulta = "insert into atendidos " +
"(placa, fechaAtencion, completado) " +
"values (?,?,?)";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            //set String el 1 primer ? pongale el idUsuario y al 2 segundo ? pongale el pwUsuario
            sentencia.setString(1, pedido.getPlaca());
            sentencia.setObject(2, pedido.getFechaAtencion());
            sentencia.setString(3, "N");
            sentencia.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
    }        


}
