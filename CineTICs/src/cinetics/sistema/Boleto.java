/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.sistema;

/**
 *
 * @author Andres
 */
public class Boleto {
    private String sala = "";
    private String asiento = "";
    private String funcion = "";
    private String precio = "";
    
    
    public Boleto(String sala, String funcion, String asiento, String precio){
        this.sala = sala;
        this.funcion = funcion;
        this.asiento = asiento;
        this.precio = precio;
    }
    //getters 
    
    public String getSala(){
        return this.sala;
    }
    
    public String getAsiento(){
        return this.asiento;
    }
    
    public String getFuncion(){
        return this.funcion;
    }
    
    public String getPrecio(){
        return this.precio;
    }
    

}
