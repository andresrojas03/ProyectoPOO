/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simulacion;



/**
 *
 * @author Andres
 */
import cinetics.sistema.Ticket;
import java.util.*;


public class Caja {
    private String numCaja;
    private ArrayList<Ticket> ventas = new ArrayList<>();
    private double total;
    
    public Caja(String numCaja){
        this.numCaja = numCaja;
    }
    
    public String getNumCaja(){
        return this.numCaja;
    }
    
    //escribe el archivo de ventas con cada venta que se realice
    public void registrarVenta(){
        
    }
    
    
    
}
