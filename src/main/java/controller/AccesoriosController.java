package controller;
import gestion.AccesoriosGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Accesorios;

@Named(value = "accesoriosController")
@SessionScoped
public class AccesoriosController extends Accesorios implements Serializable {


    public AccesoriosController() {
    }
    
    public String insertar(){
        if (AccesoriosGestion.insertar(this)){ //Si lo logró insertar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró insertar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }

    public String modifica(){
        if (AccesoriosGestion.modificar(this)){ //Si lo logró modificar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR1","No se logró modificar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }
    
    public String elimina(){
        if (AccesoriosGestion.eliminar(this)){ //Si lo logró elminar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró eliminar. Es posible que tenga datos relacionados");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }

    public String edita(String descripcion){
        Accesorios accesorios = AccesoriosGestion.getAccesorios(descripcion);
        if(accesorios != null){
            this.setDepartamento(accesorios.getDepartamento());
            this.setDescripcion(accesorios.getDescripcion());
            this.setCantidad(accesorios.getCantidad());
            this.setPrecio(accesorios.getPrecio());
            return "edit.xhtml";
        }else{ 
            return "list.xhtml";
        }
    }
    
    public String edita2(){
        return "edit.xhtml";
    }

    //PARA IR AL CARRITO EN FOLDER: CARRITO
    public String carrito(){
        return "/Carrito/list.xhtml";
    }
    
    public List<Accesorios> getAccesorio(){
        return AccesoriosGestion.getAccesorio();
    }
}

