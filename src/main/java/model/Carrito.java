package model;

public class Carrito {
    private int id;
    private String producto;

    public Carrito() {
    }

    public Carrito(String producto) {
        this.producto = producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
