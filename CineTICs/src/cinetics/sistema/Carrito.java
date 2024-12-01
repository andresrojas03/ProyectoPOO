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


public class Carrito {
    private ArrayList<Producto> productos = new ArrayList<>();
    private double total = 0.0;
    
    
    
    
    public ArrayList<Producto> getProductosCarrito(){
        return this.productos;
    }
    
    public void agregarProductos(ArrayList<Producto> carritoUsuario){
        for(Producto p: carritoUsuario){
            this.productos.add(p);
        }
    }
    
    public void eliminarProducto(Producto productoSeleccionado){
        if(this.productos.contains(productoSeleccionado)){
            this.productos.remove(productoSeleccionado);
            System.out.println("El objeto " + productoSeleccionado.getNombre() + " ha sido eliminado del carrito");
        }else{
            System.out.println("El producto no se encuentra en el carrito");
        }
        
    }
    
    public void editarCarrito(ArrayList<Producto> agregarProductos, Producto productoEliminar){
        Scanner scanner = new Scanner(System.in);
        int seleccion;
        while(true){
            
            try{
            System.out.println("Que desea hacer con su carrito? 1.Agregar Producto\n2.Eliminar Producto\n3.Cancelar");
            seleccion = scanner.nextInt();
            switch(seleccion){
                case 1:
                    this.agregarProductos(agregarProductos);
                    break;
                case 2:
                    this.eliminarProducto(productoEliminar);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Indice invalido");
                    continue;
            }
            break;
        
            }catch(Exception e){
                System.out.println("Entrada invalida, intentelo de nuevo");
                scanner.nextLine();
            }
            
        }
        
    }
    
    public void pagarCarrito(){
        
        for(Producto p: this.productos){
            this.total += Integer.parseInt(p.getPrecio());
        }
        System.out.println("El total de su carrito es de: " + this.total);
        
    }
 
            
}
