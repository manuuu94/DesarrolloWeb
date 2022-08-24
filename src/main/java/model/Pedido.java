package model;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Pedido {
    
    private int id;
    private String placa;
    private Date fechaAtencion;

    public Pedido() {
    }

    public Pedido(String placa, Date fechaAtencion) {
        this.placa = placa;
        this.fechaAtencion = fechaAtencion;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha1 = formato.format(this.fechaAtencion);
        return "Pedido{" + "placa=" + placa + ", fechaAtencion=" + fecha1 + '}';
    }
    
    
}
