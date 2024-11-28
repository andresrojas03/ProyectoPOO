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
    private int stock = 0;
    
    public Producto(String nombre, String codigo, String precio, String categoria, int stock){
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
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
    
    public int getStock(){
        return this.stock;
    }
    
    
    
}