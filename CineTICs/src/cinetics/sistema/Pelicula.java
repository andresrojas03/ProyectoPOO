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

public class Pelicula {
    private String nombre = "";
    private String clasificacion = "";
    private String sala = "";
    private String hora = "";
    private String director = "";
    private String duracion = "";
    private String sinopsis = "";
    private int[] costoBoleto = {50, 80}; 
            
    
    public Pelicula(String nombre, String clasificacion, String sala, String hora, String director, String duracion){
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.sala = sala;
        this.hora = hora;
        this.director = director;
        this.duracion = duracion;
        
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getClasificacion(){
        return this.clasificacion;
    }
    
    public String getSala(){
        return this.sala;
    }
    
    public String getHora(){
        return this.hora;
    }
    
    public String getDuracion(){
        return this.duracion;
    }
    
    public String getDirector(){
        return this.director;
    }
    
    public String getSinopsis(){
        return this.sinopsis;
    }
    
}
