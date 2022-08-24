package controller;
import gestion.LavadosGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.TipoLavados;

@Named(value = "lavadosController")
@SessionScoped
public class LavadosController extends TipoLavados implements Serializable {


    public LavadosController() {
    }
    
    public String insertar(){
        if (LavadosGestion.insertar(this)){ //Si lo logró insertar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró insertar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }

    public String modifica(){
        if (LavadosGestion.modificar(this)){ //Si lo logró modificar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR1","No se logró modificar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }
    
    public String elimina(){
        if (LavadosGestion.eliminar(this)){ //Si lo logró elminar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró eliminar. Es posible que tenga datos relacionados");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }

    public String edita(String descripcion){
        TipoLavados lavados = LavadosGestion.getLavados(descripcion);
        if(lavados != null){ 
            this.setDescripcion(lavados.getDescripcion());
            this.setPrecio(lavados.getPrecio());

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
    
    public List<TipoLavados> getLavado(){
        return LavadosGestion.getLavado();
    }
}

