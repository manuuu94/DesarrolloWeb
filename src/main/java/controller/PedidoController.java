package controller;
import gestion.PedidoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Pedido;

@Named(value = "pedidoController")
@SessionScoped
public class PedidoController extends Pedido implements Serializable {

    public PedidoController() {
    }
    
    public String inserta(){
        if(PedidoGestion.insertar(this)){
            //Lo pudo insertar...
            
            return "confirmacion.xhtml";
        }else{
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró insertar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
             return "edit.xhtml";
        }
    }   

    public void insertaAtendido(){
        PedidoGestion.insertarAtendido(this);
        }
    }        
