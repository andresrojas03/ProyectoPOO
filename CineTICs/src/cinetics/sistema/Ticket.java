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
import java.io.*;

public class Ticket {
    private ArrayList<Boleto> boletos = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private int totalTicket = 0;
    
    public void setProductos(ArrayList<Producto> productos){
        this.productos = productos;
    }
    
    public void setBoletos(ArrayList<Boleto>boletos){
        this.boletos = boletos;
    }
    public ArrayList<Boleto> getBoletos(){
        return this.boletos;
    }
    
    public int getTotalTicket(){
        return this.totalTicket;
    }
    
    public ArrayList<Producto> getProductos(){
        return this.productos;
    }
    
    public synchronized void imprimirTicket(String sucursal) {
        String archivoTicket = "src/Simulacion/archivos/ventasSimulacion.txt";
        int totalTicket = 0;
        StringBuilder ticket = new StringBuilder();

        ticket.append("------------------------------------------------------------\n");
        ticket.append("CineTICs " + sucursal + "\n");

        // Escribir productos
        for (Producto producto : this.productos) {
            totalTicket += Integer.parseInt(producto.getPrecio());
            ticket.append(producto.getNombre() + " " +
                    producto.getCodigo() + " " + producto.getCategoria() + 
                    " " + producto.getPrecio() + "\n");
        }

        // Escribir boletos
        for (Boleto boleto : this.boletos) {
            totalTicket += Integer.parseInt(boleto.getPrecio());
            ticket.append(boleto.getSala() + " " +
                    boleto.getAsiento() + " " + boleto.getFuncion() + 
                    " " + boleto.getPrecio() + "\n");
        }

        // Escribir total
        ticket.append("Total de compra: " + totalTicket + "\n");
        ticket.append("------------------------------------------------------------\n");
        
        // Escribir el contenido en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTicket, true))) {
            writer.write(ticket.toString());  // Escribe todo el ticket en el archivo
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de ticket: " + e.getMessage());
        }
    }
}
