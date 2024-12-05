/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.sistema;

/**
 *
 * @author Andres
 */
public class Producto {
    private String nombre = "";
    private String codigo = "";
    private String precio = "";
    private String categoria = "";
    private int cantidad = 0;
    private int stock = 0;
    private int venta =0;
    
    public Producto(String nombre, String codigo, String precio, String categoria, int stock){
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.categoria = categoria;
        
        this.stock = stock;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }
    
    public void setVenta(int venta){
        this.venta += venta;
    }
    public int getVenta(){
        return this.venta;
    }
    public String getNombre(){
        return this.nombre;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public String getPrecio(){
        return this.precio;
    }
    
    public String getCategoria(){
        return this.categoria;
    }
    
    public int getCantidad(){
        return this.cantidad;
    }
    
    public int getStock(){
        return this.stock;
    }
    
    public synchronized void reducirStock(int cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
        } else {
            System.out.println("No hay suficiente stock para " + nombre);
        }
        this.venta += cantidad;
    }
    
    public String toString() {
        return this.codigo + ".-/-." + this.nombre + ".-/-." + this.precio + ".-/-." + this.categoria + ".-/-." + this.stock;
    }
    
    
    
}
