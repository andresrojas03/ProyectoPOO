package cinetics.sistema;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



/**
 *
 * @author Andres
 */

import cinetics.personas.Gerente;
import cinetics.personas.Empleado;
import java.util.*;
import java.io.*;

public class Sucursal {
    private String nombre = "";
    private ArrayList<Pelicula> cartelera = new ArrayList<Pelicula>();
    private ArrayList<Sala> salas = new ArrayList<Sala>();
    private ArrayList<Producto> inventario = new ArrayList<Producto>();
    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    private ArrayList<Gerente> gerentes = new ArrayList<Gerente>();
    private double caja = 0.00;
    
    
    public Sucursal(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void mostrarCartelera(){
        for(Pelicula p: this.cartelera){
            System.out.println("Pelicula: " + p.getNombre() + " || " +
                    "Sala: " + p.getSala() + " || " + "Inicio de la funcion: " + p.getHora() + 
                    " || " + "Duracion: " + p.getDuracion() + " minutos");
        }
    }
    
    public void cartelera(String archivoCartelera){
        this.crearCartelera(archivoCartelera);
    }
    
    private void crearCartelera(String archivoCartelera){
        
        try(BufferedReader br = new BufferedReader(new FileReader(archivoCartelera))){
            
            String linea;
            while((linea = br.readLine()) != null){
                try{
                    
                    String[] datos = linea.split(".-/-.");
                    
                    if(datos.length != 6){
                        System.err.println("Linea mal formada: " + linea);
                        continue;
                    }
                    
                    String nombre = datos[0];
                    String clasificacion = datos[1];
                    String sala = datos[2];
                    String hora = datos[3];
                    String duracion = datos[4];
                    String director = datos[5];
                    
                    Pelicula pelicula = new Pelicula(nombre, clasificacion, sala, hora, director, duracion);
                    this.cartelera.add(pelicula);
                    
                    
                }catch(NumberFormatException e){
                    System.err.println("Error de formato en la linea: " + linea);
                    e.printStackTrace();
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    public void inventario(String archivoInventario){
        this.crearInventario(archivoInventario);
    }
    
    private void crearInventario(String archivoInventario){
        try(BufferedReader br = new BufferedReader(new FileReader(archivoInventario))){
            
            String linea;
            while((linea = br.readLine()) != null){
                try{
                    
                    String[] datos = linea.split(".-/-.");
                    
                    if(datos.length != 5){
                        System.err.println("Linea mal formada: " + linea);
                        continue;
                    }
                    
                    String nombre = datos[0];
                    String codigo = datos[1];
                    String precio = datos[2];
                    String categoria = datos[3];
                    int stock = Integer.parseInt(datos[4]);
                    
                    
                    Producto producto = new Producto(nombre, codigo, precio, categoria, stock);
                    this.inventario.add(producto);
                    
                    
                }catch(NumberFormatException e){
                    System.err.println("Error de formato en la linea: " + linea);
                    e.printStackTrace();
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void mostrarInventario(){
        for(Producto p: this.inventario){
            System.out.println(p.getNombre() + " || " + p.getPrecio() + 
                    " || " + p.getCategoria() + " || " + p.getStock() + 
                    " || " + p.getCodigo());
            System.out.println();
        }
    }
    
}

    
    
   

