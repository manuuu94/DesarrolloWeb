package gestion;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import model.Conexion;
import model.CarrosAtendidos;

public class CarroGestion {
private static final String SQL_SELECT_ATENDIDOS="select * from atendidos"; 

    public static boolean insertar(CarrosAtendidos atendidos){
        //verdadero si logra insertar el estudiante y falso si no lo logra.
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
            sentencia.setString(1, atendidos.getPlaca());
            sentencia.setObject(2, atendidos.getFechaAtencion());
            sentencia.setString(3, ""+atendidos.getCompletado());

            //Returns true si lo logra insertar y falso si no lo logra...
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }
    
    public static boolean insertarAtendido(CarrosAtendidos atendidos){
        //verdadero si logra insertar el estudiante y falso si no lo logra.
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
            sentencia.setString(1, atendidos.getPlaca());
            sentencia.setObject(2, atendidos.getFechaAtencion());
            sentencia.setString(3, "N");
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }
    
    public static void truncate(){
        String consulta = "truncate table Carrito";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);          
            //Returns true (1) si lo logra insertar y falso (0) si no lo logra...
            sentencia.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
    }   
    
    public static boolean modificar(CarrosAtendidos atendidos){
        //verdadero si logra insertar el estudiante y falso si no lo logra.
        String consulta = "update atendidos " +
"set fechaAtencion = ?, completado = ? " +
"where placa = ?";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setObject(1, atendidos.getFechaAtencion());
            sentencia.setString(2, ""+atendidos.getCompletado());
            sentencia.setString(3, atendidos.getPlaca());           
            //Returns true (1) si lo logra insertar y falso (0) si no lo logra...
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }    
    
    public static boolean eliminar(CarrosAtendidos atendidos){
        //verdadero si logra insertar el estudiante y falso si no lo logra.
        String consulta = "delete from atendidos where placa = ?";
//se usan los ? como parametros
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            sentencia.setString(1, atendidos.getPlaca());
           
            //Returns true (1) si lo logra insertar y falso (0) si no lo logra...
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroGestion.class.getName()).log(Level.SEVERE, null, ex);
        }         
        //en caso de error recibe Falso de todas maneras...
        return false;
    }  
    
    public static CarrosAtendidos getCarros(int placa){
        CarrosAtendidos atendidos = null;
        String consulta = "select * from atendidos where placa = ?";       
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion
                    .getConexion()
                    .prepareStatement(consulta);
            
            sentencia.setInt(1, placa);
            
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()){ 
                //llenar la tabla con los datos de estudiante
                atendidos = new CarrosAtendidos(
                        datos.getString(2), //placa
                        datos.getDate(3),//fecha
                        datos.getString(4).charAt(0) //completado?
                );      
            }            
        } catch (SQLException ex) {
            Logger.getLogger(CarroGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return atendidos;       
    }
  
    public static ArrayList<CarrosAtendidos> getCarro(){
        ArrayList<CarrosAtendidos> lista = new ArrayList<>();       
        String consulta = "select * from atendidos";
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()){ 
                //llenar la tabla con los datos de estudiante
                lista.add(new CarrosAtendidos(
                        datos.getString(2), //placa
                        datos.getDate(3),//fecha
                        datos.getString(4).charAt(0) //completado?
                ));      
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return lista;       
    }
    
    public static String getJsonCarro() {
        String tiraJson="";        
        //sentencia es donde realmente se crea el selec 
        PreparedStatement sentencia;
        try {
            sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_SELECT_ATENDIDOS);
            //en info se deja el resultado de la consulta..
            ResultSet info = sentencia.executeQuery();
            //Formato para la fechas
            DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            
            //Objetos para la parte de convertir a Json
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            JsonObject jsonObject;            
            JsonWriter jsonWriter;
            StringWriter tira;            
            
            while (info.next()) {  //mientras existan registros                
                //Se crea un ojbeto Json desde la info del select
                jsonObject = jsonObjectBuilder.
                        add("placa",info.getString(2)).
                        add("fechaAtencion",formato.format(info.getDate(3))).
                        add("completado",info.getString(4)).
                        build();                
                //se va a convertir un Json en String...
                tira = new StringWriter();
                jsonWriter = Json.createWriter(tira);
                jsonWriter.writeObject(jsonObject);                
                //Se "acumula" json en la tira general...
                tiraJson+=tira.toString()+"\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarroGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
     
    public static String generaJson(){//para el respaldo de las demas tablas se hace este metodo en la clase GESTION de cada una.
        //inicializa un string tiraJson. hace select de la tabla estudiante. cuando se ejecuta, 
        //en los datos queda los registros de la tabla de estudiantes y con un ciclo se recorre.
        String tiraJson = "";  
        String consulta = "select * from atendidos";
        try {
            //estamos pidiendo una conexion a la DB con el metodo que ya habiamos hecho en la clase Conexion
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            ResultSet info = sentencia.executeQuery();
            //para convertir la fecha del estudiante en texto normal
            DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            //Objetos para transformar un estudiante en Json
            //constructor de Json
            JsonObjectBuilder constructorJson = Json.createObjectBuilder();
            //objetoJson
            JsonObject objetoJson;
            //escritorJson
            JsonWriter salidaJson;            
            StringWriter tira;
            while (info.next()){ 
                //convierta un registro en Json y despues adicione el Json del registro a la tiraJson general
                //tomando del resultset datos la info del estudiante y lo convertimos en Json
                //toma toda la informacion del registro y lo transforma en un objeto JSON
                objetoJson = constructorJson
                        .add("placa",info.getString(2))
                        .add("fechaAtencion",formato.format(info.getDate(3)))
                        .add("completado",info.getString(4))
                        .build();
                //convertimo el objeto JSon a su representacion en String
                tira = new StringWriter();
                salidaJson = Json.createWriter(tira);
                salidaJson.writeObject(objetoJson);
                //adicional el json del registro a la tira general
                tiraJson += tira.toString()+"\n";
            }           
        } catch (SQLException ex) {
            Logger.getLogger(CarroGestion.class.getName()).log(Level.SEVERE, null, ex);
        }              
        return tiraJson;       
    }         
}

