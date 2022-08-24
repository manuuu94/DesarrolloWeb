//se crea un package gestion y clases que van a tener los metodos para gestionar la informacion de la tabla. 
//en este caso se va a valid al usuario
package gestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Usuario;

public class UsuarioGestion {
//execute command de la conexion en services:
//select nombreUsuario,correoUsuario,idRol
//from usuario
//where idUsuario=? and pwUsuario=md5(?) and activo    
//sentencia que nos va a ayudar a buscar un usuario
    public static Usuario valida(String idUsuario,String pwUsuario){
        Usuario usuario = null;
        String consulta = "select nombreUsuario,correoUsuario,idRol from usuario where idUsuario=? and pwUsuario=md5(?) and activo";
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            //set String el 1 primer ? pongale el idUsuario y al 2 segundo ? pongale el pwUsuario
            sentencia.setString(1, idUsuario);
            sentencia.setString(2, pwUsuario);
            //conjunto de resultados/datos. se realiza la consulta y se guarda en "datos". La tabla queda almacenada en datos
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()){ //si al menos tiene una fila va a entrar. se autentica. porque se busca un usuario y una pw activa
                usuario = new Usuario(idUsuario,datos.getString(1),datos.getString(2),datos.getString(3));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }    
        //regresa un null o los datos que recibe en el if
        return usuario;
    }
    
}
