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
    private String sucursal = "";
    private String funcion = "";
    private String sala = "";
    private String asientos = "";
    private String precio = "";
    private ArrayList<Producto> productos = new ArrayList<>();
    
    public void ticket(String sucursal, String funcion, String sala, String asientos, String precio){
        this.sucursal = sucursal;
        this.funcion = funcion;
        this.sala = sala;
        this.asientos = asientos;
        this.precio = precio;
    }
    
    
}
