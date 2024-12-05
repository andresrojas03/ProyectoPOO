/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.sistema;

import cinetics.CineTICs;
import cinetics.personas.Persona;
import java.util.Scanner;

/**
 *
 * @author Andres
 */
public class ROB {
    public void ayudarCompra(CineTICs main, Persona cliente){
        Scanner scanner = new Scanner(System.in);
        System.out.println("****************************************************");
        System.out.println("Bienvenido al asistente de compras Rob ¿Que deseas hacer?");
        System.out.println("1. Comprar boletos\n2. Comprar alimentos");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        switch(opcion){
            case 1:
                Pelicula peliculaABuscar = cliente.buscarPelicula(main.getPeliculas());
                main.buscarPelicula(peliculaABuscar, cliente);
                break;
            case 2:
                this.mostrarProductos(main, cliente);
                int cantidad = scanner.nextInt();
                break;
            default:
                System.out.println("Opcion invalida.");
        }
        System.out.println("****************************************************");
    }
    
    public void mostrarProductos(CineTICs main, Persona cliente){
        int indiceProducto = 1;
        for(Sucursal s: main.getSucursales()){
            if(s.getNombre().equals(cliente.getSucursal())){
                s.agregarAlimentos(cliente);
            }
        }
        
    }
    
    public void ayudarEstadisticas(CineTICs main){
        Scanner scanner = new Scanner(System.in);
        System.out.println("****************************************************");
        //comunicarse con siga
        System.out.println("Estadisticas del negocio:\n1. Ventas por sucursal\n2. Ventas por funcion\n3. Ventas por producto\n4. Ventas totales");
        System.out.print("Selecciona la opcion: ");
        int opcion = scanner.nextInt();
        switch(opcion){
            case 1:
                main.reporteSucursal();
                break;
            case 2:
                main.reporteFuncion();
                break;
            case 3:
                main.reporteProducto();
                break;
            case 4:
                main.reporteSucursal();
                main.reporteFuncion();
                main.reporteProducto();
                break;
            default:
                System.out.println("Opcion invalida.");
        }
        System.out.println("****************************************************");
    }
    
    public void recomendarPelicula(CineTICs main,  Persona cliente){    
        Scanner scanner = new Scanner(System.in);
        Pelicula peliculaABuscar = cliente.buscarPelicula(main.getPeliculas());
        System.out.println("****************************************************");
        System.out.println("Parece que no encontraste la pelicula que buscabas");
        System.out.println("¿Te gustaria ampliar la busqueda en otras sucursales?\n1. Si\n2. No");
        int seleccionar = scanner.nextInt();
        scanner.nextLine();
        switch(seleccionar) {
            case 1:
                boolean peliculaEncontrada = false;
                for(Sucursal sucursal : main.getSucursales()){
                    System.out.println("Buscando en la sucursal "+sucursal.getNombre());
                    for(Pelicula p: sucursal.getCartelera()){
                        if(p.getNombre().equals(peliculaABuscar.getNombre())){
                            cliente.setSucursal(sucursal.getNombre());
                            System.out.println("¡Pelicula encontrada en la sucursal "+sucursal.getNombre()+"! Has sido trasladado a esa sucursal.");
                            peliculaEncontrada = true;
                            main.buscarPelicula(peliculaABuscar, cliente);
                            break;
                        }
                    }
                }
                if(!peliculaEncontrada){
                    System.out.println("Lo siento, no encontramos la pelicula en ninguna otra sucursal.");
                }
                break;

            case 2:
                System.out.println("¡Perfecto! Si tienes alguna otra duda no dudes en consultarme!");
                break;

            default:
                System.out.println("Dato incorrecto");
                break;
            }
        System.out.println("****************************************************");
    }
    
}
