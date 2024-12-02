/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.sistema;

/**
 *
 * @author Andres
 */

import java.util.*;

public class Ticket {
    private ArrayList<Boleto> boletos = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private String total = "";
    
    public Ticket(ArrayList<Boleto> boletos, ArrayList<Producto> productos){
        this.boletos = boletos;
        this.productos = productos;
    }
    
    public ArrayList<Boleto> getBoletos(){
        return this.boletos;
    }
    
    public String getTotal(){
        return this.total;
    }
    
    public ArrayList<Producto> getProductos(){
        return this.productos;
    }
    
    public void imprimirTicket(String sucursal){
        double totalTicket = 0.0;
        System.out.println("------------------------------------------------------------");
        System.out.println("CineTICs " + sucursal);
        for(Producto producto: this.productos){
            totalTicket += Double.parseDouble(producto.getPrecio());
            System.out.println(producto.getNombre() + " " +
                    producto.getCodigo() + " " + producto.getCategoria() + 
                    " " + producto.getPrecio());
        }
        
        for(Boleto boleto: this.boletos){
            totalTicket += Double.parseDouble(boleto.getPrecio());
            System.out.println(boleto.getSala() + " " +
                    boleto.getAsiento() + " " + boleto.getFuncion() + 
                    " " + boleto.getPrecio());
        }
        System.out.println("Total de compra: " + totalTicket);
        System.out.println("------------------------------------------------------------");
    }
    
    
}
