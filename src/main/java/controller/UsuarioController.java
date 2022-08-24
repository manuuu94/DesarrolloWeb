package controller;
import gestion.UsuarioGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import model.Usuario;
//anotacion 
@Named(value = "usuarioController")
//SessionScoped: este objeto "usuarioController" va a estar vivo mientras el usuario tenga la sesion abierta con el servidor
@SessionScoped
//las clases controller pueden ser vistas desde las paginas Web   . 
//Como es Java tambien tiene acceso a los demas paquetes dentro de SourcePackages
public class UsuarioController extends Usuario implements Serializable {
//todo usuarioController hereda de su clase base: Usuario. Todo UsuarioController es un Usuario
    public UsuarioController() {               
    }
    
    public String valida(){
        //estamos llamando al metodo valida de UsuarioGestion, que recibe un idUsuario y pwUsuario
        //es donde realmente se está validando
        Usuario usuario = UsuarioGestion.valida(
        this.getIdUsuario(),
        this.getPwUsuario());
        if(usuario!=null){ //se autenticó?
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setCorreoUsuario(usuario.getCorreoUsuario());
            this.setIdRol(usuario.getIdRol());
            return "principaladmin.xhtml";// se presentaria la pantalla principal si se logra autenticar al usuario
        }else{
            return "index_1.xhtml"; //si no se autentica devuelve la misma pagina donde se hace el login
        }
    }
    
    public String login(){
        return "index_1";
    }
    
    public String accion(){
        return "principal.xhtml";
    }
}
