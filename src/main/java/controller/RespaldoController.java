package controller;
import gestion.CarroGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
@Named(value = "respaldoController")
@SessionScoped
public class RespaldoController implements Serializable {

    private String[] archivos = {"Carros Atendidos", "Articulos Lavado"};
    private String[] seleccionados;

    public String[] getArchivos() {
        return archivos;
    }

    public void setArchivos(String[] archivos) {
        this.archivos = archivos;
    }

    public String[] getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(String[] seleccionados) {
        this.seleccionados = seleccionados;
    }
    
    public String getArchivoRespaldo() {
        String patron = "yyyyMMdd_HHmmss_sss";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        archivoRespaldo = "Respaldo_" + formato.format(new Date()) + ".zip";
        return archivoRespaldo;
    }

    public void setArchivoRespaldo(String archivoRespaldo) {
        this.archivoRespaldo = archivoRespaldo;
    }
    private String archivoRespaldo;

    public RespaldoController() {
    }

    public void respaldo() {
        //toma la informacion y crea un archivo zip
        //primero se valida que se seleccione una opcion en la pagina web
        if(seleccionados!=null && seleccionados.length > 0){
            //Libreria para escribir achivos ZIP
            ZipOutputStream archivoZip; //donde quedará el achivo ZIP
            //se define físicamente el archivo dentro del servidor web
            File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/temporales/")+archivoRespaldo);         
            try {
                archivoZip = new ZipOutputStream(new FileOutputStream(archivo)); //inputStream es para leer zip files
                ZipEntry entradaZip; //un archivo/carpeta dentro del zip
                //se recorre el arreglo de seleccionados para ver qué está seleccionado
                for (String s: seleccionados){
                    //se va a iterar dentro del arreglo de seleccionados
                    if(s.contains("Carros Atendidos")){
                        //si se seleccionó la tabla estudiante... hago ese respaldo
                        //creo una entrada en el zip y la dispongo para comprimir
                        entradaZip = new ZipEntry("Carros.json"); //nombre del archivo dentro de la carpeta zip. cualquier nombre
                        archivoZip.putNextEntry(entradaZip);//se incorpora estudiante.zip dentro del archivo zip general
                        //comprimo la información en la entrada
                        //toma la tira Json y la genera en bytes para meterla a arreglo datos
                        byte datos[] = CarroGestion.generaJson().getBytes();
                        archivoZip.write(datos);
                        archivoZip.closeEntry();
                    }
                }        
                archivoZip.close(); //se salva el zip dentro del servidor...en el archivo temporal que está en carpeta temporales mas arriba.
                //se procede a descargar el archivo zip... en el equipo del usuario
                //se genera la página de respuesta del servidor
        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.setContentType("application/zip");
        respuesta.addHeader("Content-disposition","attachment; filename="+archivoRespaldo);
        
        //Se pasa la info del zip a la respuesta... por medio de un flujo para trasladar informacion de un lugar a otro
        ServletOutputStream flujo = respuesta.getOutputStream();  
                byte[] zip = Files.readAllBytes(archivo.toPath());
                flujo.write(zip);
                flujo.flush();
        //se completa la página y la respuesta
        FacesContext.getCurrentInstance().responseComplete();
        archivo.delete(); //elmino el archivo del servidor
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
 














