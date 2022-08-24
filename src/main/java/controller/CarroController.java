package controller;
import gestion.CarroGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.CarrosAtendidos;

@Named(value = "carroController")
@SessionScoped
public class CarroController extends CarrosAtendidos implements Serializable {
    
    public CarroController() {        
    }
    
    public String insertar(){
        if (CarroGestion.insertar(this)){ //Si lo logró insertar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró insertar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }

    public String insertarCarro(){
        if (CarroGestion.insertarAtendido(this)){ //Si lo logró insertar
            return "confirmacion.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró insertar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }
    
    public void borraCarrito(){
        CarroGestion.truncate();
    }
    
    public String modifica(){
        if (CarroGestion.modificar(this)){ //Si lo logró modificar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR1","No se logró modificar");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }
    
    public String elimina(){
        if (CarroGestion.eliminar(this)){ //Si lo logró elminar
            return "list.xhtml";
        }else{//si no lo logra insertar
FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
"ERROR","No se logró eliminar. Es posible que tenga datos relacionados");       
FacesContext.getCurrentInstance().addMessage("", mensaje);
            return "edit.xhtml";
        }
    }

    public String edita(int placa){
        CarrosAtendidos atendidos = CarroGestion.getCarros(placa);
        if(atendidos != null){ 
            this.setPlaca(atendidos.getPlaca());
            this.setFechaAtencion(atendidos.getFechaAtencion());
            this.setCompletado(atendidos.getCompletado());
            return "edit.xhtml";
        }else{ 
            return "list.xhtml";
        }
    }
    
    public String edita2(){
        return "edit.xhtml";
    }
    
    public List<CarrosAtendidos> getCarro(){
        return CarroGestion.getCarro();
    }
}

