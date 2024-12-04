/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simulacion;

/*
Clase para la simulación del proceso de recoger ordenes en el sistema.
Todas las ordenes y clientes serán leidos de un archivo destinado y preparado para la simulación.
Las ventas registradas en la simulación serán guardadas en un archivo especial para las ventas de la simulación
para no interferir con los archivos del sistema 
*/

/**
 *
 * @author Andres
 */

import cinetics.sistema.Sucursal;
import cinetics.sistema.Producto;
import cinetics.sistema.Ticket;
import java.io.*;
import java.util.*;


public class Simulacion {
    
    public boolean iniciarSimulacion(ArrayList<Sucursal> sucursales){
        if(this.leerPeticiones(sucursales)){
            //seguir la simulacion
        } else{
            return false;
        }
        return true;
    }
    
    public boolean leerPeticiones(ArrayList<Sucursal> sucursales) {
        
        // Leemos el archivo de peticiones
        String archivo = "src/simulacion/archivos/peticionesSimulacion.txt";
        Queue<Queue> colaPrecompras = new LinkedList<>();
        Queue<Queue> colaOrdenes = new LinkedList<>();
        Queue<String[]> colaOrdenesCU = new LinkedList<>();
        Queue<String[]> colaPrecompraCU = new LinkedList<>();
        Queue<String[]> colaOrdenesDelta = new LinkedList<>();
        Queue<String[]> colaPrecompraDelta = new LinkedList<>();
        Queue<String[]> colaOrdenesUniversidad = new LinkedList<>();
        Queue<String[]> colaPrecompraUniversidad = new LinkedList<>();
        Queue<String[]> colaOrdenesXochimilco = new LinkedList<>();
        Queue<String[]> colaPrecompraXochimilco = new LinkedList<>();
        
        if(this.comprobarInventariosSucursales(sucursales)){
            System.out.println("Iniciando simulacion");
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String peticion;
                while ((peticion = br.readLine()) != null) {
                    // Separar los datos por ".--/--."
                    String[] datosOrden = peticion.split(".--/--.");
                    String sucursal = datosOrden[0];
                    String precompra = datosOrden[1];
                    
                    
                    for(Sucursal s: sucursales){
                        if(s.getNombre().equals("CU") && sucursal.equals("CU")){
                            if(precompra.equals("si")){
                                colaPrecompraCU.add(datosOrden);
                            } else if(precompra.equals("no")){
                                colaOrdenesCU.add(datosOrden);
                            }
                        } else if(s.getNombre().equals("Delta") && sucursal.equals("Delta")){
                            if(precompra.equals("si")){
                                colaPrecompraDelta.add(datosOrden);
                            } else if(precompra.equals("no")){
                                colaOrdenesDelta.add(datosOrden);
                            }
                        }else if(s.getNombre().equals("Universidad") && sucursal.equals("Universidad")){
                            if(precompra.equals("si")){
                                colaPrecompraUniversidad.add(datosOrden);
                            } else if(precompra.equals("no")){
                                colaOrdenesUniversidad.add(datosOrden);
                            }
                        }else if(s.getNombre().equals("Xochimilco") && sucursal.equals("Xochimilco")){
                            if(precompra.equals("si")){
                                colaPrecompraXochimilco.add(datosOrden);
                            } else if(precompra.equals("no")){
                                colaOrdenesXochimilco.add(datosOrden);
                            }
                        }
                    }
                    
                }
                colaPrecompras.add(colaPrecompraCU);
                colaPrecompras.add(colaPrecompraDelta);
                colaPrecompras.add(colaPrecompraUniversidad);
                colaPrecompras.add(colaPrecompraXochimilco);
                colaOrdenes.add(colaOrdenesCU);
                colaOrdenes.add(colaOrdenesDelta);
                colaOrdenes.add(colaOrdenesUniversidad);
                colaOrdenes.add(colaOrdenesXochimilco);
                
                
                
                
                    
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            //atendemos primero a los clientes con precompra
            int peticionesPrecompra = colaPrecompras.size();
            int conteoPeticiones = 0;
            while (!colaPrecompras.isEmpty()) {
                if(conteoPeticiones == peticionesPrecompra){
                    break;
                }else{
                    conteoPeticiones += 1;
                }
                // Iteramos sobre la cola principal que contiene colas de peticiones
                for (Queue<String[]> queue : colaPrecompras) {
                    // Procesamos la cola interna (Queue<String[]>) que contiene las peticiones
                    while (!queue.isEmpty()) {
                        String[] datosOrden = queue.poll();  // Obtener el primer pedido de la cola interna
                        String sucursal = datosOrden[0];  // Extraer la sucursal del pedido

                        // Buscar la sucursal correspondiente
                        for (Sucursal s : sucursales) {
                            if (s.getNombre().equals(sucursal)) {
                                // Llamar a procesarPeticion con la cola interna completa
                                s.procesarPeticion(queue);
                            }
                        }
                    }
                }
            }


            int peticionesNormales = colaOrdenes.size();
            conteoPeticiones = 0;
            //atendemos primero a los clientes sin precompra
            while (!colaOrdenes.isEmpty()) {
                if(conteoPeticiones == peticionesNormales){
                    break;
                }else{
                    conteoPeticiones +=1;
                }
                // Iteramos sobre la cola principal que contiene colas de peticiones
                for (Queue<String[]> queue : colaOrdenes) {
                    // Procesamos la cola interna (Queue<String[]>) que contiene las peticiones
                    while (!queue.isEmpty()) {
                        String[] datosOrden = queue.poll();  // Obtener el primer pedido de la cola interna
                        String sucursal = datosOrden[0];  // Extraer la sucursal del pedido

                        // Buscar la sucursal correspondiente
                        for (Sucursal s : sucursales) {
                            if (s.getNombre().equals(sucursal)) {
                                // Llamar a procesarPeticion con la cola interna completa
                                s.procesarPeticion(queue);
                            }
                        }
                    }
                }
            }
            
            
        }else{
            return false;
        }
        this.mostrarVentas(sucursales);
        return true;
    }
    
    public boolean comprobarInventariosSucursales(ArrayList<Sucursal> sucursales){
        
        for(Sucursal s: sucursales){
            for(Producto p: s.getInventario()){
                if(p.getStock() < 38){
                    System.out.println("No se pueden iniciar operaciones, actualice el inventario");
                    return false;
                }
            }
        }
        return true;
        
    }
    
    public void mostrarVentas(ArrayList<Sucursal> sucursales){
        int totalVentaSimulacion = 0;
        for(Sucursal s: sucursales){
            totalVentaSimulacion += s.getTotalSucursal();
            System.out.println("La simulacion genero un total de: $" + totalVentaSimulacion + " en la sucursal " + s.getNombre() + 
                    " los datos de venta de productos "+ "pueden ser consultados en el archivo ventasSimulacion.txt");
        }
    }
}

    
    
    

