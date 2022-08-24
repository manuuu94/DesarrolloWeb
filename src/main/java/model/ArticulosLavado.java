/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jose Andres
 */
public class ArticulosLavado {
    private String departamento;
    private String descripcion;
    private int cantidad;
    private String precio;

    public ArticulosLavado() {
    }

    public ArticulosLavado(String departamento, String descripcion, int cantidad, String precio) {
        this.departamento = departamento;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    
    
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    
    

   
    
    
}
