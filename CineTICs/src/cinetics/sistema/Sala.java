/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.sistema;

/**
 *
 * @author Andres
 */


public class Sala {
    private int lugares = 0;
    private String nombre = "";
    
    
    public Sala(String nombre, int lugares){
        this.nombre = nombre;
        this.lugares = lugares;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int getLugares(){
        return this.lugares;
    }
    
    public void actualizarLugares(int lugares){
        this.lugares -= lugares;
    }
    
}
