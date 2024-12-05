package cinetics.sistema;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



/**
 *
 * @author Andres
 */

import cinetics.personas.*;
import cinetics.sistema.Caja;

import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Sucursal {
    private String nombre;
    private ArrayList<Pelicula> cartelera = new ArrayList<>();
    private ArrayList<Sala> salas = new ArrayList<>();
    private ArrayList<Producto> inventario = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private ArrayList<Gerente> gerentes = new ArrayList<>();
    private ArrayList<Ticket> ventas = new ArrayList<>();
    private ArrayList<Caja> cajas = new ArrayList<>();
    private int totalSucursal;
    
    
    public Sucursal(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public ArrayList<Pelicula> getCartelera(){
        return this.cartelera;
    }
   
    public int getTotalSucursal(){
        return this.totalSucursal;
    }
    
    public ArrayList<Ticket> getVentas(){
        return this.ventas;
    }
    
    public ArrayList<Producto> getInventario(){
        return this.inventario;
    }
    
    public void mostrarCartelera(Persona cliente){
        Scanner scanner = new Scanner(System.in);
        String salir;
        int contarPeliculas = 1;
        int indiceFunciones = 0;
        
        for(Pelicula p: this.cartelera){
            System.out.println(contarPeliculas +".Pelicula: " + p.getNombre() + " || " +
                    "Sala: " + p.getSala() + " || " + "Inicio de la funcion: " + p.getHora() + 
                    " || " + "Duracion: " + p.getDuracion() + " minutos");
        }
        
        while(true){
            try {
                System.out.print("Ingrese el indice del producto que desea agregar. Presione s para salir: ");
                salir = scanner.nextLine();

                if (salir.equalsIgnoreCase("s")) {
                    break;
                }

                // Intenta convertir la entrada a un índice
                try {
                    indiceFunciones = Integer.parseInt(salir);
                } catch (NumberFormatException e) {
                    System.out.println("Indice invalido, intentelo de nuevo.");
                    continue; // Reinicia el ciclo
                }
                
                if(indiceFunciones-1 > this.cartelera.size()){
                    System.out.println("Ingrese un indice valido");
                }
                
                this.procesoCompra(this.cartelera.get(indiceFunciones-1), cliente);
                
                
            }catch(Exception e){
                    System.out.println("Entrada invalida, intentelo de nuevo");
                    scanner.nextLine();
                }
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
                    String codigo = datos[0];
                    String nombre = datos[1];
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
        int indiceInventario =0;
        for(Producto p: this.inventario){
            System.out.println(indiceInventario + " || " +p.getNombre() + " || " + p.getPrecio() + 
                    " || " + p.getCategoria() + " || " + p.getStock() + 
                    " || " + p.getCodigo());
            System.out.println();
        }
    }
    
    public void crearSalas(int[] lugaresSalas){
        String nombreSala;
        for(int i = 0; i < lugaresSalas.length; i++){
            nombreSala = "sala" + (i+1);
            Sala nuevaSala = new Sala(nombreSala,lugaresSalas[i] );
            this.salas.add(nuevaSala);
        }
    }
    
    public void generarTicket(Ticket ticket, String archivoInventario){
        
        int costoBoletos = 0;
        int costoProductos = 0;
        
        System.out.println("---------------------------------------------------");
        for(Boleto boleto: ticket.getBoletos()){
            System.out.println(boleto.getFuncion() + " " + boleto.getAsiento() +
                    " " + boleto.getSala() + " " + boleto.getPrecio());
            costoBoletos += Integer.parseInt(boleto.getPrecio());
        }
        for(Producto producto: ticket.getProductos()){
            System.out.println(producto.getNombre() + " " + producto.getCategoria() +
                    " " + producto.getCodigo() + " " + producto.getCantidad() + 
                    " " + producto.getPrecio());
            costoProductos = Integer.parseInt(producto.getPrecio());
        }
        int totalCompra = costoBoletos + costoProductos;
        
        System.out.println("Total de compra: " + totalCompra);
        System.out.println("---------------------------------------------------");
        
        //llamar a la funcion actualizar productos
        this.actualizarInventario(archivoInventario);
        
        //
        
        
    }
    
    public void comprobarInventario(ArrayList<Producto> carritoUsuario){
        //llamar antes de agregar produtos al carrito para que sea valida la entrada
        for(Producto p: carritoUsuario){
            //buscamos el producto
            for(Producto pInventario: this.inventario){
                if(p.equals(pInventario)){
                    int stock = pInventario.getStock();
                    int cantCompra = p.getCantidad();
                    
                    if((stock-cantCompra) <0){
                        System.out.println("No contamos con las unidades suficientes para su peticion");
                    }
                    int nuevoStock = stock-cantCompra;
                    pInventario.setStock(nuevoStock);
                    pInventario.setVenta(cantCompra);
                }  
            }
        }
    }
    
    public void actualizarInventario(String archivoInventario){
        List<String> lineas = new ArrayList<>();
        for (Producto producto : this.inventario) {
            lineas.add(producto.toString());
        }

        Path path = Paths.get(archivoInventario);
        try {
            Files.write(path, lineas);
            System.out.println("Se actualizo el inventario");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    public void procesarPeticion(Queue<String[]> peticiones) {
            // Asignamos los empleados a cada caja
            Caja caja1 = new Caja(this.empleados.get(0), this.nombre);
            Caja caja2 = new Caja(this.empleados.get(1), this.nombre);
            
            //agregamos los inventarios a las cajas
            caja1.setInventario(inventario);
            caja2.setInventario(inventario);
            // Inicializamos los hilos para cada caja
            Thread hiloCaja1 = new Thread(() -> {
                if (!peticiones.isEmpty()) {
                    String[] peticion = peticiones.poll();  // Asignamos la primera petición a la caja 1
                    caja1.setOrden(peticion);
                    caja1.run();
                }
            });

            Thread hiloCaja2 = new Thread(() -> {
                if (!peticiones.isEmpty()) {
                    String[] peticion = peticiones.poll();  // Asignamos la siguiente petición a la caja 2
                    caja2.setOrden(peticion);
                    caja2.run();
                }
            });

            // Iniciamos ambos hilos simultáneamente
            hiloCaja1.start();
            hiloCaja2.start();

            // Esperamos que ambos hilos terminen antes de continuar
            try {
                hiloCaja1.join();
                hiloCaja2.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restaurar el estado de interrupción
                System.out.println("Hilo principal interrumpido: " + e.getMessage());
            }

            // Agregamos los tickets a la lista de ventas
            this.ventas.add(caja1.getTicket());
            this.ventas.add(caja2.getTicket());
            
            //sumamos las ventas de las cajas
            this.totalSucursal += caja1.getTotalCaja();
            this.totalSucursal += caja2.getTotalCaja();
        }
    
    public void incrementarInventario(String archivoInventario){
        Scanner scanner = new Scanner(System.in);
        int nuevoStock= 0;
        System.out.println("Actualizando inventario de la sucursal " + this.nombre);
        
        for(Producto p: this.inventario){
            if(p.getNombre().equals("Peluche Rob")){
                //no hacer nada
                continue;
            }
            if(p.getStock() < 38){
                System.out.println("El producto " + p.getNombre() + " tiene " + p.getStock() + " unidades en el inventario");
                try{
                    System.out.print("Ingrese la nueva cantidad del producto " + p.getNombre() + ": ");
                    nuevoStock= scanner.nextInt();
                    scanner.nextLine();
                    p.setStock(nuevoStock);
                
            }catch(Exception e){
                System.out.println("Entrada invalida, intentelo de nuevo");
                scanner.nextLine();
            }
                
            }
        }
        
        this.actualizarInventario(archivoInventario);
    }
    
    public void cargarEmpleados(Persona nuevoEmpleado){
        if(nuevoEmpleado instanceof Gerente){
            this.gerentes.add((Gerente)nuevoEmpleado);
            
        } else if(nuevoEmpleado instanceof Empleado){
            this.empleados.add((Empleado) nuevoEmpleado);
        } else{
            System.out.println("No se pudo agregar");
        }
    }
    
    public Ticket procesoCompra(Pelicula peliculaSeleccionada, Persona cliente){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Boleto> boletos = new ArrayList<>();
        Ticket nuevaVenta;
        
        int boletosAdulto = 0;
        int boletosNino = 0;
        int precioTotalBoletos = 0;
        int precioAdulto = 80;
        int precioNino = 50;
        int asiento = 0;
        String confirmarAlimentos;
        
        System.out.println("La función seleccionada tiene su funcion a las: " + peliculaSeleccionada.getHora());
        
        for(Sala sala: this.salas){
            //obtener la sala en la que está la pelicula y comprobar que haya lugares disponibles
            if(sala.getNombre().equals(peliculaSeleccionada.getSala()) && sala.getLugares() > 0){
                System.out.println("Hay " + sala.getLugares() + " disponibles para la funcion");
                
                //elegir boletos adulto
                while(true){
                    try{
                        System.out.print("Cuantos boletos de adulto desea agregar?: ");
                        boletosAdulto = scanner.nextInt();
                        scanner.nextLine();
                        
                    }catch(Exception e){
                        System.out.println("Entrada invalida, intentelo de nuevo");
                        scanner.nextLine();
                    }
                    break;
                }
                
                //elegir boletos niño
                while(true){  
                    try{
                        System.out.print("Cuantos boletos de nino desea agregar?: ");
                        boletosNino = scanner.nextInt();
                        scanner.nextLine();
                        
                    }catch(Exception e){
                        System.out.println("Entrada invalida, intentelo de nuevo");
                        scanner.nextLine();
                    }
                    break;
                }
                
                //comprobamos que haya suficientes lugares para la peticion de boletos
                if(boletosAdulto + boletosNino > sala.getLugares()){
                    System.out.println("No tenemos lugares suficientes para la peticion");
                }
                
                //creamos los boletos de adulto y los guardamos en la lista de boletos
                for(int i = 0; i < boletosAdulto; i++){
                    asiento = i+1;    
                    Boleto nuevoBoletoAdulto = new Boleto(sala.getNombre(), peliculaSeleccionada.getHora(), peliculaSeleccionada.getNombre(), String.valueOf(asiento), String.valueOf(precioAdulto));
                    sala.actualizarLugares(asiento);
                    boletos.add(nuevoBoletoAdulto);
                    nuevoBoletoAdulto.setVenta(1);
                    precioTotalBoletos += precioAdulto;
                }
                
                //creamos los boletos de niño y los guardamos en la lista de boletos
                for(int j = 0; j < boletosNino; j++){
                    asiento = j+1;    
                    Boleto nuevoBoletoNino = new Boleto(sala.getNombre(), peliculaSeleccionada.getHora(), peliculaSeleccionada.getNombre(), String.valueOf(asiento), String.valueOf(precioNino));
                    sala.actualizarLugares(asiento);
                    nuevoBoletoNino.setVenta(1);
                    boletos.add(nuevoBoletoNino);
                    precioTotalBoletos += precioNino;
                }
                
                break;
                
                
            }
        }
        
        System.out.println("Boletos agregados al carrito");
        cliente.getCarrito().setBoletos(boletos);
        
        while(true){
            try{
                System.out.println("Desea agregar alimentos a su compra? [S/N]");
                confirmarAlimentos = scanner.nextLine();
                if(confirmarAlimentos.equals("s")){
                    this.agregarAlimentos(cliente);
                }else{
                    System.out.println("Que tenga un lindo dia, disfrute de su función");
                }
                
                this.totalSucursal += cliente.realizarCompra();
                nuevaVenta = cliente.nuevoTicket();
                break;
            }catch(Exception e){
                System.out.println("Entrada invalida");
                continue;
            }
        }
        
        this.ventas.add(nuevaVenta);
        return nuevaVenta;
        
    }
    
    public void agregarAlimentos(Persona cliente){
        Scanner scanner = new Scanner(System.in);
        String salir;
         int indiceInventario =0;
         int cantidadProductos = 0;
         
        for(Producto p: this.inventario){
            System.out.println((indiceInventario+1) + " || " +p.getNombre() + " || " + p.getPrecio() + 
                    " || " + p.getCategoria());
            indiceInventario ++;
            
        }
        
        //agregar los productos al carrito
        do {
            try {
                System.out.print("Ingrese el indice del producto que desea agregar. Presione s para salir: ");
                salir = scanner.nextLine();

                if (salir.equalsIgnoreCase("s")) {
                    break;
                }

                // Intenta convertir la entrada a un índice
                try {
                    indiceInventario = Integer.parseInt(salir);
                } catch (NumberFormatException e) {
                    System.out.println("Indice invalido, intentelo de nuevo.");
                    continue; // Reinicia el ciclo
                }

                // Verifica si el índice es válido (dentro de los límites del inventario)
                if (indiceInventario < 0 || indiceInventario >= this.inventario.size()) {
                    System.out.println("Indice fuera de rango, intentelo de nuevo.");
                    continue; // Reinicia el ciclo
                }

                System.out.print("Ingrese la cantidad que desea de ese producto: ");
                cantidadProductos = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner
                
                this.inventario.get(indiceInventario-1).setCantidad(cantidadProductos);
                
                // Agregar los productos al carrito
                for (int i = 0; i < cantidadProductos; i++) {
                    cliente.getCarrito().getProductosCarrito().add(this.inventario.get(indiceInventario-1));
                }

                System.out.println("Productos agregados exitosamente.");

            } catch (Exception e) {
                System.out.println("Entrada invalida, intentelo de nuevo.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        } while (true);
        
        this.comprobarInventario(cliente.getCarrito().getProductosCarrito());
        
    
    }
}

    
    
   

