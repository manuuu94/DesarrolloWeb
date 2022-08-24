package model;
public class TipoLavados {
    private int id;
    private String descripcion;
    private String precio;

    public TipoLavados() {
    }
    public TipoLavados(String descripcion, String precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }            
}
