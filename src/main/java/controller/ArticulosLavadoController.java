package controller;
import gestion.ArticulosLavadoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ArticulosLavado;

@Named(value = "articulosLavadoController")
@SessionScoped
public class ArticulosLavadoController extends ArticulosLavado implements Serializable {


    public ArticulosLavadoController() {
    }
    
    public String insertar(){
        if (ArticulosLavadoGestion.insertar(this)){ //Si lo logró insertar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró insertar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }

    public String modifica(){
        if (ArticulosLavadoGestion.modificar(this)){ //Si lo logró modificar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR1","No se logró modificar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }
    
    public String elimina(){
        if (ArticulosLavadoGestion.eliminar(this)){ //Si lo logró elminar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró eliminar. Es posible que tenga datos relacionados");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }

    public String edita(String descripcion){
        ArticulosLavado articulos = ArticulosLavadoGestion.getArticulosLavado(descripcion);
        if(articulos != null){
            this.setDepartamento(articulos.getDepartamento());
            this.setDescripcion(articulos.getDescripcion());
            this.setCantidad(articulos.getCantidad());
            this.setPrecio(articulos.getPrecio());
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
    
    public List<ArticulosLavado> getArticulosLavado(){
        return ArticulosLavadoGestion.getArticulosLavado();
    }
}

