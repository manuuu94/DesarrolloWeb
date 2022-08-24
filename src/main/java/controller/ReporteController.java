package controller;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
@Named(value = "reporteController")
@SessionScoped
public class ReporteController implements Serializable {

    public ReporteController() {
    }
    //JASPER REPORT Método Controller
    public void verPdf(){
        try {
            //método permite ver un reporte dentro de un sitio web
            //definimos la ubicacion del archivo .jasper
            File jasper = new File(
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getRealPath("/CarrosAtendidos/ProyectoDesarrollo.jasper")
            );
            //se general el report a partir del .jasper
        JasperPrint reporte = JasperFillManager.fillReport(jasper.getPath(),null,Conexion.getConexion());   
// se genera una respuesta del servidor        
        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.setContentType("application/pdf");
        respuesta.addHeader("Content-Type","application/pdf");
        
        //Se pasa la info del reporte a la respuesta por medio de un flujo para trasladar informacion de un lugar a otro
        ServletOutputStream flujo = respuesta.getOutputStream();
        JasperExportManager.exportReportToPdfStream(reporte, flujo);
        
        //se completa la página y la respuesta
        FacesContext.getCurrentInstance().responseComplete();
        
        
        } catch (JRException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
