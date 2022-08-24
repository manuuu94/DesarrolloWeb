package model;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    //variable que se va a usar para crear la conexion en el metodo Connection
    private static Conexion conexion;
    //DRURL está en mayus por ser una variable final/constantes ' El URL esta en services, DB properties DB URL
    //Significa: Vamos a usar una conexion a un servidor mysql. Está en nuestro equipo local "localhost" o se pondria el IP, etc.
    //la solicitudes desde el puerto 3306 son las que se escuchan y se atendera. por default se conecta a la BD ugeneral
    private static final String DBURL="jdbc:mysql://localhost:3306/proyectodesarrolloweb";
    //esta conexion es el objecto Connection para conectarse a una base de datos
    private static Connection conn=null;
    
    //implementamos un singleton en esta clase. signifca no tener que crear mas de una conexion a una BD. Sino solo usar una. 
    // no es necesario crear mas de un objeto o conexion. Singleton es un patron de diseño que asegura que eso suceda: que solo 
    //hay una conexion en memoria hacia un objeto en particular. En este caso una conexion a una DB. 
    //primer se hace un constructor privado y se le dice que queremos usar un driver para conectarnos a una DB
    //
    private Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
                    .getDeclaredConstructor()
                    .newInstance();
            //inicializamos conn hacia la DB - URL de la DB - el usuario y la contraseña de la DB. Con esto tenemos la conexion
            // a la base de Datos - se necesita usar Try catch.
            //cuando es solamente una instrucion utilzo surround statement y si son mas el block.
            conn = DriverManager.getConnection(DBURL,"proyectodesarrolloweb_user","Prueba123_");
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
                InstantiationException | IllegalAccessException | IllegalArgumentException | 
                InvocationTargetException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            //Add Catch Clause and replace with multicatch
        }        
    }  
    //el metodo que efectivamente se llama en las demas clases. Internamente en las demas clases se va a llamar este metodo para
    //crear el objeto conexion y crear conn para hacer la autenticacion con el usuario que se va a utilizar. 
    //una vez creada lo que hace es regresar conn para no crear mas "conexion"
    //synchronized es en caso que multiples solicitudes entren al mismo tiempo, pero solamente se ejecuta una vez a la vez 
    //para que entren de manera sincronizada (hilos)
    public static synchronized Connection getConexion(){
        if(conexion == null){
            conexion = new Conexion();            
        }
        return conn;
    }
}
 