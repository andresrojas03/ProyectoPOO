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
import cinetics.personas.Persona;


public class Carrito {
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Boleto> boletos = new ArrayList<>();
    private int total = 0;
    
    
    
    
    public ArrayList<Producto> getProductosCarrito(){
        return this.productos;
    }
    
    public ArrayList<Boleto> getBoletosCarrito(){
        return this.boletos;
    }
    
    public void setBoletos(ArrayList<Boleto> boletos){
        this.boletos = boletos;
    }
    
    public void setProductos(ArrayList<Producto> productos){
        this.productos = productos;
    }
    
    public void eliminarProducto(){
        Scanner scanner = new Scanner(System.in);
        Producto productoSeleccionado;
        int indiceProductos = 1;
        int selProducto = 0;
        for(Producto p: this.productos){
            System.out.println((indiceProductos+1) + ". " +p.getNombre());
        }
        
        while(true){
            try{
                System.out.print("Ingrese el indice del producto que desea eliminar: ");
                selProducto = scanner.nextInt();
                if(selProducto > this.productos.size() || selProducto < 0){
                    System.out.println("Intentelo de nuevo");
                }
                
                break;
            }catch(Exception e){
                System.out.println("Indice invalido, intentelo de nuevo");
                scanner.nextLine();
            }
        }
        
        productoSeleccionado = this.productos.get(selProducto);
        
        if(this.productos.contains(productoSeleccionado)){
            this.productos.remove(productoSeleccionado);
            System.out.println("El objeto " + productoSeleccionado.getNombre() + " ha sido eliminado del carrito");
        }else{
            System.out.println("El producto no se encuentra en el carrito");
        }
        
    }
    
    public void editarCarrito(int seleccion, Sucursal sucursal, Persona cliente){
        if(seleccion == 1){
            //logica para agregar Productos
            sucursal.agregarAlimentos(cliente);
        } else if(seleccion == 2){
            this.eliminarProducto();
        }
        
    }
    
    public void verCarrito(Sucursal sucursal, Persona cliente){;
        Scanner scanner = new Scanner(System.in);
        int indiceProductos = 0;
        int indiceBoletos = 0;
        int seleccion = 0;
        
        int seleccionEditar = 0;

        for(Producto p: this.productos){
            System.out.println((indiceProductos+1) + ". " +p.getNombre() + " || " + p.getCantidad() + " || " + "total: " + (Integer.parseInt(p.getPrecio()) * p.getCantidad()));
        }
        
        for(Boleto b: this.boletos){
            System.out.println((indiceBoletos+1) + b.getFuncion() + " " + b.getAsiento() + " " + b.getPrecio() );
        }
        
        while(true){
            try{
                System.out.println("Que desea hacer? 1.Editar Carrito\n2.Salir");
                System.out.print("Su seleccion: ");
                seleccion = scanner.nextInt();
                switch(seleccion){
                    case 1:
                        while(true){
                            try{
                                System.out.println("Que desea hacer? 1.Editar Carrito\n2.Salir");
                                System.out.print("Su seleccion: ");
                                seleccionEditar = scanner.nextInt();
                                if(seleccionEditar ==1 || seleccionEditar == 2){
                                    this.editarCarrito(seleccionEditar, sucursal, cliente);
                                    break;
                                }else{
                                    System.out.println("Indice invalido, intentelo de nuevo");
                                    continue;
                                }
                            }catch(Exception e){
                                System.out.println("Entrada invalida, intentelo de nuevo");
                                scanner.nextLine();
                            }
                        }
                        continue;
                    case 2: 
                        break;
                }
                break;
                
            }catch(Exception e){
                System.out.println("Entrada invalida, intentelo de nuevo");
                scanner.nextLine();
            }
        }
        
        
    }
    
    public int pagarCarrito(){
        for(Boleto b: this.boletos){
            System.out.println(b.getFuncion() + " " + b.getPrecio());
            this.total += Integer.parseInt(b.getPrecio());
        }
        
        for(Producto p: this.productos){
            System.out.println(p.getNombre() + " " + p.getPrecio());
            this.total += Integer.parseInt(p.getPrecio());
        }
        System.out.println("El total de su carrito es de: " + this.total);
        System.out.println("Se realizo el cargo a la tarjeta que vinculo al registrarse, que tenga un buen dia. Disfrute sus productos y la funcion");
        
        return this.total;
    }
    
    
            
}
