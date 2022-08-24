package controller;
import gestion.CarritoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Carrito;

@Named(value = "carritoController")
@SessionScoped
public class CarritoController extends Carrito implements Serializable {
Carrito carro = new Carrito();

    public CarritoController() {
    }
    
    public String insertarCarrito(String producto){
        if (CarritoGestion.insertarCarrito(producto)){ //Si lo logró insertar
            return "/Carrito/list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró insertar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "list.xhtml";
        }
    }

    public String elimina(String producto){
        if (CarritoGestion.eliminar(producto)){ //Si lo logró elminar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró eliminar. Es posible que tenga datos relacionados");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "list.xhtml";
        }
    }   
      
    public List<Carrito> getCarrito(){
        return CarritoGestion.getCarrito();
    }    
    
    public String completarPedido(){
        return "edit.xhtml";  
    }
    
}

