/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.sistema;

/**
 *
 * @author Andres
 */
import cinetics.personas.*;
import java.util.*;


public class Caja implements Runnable {
    private String sucursal; 
    private Empleado cajero;
    private String[] orden;
    private ArrayList<Producto> inventario = new ArrayList<>();
    private Ticket ticket;
    private int totalCaja;
    
    
    public Caja(Empleado cajero, String sucursal){
        this.sucursal = sucursal;
        this.cajero = cajero;
    }
    
    public void setInventario(ArrayList<Producto> inventario){
        this.inventario = inventario;
    }
    public void setOrden(String[] orden){
        this.orden = orden;
    }
    
    public int getTotalCaja(){
        return this.totalCaja;
    }
    
    @Override
  public void run() {
        ArrayList<Producto> productosCliente = new ArrayList<>();
        String[] productosEnInventario = {
            "Palomitas Grandes", 
            "Palomitas Medianas",
            "Refresco Grande",
            "Refresco Mediano",
            "Icee",
            "Nachos",
            "Hotdog",
            "Nerds",
            "M&Ms",
            "Peluche"
        };
        
        System.out.println("Hola soy " + this.cajero.getNombre() + " me encargaré de procesar tu orden");
        
        // Inicializamos las listas para los productos y las cantidades
        ArrayList<String> productosPeticion = new ArrayList<>();
        ArrayList<Integer> cantidadProductos = new ArrayList<>();

        // Procesar los productos y sus cantidades
        for (int i = 2; i < this.orden.length; i++) {
            String[] productoCantidad = this.orden[i].split(":");
            if (productoCantidad.length == 2) {
                String producto = productoCantidad[0];
                int cantidad = Integer.parseInt(productoCantidad[1]);
                
                // Verificar si el producto existe
                if (Arrays.asList(productosEnInventario).contains(producto)) {
                    productosPeticion.add(producto);
                    cantidadProductos.add(cantidad);
                }else{
                    System.out.println("El producto " + producto + " no existe en el inventario");
                }
            }
        }
        
        
        // Verificar y actualizar inventario
        for (int i = 0; i < productosPeticion.size(); i++) {
            String productoPeticion = productosPeticion.get(i);
            
            int cantidadPeticion = cantidadProductos.get(i);
            
            // Buscar el producto en el inventario
            for (Producto p : this.inventario) {
                if (p.getNombre().equals(productoPeticion)) {
                    if (p.getStock() >= cantidadPeticion) {
                        // Hay suficiente stock, actualizar inventario y sumar la cuenta
                        productosCliente.add(p);
                        totalCaja += Integer.parseInt(p.getPrecio());
                        p.reducirStock(cantidadPeticion);
                    } else {
                        // No hay suficiente stock
                        System.out.println("No hay suficiente stock para " + productoPeticion + ". Disponible: " + p.getStock());
                    }
                }
            }
        }
        
        this.generarTicket(productosCliente);
        
  }
  
  public void generarTicket(ArrayList<Producto> productosCliente){
      
      Ticket ticket = new Ticket();
      ticket.setProductos(productosCliente);
      this.totalCaja += ticket.getTotalTicket();
      this.ticket = ticket;
      ticket.imprimirTicket(sucursal);
      System.out.println("Su ticket será enviado a su correo, disfrute su compra!");
      
  }
  
  public Ticket getTicket(){
      return this.ticket;
  }
    
}
