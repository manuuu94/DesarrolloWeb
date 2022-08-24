package model;
import java.util.Date;

public class CarrosAtendidos {
    private String placa;
    private Date fechaAtencion;
    private char completado;

    public CarrosAtendidos() {
    }

    public CarrosAtendidos(String placa, Date fechaAtencion, char completado) {
        this.placa = placa;
        this.fechaAtencion = fechaAtencion;
        this.completado = completado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public char getCompletado() {
        return completado;
    }

    public void setCompletado(char completado) {
        this.completado = completado;
    }
    
    
}
