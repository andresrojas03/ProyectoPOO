/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.personas;

/**
 *
 * @author Andres
 */

import cinetics.sistema.*;
import java.util.*;


public class Persona {
    
    private String nombre = "";
    private String apellidoPaterno = "";
    private String apellidoMaterno = "";
    private String direccion = "";
    private String correo = "";
    private String celular = "";
    private String password = "";
    private String noTarjeta = "";
    private String RFC = "";
    private String direccionFiscal= "";
    private boolean inicioSesion = false;
    private String sucursalSeleccionada = "";
    private float misPuntos = 0;
    private int nivelCuenta = 0;
    private ArrayList<Ticket> misCompras = new ArrayList<>();
    private Carrito carrito = new Carrito();
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setAPaterno(String aPaterno){
        this.apellidoPaterno = aPaterno;
    }
    public void setAMaterno(String aMaterno){
        this.apellidoMaterno = aMaterno;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }
    public void setCelular(String celular){
        this.celular = celular;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setNoTarjeta(String noTarjeta){
        this.noTarjeta = noTarjeta;
    }
    public void setRFC(String rfc ){
        this.RFC = rfc;
    }
    public void setDireccionFiscal(String direccionFiscal){
        this.direccionFiscal = direccionFiscal;
    }
    public void setSucursal(String sucursal){
        this.sucursalSeleccionada = sucursal;
    }
    
    public String getSucursal(){
        return this.sucursalSeleccionada;
    }
  
    public String getNombre(){
        return this.nombre;
    }
    
    public String getAPaterno(){
        return this.apellidoPaterno;
    }
    
    public String getCorreo(){
        return this.correo;
    }
    
    public String getCelular(){
        return this.celular;
    }
    
    public boolean sesionActiva(){
        return this.inicioSesion;
    }
    
    public boolean iniciarSesion(){
        
        Scanner scanner = new Scanner(System.in);
        String validarCorreo;
        String validarPassword;
        String salir;
        
        
        while(true){
            
            System.out.println("Bienvenido, por favor ingrese sus credenciales para continuar.");
            System.out.print("Ingrese su correo: ");
            validarCorreo = scanner.nextLine();
            System.out.print("Ingrese su contrasena: ");
            validarPassword = scanner.nextLine();

            if(this.correo.equals(validarCorreo) && this.password.equals(validarPassword)){
                System.out.println("Inicio de sesion correcto, bienvenido " + this.nombre);
                this.inicioSesion = true;
                break;
            } else{
                System.out.println("Credenciales invalidas, intentelo de nuevo");
                while(true){
                    try{
                        System.out.println("Desea salir?[S/N]");
                        salir = scanner.nextLine().toLowerCase();
                        if(salir.equals("s")){
                            return false;
                        } else if(salir.equals("n")){
                            continue;
                        } 
                        break;
                    }catch(Exception e){
                        System.out.println("Entrada invalida");
                    }
                }
            }
        }
        return this.inicioSesion;
        
        
        
    }
    
    public void registroProgramaLealtad(){
        Scanner scanner = new Scanner(System.in);
        String confirmarSuscripcion;
        
        if(this.inicioSesion == true){
            System.out.println("Hola, este es el registro para el programa de lealtad, a continuacion se muestran "
                    + "los detalles de este programa");
            System.out.println("El sistema se divide en niveles los cuales iran aumentando a medida que realices compras. "
                    + "Cada nivel tiene un porcentaje de puntos y entre mas nivel tengas mas puntos puedes obtener");
            System.out.println("Nivel 0-5: En este nivel recibes un 3% de puntos en tus compras, para aumentar un nivel "
                    + "deberas acumular compras por al menos $1000");
            System.out.println("Nivel 5-10: En este nivel recibes un 7.5% de puntos en tus compras, para aumentar un nivel "
                    + "deberas acumular compras por al menos $1200");
            System.out.println("Nivel 10-15: En este nivel recibes un 15% de puntos en tus compras, para aumentar un nivel "
                    + "deberas acumular compras por al menos $1350");
            System.out.println("Nivel 15: En este nivel recibes un 15% de puntos en tus compras, por cada $500 de compra  "
                    + "recibes un producto gratis de manera aleatoria");
            System.out.println("Cada punto que tengas en tu cuenta vale $0.0027");

            try{
                System.out.println("Deseas continuar? [S/N]");
                confirmarSuscripcion = scanner.nextLine().toLowerCase();
                if(confirmarSuscripcion.equals("s")){
                    //logica para el programa de lealtad
                } else if(confirmarSuscripcion.equals("n")){
                    //logica si se rechaza la suscripcion
                    return;
                }
                
            } catch(Exception e){
                
            }
            
            
            
            
            
        } else{
            System.out.println("Primero debes iniciar sesion para poder registrarte en el programa de lealtad");
            this.iniciarSesion();
        }
    
    
    }
    
    public ArrayList<String> buscarPelicula(){
        //esta funcion necesita implementacion con R.O.B
        //regresa una lista con los datos de la pelicula para buscarlos en el sistema
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datosPelicula = new ArrayList<>();
        
        String nombrePelicula;
        String nombreSucursal;
        int elegirSucursal;
        
        System.out.print("Que funcion estas buscando?: ");
        nombrePelicula = scanner.nextLine();
        while(true){
            
            try{
                System.out.print("En que sucursal buscas la funcion " + nombrePelicula + 
                        "1.CU\n2.Delta\n3.Universidad\n4.Xochimilco");
                elegirSucursal = scanner.nextInt();
                
                switch(elegirSucursal){
                    case 1:
                        nombreSucursal = "CU";
                        break;
                    case 2:
                        nombreSucursal = "Delta";
                        break;
                    case 3:
                        nombreSucursal = "Universidad";
                        break;
                    case 4:
                        nombreSucursal = "Xochimilco";
                        break;
                    default:
                        System.out.println("Intentelo de nuevo");
                        continue; //regresa al inicio para pedir la entrada de nuevo
                }
                break; //sale del while
                
            } catch(Exception e){
                System.out.println("Entrada invalida, intentelo de nuevo: ");
                scanner.nextLine(); //consume la entrada invalida
                
            }
            
        }
        
        //creamos la lista con la forma [nombre, sucursal]
        datosPelicula.add(nombrePelicula);
        datosPelicula.add(nombreSucursal);
        
        return datosPelicula;
    }
    
    public String verCartelera(){
        System.out.println("Mostrando la cartelera de la sucursal " + this.sucursalSeleccionada);
        return this.sucursalSeleccionada;
    }
    
    public void verMisPuntos(){
        System.out.println("Tus puntos: " + this.misPuntos);
    }
    
    public void calcularPuntos(Ticket ticket){
        double puntosPorCompra = 0.0;
        double porcentajePuntos = this.obtenerNivel();
        puntosPorCompra = Double.parseDouble(ticket.getTotal())*porcentajePuntos;
        System.out.println("Tu compra de " + ticket.getTotal() + " genera " + puntosPorCompra + " puntos.");
        this.misPuntos += puntosPorCompra;
    }
    
    private double obtenerNivel(){
        //muestra el nivel y regresa el porcentaje de puntos que se obtienen por las compras
        
        int totalCompras = 0;
        int totalTicket = 0;
        double puntos = 0.0;
        
        for(Ticket ticket: this.misCompras){
            totalTicket = Integer.parseInt(ticket.getTotal());
            totalCompras += totalTicket;
        }
        //logica para todos los niveles
        //nivel 1
        if(totalCompras > 0 && totalCompras < 1000){
            //nivel 1
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 1.");
            puntos = (3.0/100);
            this.nivelCuenta = 1;
            
        }else if(totalCompras < 1000 && totalCompras > 2000){
            //nivel 2
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 2.");
            puntos = (3.0/100);
            this.nivelCuenta = 2;
            
        }else if(totalCompras < 2000 && totalCompras > 3000){
            //nivel 3
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 3.");
            puntos = (3.0/100);
            this.nivelCuenta = 3;
            
        }else if(totalCompras < 3000 && totalCompras > 4000){
            //nivel 4
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 4.");
            puntos = (3.0/100);
            this.nivelCuenta = 4;
            
        }else if(totalCompras < 4000 && totalCompras > 5200){
            //nivel 5
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 5.");
            puntos = (7.5/100);
            this.nivelCuenta = 5;
            
        }else if(totalCompras < 5200 && totalCompras > 6400){
            //nivel 6
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 6.");
            puntos = (7.5/100);
            this.nivelCuenta = 6;
            
        }else if(totalCompras < 6400 && totalCompras > 7600){
            //nivel 7
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 7.");
            puntos = (7.5/100);
            this.nivelCuenta = 7;
            
        }else if(totalCompras < 7600 && totalCompras > 8800){
            //nivel 8
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 8.");
            puntos = (7.5/100);
            this.nivelCuenta = 8;
            
        }else if(totalCompras < 8800 && totalCompras > 10000){
            //nivel 9
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 9.");
            puntos = (7.5/100);
            this.nivelCuenta = 9;
            
        }else if(totalCompras < 10000 && totalCompras > 11350){
            //nivel 10
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 10.");
            puntos = (15.0/100);
            this.nivelCuenta = 10;
            
        }else if(totalCompras < 11350 && totalCompras > 12700){
            //nivel 11
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 11.");
            puntos = (15.0/100);
            this.nivelCuenta = 11;
            
        }else if(totalCompras < 12700 && totalCompras > 14050){
            //nivel 12
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 12.");
            puntos = (15.0/100);
            this.nivelCuenta = 12;
            
        }else if(totalCompras < 14050 && totalCompras > 15400){
            //nivel 13
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 13.");
            puntos = (15.0/100);
            this.nivelCuenta = 13;
            
        }else if(totalCompras < 15400 && totalCompras > 16750){
            //nivel 14
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 14.");
            puntos = (15.0/100);
            this.nivelCuenta = 14;
            
        }else if(totalCompras > 16750){
            //nivel 15
            System.out.println("Hola " + this.nombre + " tu cuenta es nivel 15.");
            puntos = (15.0/100);
            this.nivelCuenta = 15;
            
        } 
        
        return puntos;
        
        
    }
    
    public void verMisCompras(){
        
        if(this.inicioSesion == true){
            
            int contador = 0;
            System.out.println("Aqui estan todas las compras que has realizado: ");
            for(Ticket ticket: this.misCompras){
                for(Boleto boleto: ticket.getBoletos()){
                    System.out.println(boleto.getFuncion() + " " + 
                            boleto.getAsiento() + " " + boleto.getSala() + " " + 
                            boleto.getPrecio());
                }
                for(Producto producto: ticket.getProductos()){
                    System.out.println("Producto: " +  producto.getNombre() + "\n"
                    + "Categoria: " + producto.getCategoria() + "\n"
                    + "Codigo: " + producto.getCodigo() + "\n"
                    + "Precio: " + producto.getPrecio() + "\n");
                }
            }   
        }else{
            System.out.println("Primero debes iniciar sesion");
            this.iniciarSesion();
        }
        
        
    
    }
    
    public void verCarrito(){
        
        Scanner scanner = new Scanner(System.in);
        int totalCarrito = 0;
        String procederCompra;
        
        System.out.println("Tu carrito: \n");
        for(Producto producto: this.carrito.getProductosCarrito()){
            totalCarrito += Integer.parseInt(producto.getPrecio());
            System.out.println(producto.getNombre() + " " +
                    producto.getCodigo() + " " + producto.getCategoria() + 
                    " " + producto.getPrecio());
        }
        
        System.out.println("El total de tu carrito es de: " + totalCarrito);
        
        
        
        /*
        while(true){
            try{
                System.out.println("Desea proceder con la compra? [S/N]");
                procederCompra = scanner.nextLine().toLowerCase();
                if(procederCompra.equals("s")){
                    this.realizarCompra();
                    break;
                    
                }else if (procederCompra.equals("n")){
                    break;
                }
                
                
            } catch(Exception e){
                System.out.println("Entrada invalida, intentelo de nuevo");
                scanner.nextLine();
            }
        }
        */
            
        
        
    }
    
    public void realizarCompra(){
        this.carrito.pagarCarrito();
    
    }
}
