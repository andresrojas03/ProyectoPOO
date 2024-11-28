/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cinetics;

/**
 *
 * @author Andres
 */
import cinetics.sistema.*;
import cinetics.personas.*;
import java.io.*;
import java.util.*;



public class CineTICs {

    /**
     * @param args the command line arguments
     */
    
    private static final String CLIENTES_FILE = "src/cinetics/archivos/clientes/clientes.txt";
    private ArrayList<Sucursal> sucursales = new ArrayList<Sucursal>();
    private ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private ArrayList<Persona> clientes = new ArrayList<Persona>();
    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    private ArrayList<Gerente> gerentes = new ArrayList<Gerente>();
    private ArrayList<Ticket> ventas = new ArrayList<Ticket>();
    
    
    
    
    public static void main(String[] args) {
        CineTICs main = new CineTICs();
        /*
        main.iniciarSucursales();
        main.mostrarCarteleras();
        main.mostrarInventarios();
        */
        
        
    }
    
    public void mostrarCarteleras(){
           
        for(Sucursal sucursal: this.sucursales){
            System.out.println("Cartelera de la sucursal: " + sucursal.getNombre());
            sucursal.mostrarCartelera();
            System.out.println();
        }
    }
       
    private void iniciarSucursales(){
        Sucursal cu = new Sucursal("CU");
        Sucursal delta = new Sucursal("Delta");
        Sucursal universidad = new Sucursal("Universidad");
        Sucursal xochi = new Sucursal("Xochimilco");
        
        this.sucursales.add(cu);
        this.sucursales.add(delta);
        this.sucursales.add(universidad);
        this.sucursales.add(xochi);
        
        //rutas de los archivos con las carteleras
        String carteleraCU = "src/cinetics/archivos/carteleras/carteleraCU.txt";
        String carteleraDelta = "src/cinetics/archivos/carteleras/carteleraDelta.txt";
        String carteleraUniversidad= "src/cinetics/archivos/carteleras/carteleraUniversidad.txt";
        String carteleraXochi = "src/cinetics/archivos/carteleras/carteleraXochimilco.txt";
        
        
        //crea las carteleras
        cu.cartelera(carteleraCU);
        delta.cartelera(carteleraDelta);
        universidad.cartelera(carteleraUniversidad);
        xochi.cartelera(carteleraXochi);
        
        String inventarioCU = "src/cinetics/archivos/inventarios/inventarioCU.txt";
        String inventarioDelta = "src/cinetics/archivos/inventarios/inventarioDelta.txt";
        String inventarioUniversidad= "src/cinetics/archivos/inventarios/inventarioUniversidad.txt";
        String inventarioXochi = "src/cinetics/archivos/inventarios/inventarioXochimilco.txt";
        
        cu.inventario(inventarioCU);
        delta.inventario(inventarioDelta);
        universidad.inventario(inventarioUniversidad);
        xochi.inventario(inventarioXochi);
        
    }
    
    public void mostrarInventarios(){
        for(Sucursal sucursal: this.sucursales){
            System.out.println("Inventario de la sucursal: "+ sucursal.getNombre());
            sucursal.mostrarInventario();
            System.out.println();
        }
    }
    
    public void registrarCliente() {
        // Abrir el archivo de los clientes para registrar uno nuevo
        Persona cliente = new Persona();
        Scanner scanner = new Scanner(System.in);

        String nombre;
        String aPaterno;
        String aMaterno;
        String direccion;
        String correo;
        String celular;
        String noTarjeta;
        String password;

        
        System.out.print("Ingrese su nombre: ");
        nombre = scanner.nextLine();
        System.out.print("Ingrese su apellido paterno: ");
        aPaterno = scanner.nextLine();
        System.out.print("Ingrese su apellido materno: ");
        aMaterno = scanner.nextLine();
        System.out.print("Ingrese su direccion: ");
        direccion = scanner.nextLine();
        System.out.print("Ingrese su correo: ");
        correo = scanner.nextLine();
        System.out.print("Ingrese su celular (10 digitos): ");
        celular = scanner.nextLine();
        while (true) {
            try{
                // Validar que el celular tenga exactamente 10 dígitos
                if (celular.length() != 10 || !celular.matches("\\d+")) {
                    System.err.println("El número de celular debe tener exactamente 10 dígitos. Por favor, inténtelo de nuevo.");
                    System.out.print("Ingrese su celular (10 digitos)");
                    celular = scanner.nextLine();
                    continue; // Volver a solicitar la entrada
                    
                }
                
                break;
            } catch(Exception e){
                System.err.println("Ocurrio un error: " + e.getMessage());
                e.printStackTrace();
            }

        }
        

        System.out.print("Ingrese su numero de tarjeta: ");
        noTarjeta = scanner.nextLine();

        while(true){
            System.out.print("Cree una contraseña (al menos 8 caracteres): ");
            password = scanner.nextLine();

            // Validar que la contraseña tenga al menos 8 caracteres
            if (password.length() < 8) {
                System.err.println("La contraseña debe tener al menos 8 caracteres. Por favor, inténtelo de nuevo.");
                System.out.print("Ingrese su contrasena: ");
                password = scanner.nextLine();
                continue; // Volver a solicitar la entrada
            }

            // Si todas las validaciones se cumplen, salir del bucle
            break;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTES_FILE, true))) {
            // Escribiendo los datos del usuario en el siguiente formato
            // nombre.---.aP.---.aM.---.direccion.---.correo.---.celular.---.password.---.noTarjeta
            writer.write(nombre + ".---." + aPaterno + ".---." + aMaterno + ".---."
                    + direccion + ".---." + correo + ".---." + celular + ".---." + password
                    + ".---." + noTarjeta + "\n");

            cliente.setNombre(nombre);
            cliente.setAPaterno(aPaterno);
            cliente.setAMaterno(aMaterno);
            cliente.setDireccion(direccion);
            cliente.setCorreo(correo);
            cliente.setCelular(celular);
            cliente.setPassword(password);
            cliente.setNoTarjeta(noTarjeta);
            
            this.clientes.add(cliente);
            System.out.println("Datos registrados");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
   
    public void eliminarCliente() {
        Scanner scanner = new Scanner(System.in);
        List<String> clientes = new ArrayList<>();
        
        String nombre;
        String aPaterno;
        String aMaterno;
        
        System.out.println("Ingrese sus datos para confirmar que desea eliminar su cuenta");
        System.out.print("Ingrese su nombre: ");
        nombre = scanner.nextLine();
        System.out.print("Ingrese su apellido paterno: ");
        aPaterno = scanner.nextLine();
        System.out.print("Ingrese su apellido materno: ");
        aMaterno = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTES_FILE))) {
            String linea;

            // Leer todas las líneas del archivo y agregarlas a la lista
            while ((linea = reader.readLine()) != null) {
                clientes.add(linea);
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        boolean encontrado = false;
        Iterator<String> iterator = clientes.iterator();

        // Buscar y eliminar el cliente
        while (iterator.hasNext()) {
            String cliente = iterator.next();
            String[] datos = cliente.split(".---.");

            if (datos.length == 8 && datos[0].equals(nombre) && datos[1].equals(aPaterno) && datos[2].equals(aMaterno)) {
                iterator.remove();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Escribir la lista actualizada de nuevo al archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTES_FILE))) {
            for (String cliente : clientes) {
                writer.write(cliente);
                writer.newLine();
            }
            System.out.println("Cliente eliminado correctamente.");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
}
