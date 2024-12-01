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
import java.time.*;
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
        Scanner scanner = new Scanner(System.in);
        CineTICs main = new CineTICs();
        Persona cliente = new Persona();
        
        Thread hiloIniciarSucursales = new Thread(()->main.iniciarSucursales());
        Thread hiloCargarUsuarios = new Thread(() ->main.cargarUsuarios());
        
        
        int seleccion;
        
        hiloIniciarSucursales.start();
        hiloCargarUsuarios.start();
        
        String sucursalSeleccionada;
        
        //seleccion sucursal
        while(true){
            
            try{
                System.out.println("Seleccione una sucursal:");
                System.out.println("1.CU\n2.Delta\n3.Universidad\n4.Xochimilco");
                System.out.print("Su seleccion: ");
                seleccion = scanner.nextInt();
                scanner.nextLine();
                
                switch(seleccion){
                    case 1:
                        sucursalSeleccionada = "CU";
                        break;
                    case 2:
                        sucursalSeleccionada = "Delta";
                        break;
                    case 3:
                        sucursalSeleccionada = "Universidad";
                        break;
                    case 4:
                        sucursalSeleccionada = "Xochimilco";
                        break;
                    default:
                        System.out.println("Ingrese un indice valido");
                        continue;
                }
                break;
        
            }catch(Exception e){
                System.out.println("Entrada invalida, intentelo de nuevo");
                scanner.nextLine();
            }
        }
        cliente.setSucursal(sucursalSeleccionada);
        
        
        //esperamos al hilo de las sucursales a que termine
        try{
            hiloIniciarSucursales.join();
            hiloCargarUsuarios.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        
        
        //menu de interaccion del usuario
        while(true){
            String seleccionUsuario;
            int selUsuarioInt= 0;
            
            //mostrar menu cuando no se ha iniciado sesion
            if(!cliente.sesionActiva()){
                while(true){
                    main.mostrarHeader(sucursalSeleccionada);
                    System.out.println("1.Ver Cartelera\n2.Buscar Pelicula\n3.Cambiar sucursal\n" + 
                            "4.Iniciar sesion\n5.Buscar producto\n6.Ver Carrito\n"+
                            "7.Actualizar datos personales\n8.Registrarse\n9.Salir");
                    seleccionUsuario = scanner.nextLine();
                    try{
                        selUsuarioInt = Integer.parseInt(seleccionUsuario);
                    }catch(Exception e){
                        System.out.println("Entrada invalida");
                        scanner.nextLine();
                    }
                    break;
                }
                
                switch(selUsuarioInt){
                    case 1:
                        System.out.println("Mostrando cartelera de la sucursal " + cliente.getSucursal());
                        main.mostrarCarteleraSucursal(cliente.getSucursal());
                        continue;
                    case 2:
                        //agregar metodo para buscar pelicula
                        continue;
                    case 3:
                        //agregar metodo para cambiar sucursal
                        continue;
                    case 4:
                        Persona respaldoCliente = cliente;
                        try {
                            cliente = main.comprobarInicioSesion();
                            
                            if (cliente != null) {
                                if(cliente.iniciarSesion()){
                                    break;
                                }
                                
                            } else {
                                // Si el cliente es null
                                cliente = respaldoCliente;
                                continue;
                            }
                        } catch (Exception e) {
                            // Si ocurre una excepción, mostramos el mensaje de error
                            System.err.println("Ocurrió un error: " + e.getMessage());
                            continue; // Continuamos con el flujo normal
                        }
                    case 5:
                        //agregar metodo para buscar producto
                        continue;
                    case 6:
                        cliente.verCarrito();
                        continue;
                    case 7:
                        //agregar metodo para sobreescribir un cliente
                        continue;
                    case 8:
                        main.registrarCliente(sucursalSeleccionada, cliente);
                        continue;
                    case 9:
                        System.out.println("Saliendo del sistema");
                        return;
                    default:
                        System.out.println("Ingrese un indice valido");
                        continue;
                }
            
            
            }else{
                //mostar menu cuando ya se inicio sesion
                //consumir la ultima seleccion
                scanner.nextLine();
                main.mostrarHeader(cliente.getSucursal());
                System.out.println("1.Ver Cartelera\n2.Buscar Pelicula\n3.Cambiar sucursal\n" + 
                        "\n4.Buscar producto\n5.Ver Carrito\n"+
                        "6.Actualizar datos personales\n7.Registrarse\n8.Ver mis compras\n" +
                        "9.Ver mis puntos\n10.Salir");
                seleccionUsuario = scanner.nextLine();
                    try{
                        selUsuarioInt = Integer.parseInt(seleccionUsuario);
                    }catch(Exception e){
                        System.out.println("Entrada invalida");
                        scanner.nextLine();
                    }
                    break;
                }
                
                switch(selUsuarioInt){
                    case 1:
                        main.mostrarCarteleraSucursal(cliente.getSucursal());
                        continue;
                    case 2:
                        //agregar metodo para buscar pelicula
                        continue;
                    case 3:
                        //agregar metodo para cambiar sucursal
                        continue;
                    case 4:
                        //agregar metodo para buscar producto
                        continue;
                    case 5:
                        cliente.verCarrito();
                        continue;
                    case 6:
                        //agregar metodo para sobreescribir un cliente
                        continue;
                    case 7:
                        main.registrarCliente(sucursalSeleccionada, cliente);
                        continue;
                    case 8:
                        cliente.verMisCompras();
                        continue;
                    case 9:
                        cliente.verMisPuntos();
                        continue;
                    case 10:
                        System.out.println("Saliendo del sistema");
                        return;
                    default:
                        System.out.println("Ingrese un indice valido");
                        continue;
                
            }
            
        }
        
        
    }
    
    public void mostrarHeader(String sucursalSeleccionada){
        System.out.println("****************************************************");
        System.out.println("RoboTICs presenta");
        System.out.println("CineTICs " + sucursalSeleccionada);
        System.out.println("El cine de confianza");
        System.out.println("****************************************************");
    }
    
    public Persona comprobarInicioSesion(){
        Scanner scanner = new Scanner(System.in);
        boolean usuarioEnBD = false;
        String validarNombre;
        String validarCelular;
        
        System.out.println("Ingresa tus credenciales: ");
        System.out.print("Ingresa tu nombre: ");
        validarNombre = scanner.nextLine().toLowerCase();
        System.out.print("Ingresa tu numero telefonico: ");
        validarCelular = scanner.nextLine();
        
        for(Persona cliente: this.clientes){
            if(cliente.getNombre().toLowerCase().equals(validarNombre) && cliente.getCelular().equals(validarCelular)){
                usuarioEnBD = true;
                return cliente;
            } else if(cliente.getNombre().toLowerCase().equals(validarNombre) || cliente.getCelular().equals(validarCelular)){
                if(cliente.getNombre().toLowerCase().equals(validarNombre)){
                    System.out.println("Numero de celularincorrecto, intentelo de nuevo");
                    while(true){
                        System.out.print("Ingresa tu numero telefonico: ");
                        validarCelular = scanner.nextLine();
                        if(cliente.getCelular().equals(validarCelular)){
                            return cliente;
                        }else{
                           System.out.println("Datos incorrectos");
                           break;
                        }
                    } 
                }else{
                    System.out.println("Nombre incorrecto");
                    while(true){
                        System.out.print("Ingresa tu nombre: ");
                        validarNombre = scanner.nextLine().toLowerCase();
                        if(cliente.getNombre().toLowerCase().equals(validarNombre)){
                            return cliente;
                        }else{
                           System.out.println("Datos incorrectos");
                           break;
                        }
                    }
                }
            }
        }
        System.out.println("No se ha encontrado el usuario en la base de datos");
        return null;
        
    }
    
    public void mostrarCarteleraSucursal(String sucursalSeleccionada) {
        boolean sucursalEncontrada = false;

        for (Sucursal sucursal : this.sucursales) {
            if (sucursal.getNombre().equals(sucursalSeleccionada)) {
                sucursal.mostrarCartelera();
                sucursalEncontrada = true;
                break;
            }
        }

        if (!sucursalEncontrada) {
            System.out.println("Sucursal no encontrada, intentelo de nuevo");
        }
    }

    
    public void mostrarTodasCarteleras(){
        
        for(Sucursal sucursal: this.sucursales){
            sucursal.mostrarCartelera();
            System.out.println();
        }    
    }
    
    
    private void cargarUsuarios(){
        Persona usuario = new Persona();
        try(BufferedReader br = new BufferedReader(new FileReader(CLIENTES_FILE))){
            String linea;
            while((linea = br.readLine()) != null){
                try{
                    
                    String[] datos = linea.split(".---.");
                    
                    if(datos.length != 9){
                        System.err.println("Linea mal formada: " + linea);
                        continue;
                    }
                    
                    String nombre = datos[0];
                    String aPaterno = datos[1];
                    String aMaterno = datos[2];
                    String direccion = datos[3];
                    String correo = datos[4];
                    String celular = datos[5];
                    String password = datos[6];
                    String noTarjeta = datos[7];
                    String sucursal = datos[8];
                    
                    usuario.setNombre(nombre);
                    usuario.setAPaterno(aPaterno);
                    usuario.setAMaterno(aMaterno);
                    usuario.setDireccion(direccion);
                    usuario.setCorreo(correo);
                    usuario.setCelular(celular);
                    usuario.setPassword(password);
                    usuario.setNoTarjeta(noTarjeta);
                    usuario.setSucursal(sucursal);

                    this.clientes.add(usuario);
                    
                }catch(NumberFormatException e){
                    System.err.println("Error de formato en la linea: " + linea);
                    e.printStackTrace();
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
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
    
    public void mostrarTodosInventarios(){
        for(Sucursal sucursal: this.sucursales){
            System.out.println("Inventario de la sucursal: "+ sucursal.getNombre());
            sucursal.mostrarInventario();
            System.out.println();
        }
    }
    
    public void registrarCliente(String sucursalSeleccionada, Persona cliente) {
        // Abrir el archivo de los clientes para registrar uno nuevo
        Scanner scanner = new Scanner(System.in);
        this.mostrarHeader(sucursalSeleccionada);

        String sucursal;
        String nombre;
        String aPaterno;
        String aMaterno;
        String direccion;
        String correo;
        String celular;
        String noTarjeta;
        String password;
        
        sucursal = sucursalSeleccionada;
        
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
        
        //comprobar que el cliente no este registrado ya
        try(BufferedReader br = new BufferedReader(new FileReader(CLIENTES_FILE))){
            String linea;
            while((linea = br.readLine()) != null){
                try{
                    
                    String[] datos = linea.split(".---.");
                    
                    if(datos.length != 9){
                        System.err.println("Linea mal formada: " + linea);
                        continue;
                    }
                    
                    String comprobarNombre = datos[0];
                    String comprobarAPaterno = datos[1];
                    String comprobarAMaterno = datos[2];
                    String comprobarCorreo = datos[4];
                    
                    if(nombre.equals(comprobarNombre) && aPaterno.equals(comprobarAPaterno)
                            && aMaterno.equals(comprobarAMaterno) && correo.equals(comprobarCorreo)){
                        System.out.println("El usuario ya se encuentra registrado");
                        cliente.iniciarSesion();
                        return;
                    }
                    
                    
                    
                }catch(NumberFormatException e){
                    System.err.println("Error de formato en la linea: " + linea);
                    e.printStackTrace();
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTES_FILE, true))) {
            // Escribiendo los datos del usuario en el siguiente formato
            // nombre.---.aP.---.aM.---.direccion.---.correo.---.celular.---.password.---.noTarjeta
            
            writer.write(nombre + ".---." + aPaterno + ".---." + aMaterno + ".---."
                    + direccion + ".---." + correo + ".---." + celular + ".---." + password
                    + ".---." + noTarjeta + ".---." + sucursal + "\n");

            cliente.setNombre(nombre);
            cliente.setAPaterno(aPaterno);
            cliente.setAMaterno(aMaterno);
            cliente.setDireccion(direccion);
            cliente.setCorreo(correo);
            cliente.setCelular(celular);
            cliente.setPassword(password);
            cliente.setNoTarjeta(noTarjeta);
            cliente.setSucursal(sucursal);
            
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
    
    protected void listarEmpleados(){
        //leer del archivo de empleados por sucursal
    
    }
    
    protected void listarGerentes(){
        //leer del archivo del gerentes por sucursal
    }
    
    protected void listarClientes(){
        for(Persona cliente: this.clientes){
            System.out.println("Sucursal del cliente: " + cliente. getSucursal() + " " + 
                    cliente.getNombre() + " " + cliente.getAPaterno()
            + " " + cliente.getCorreo() + " " + cliente.getCelular());
        }
    }
    
    protected void agregarGerente(){
        //escribir el archivo de gerentes
    }
    
    protected void agregarEmpleado(){
        //escribir el archivo de empleados
    }
    
    protected void eliminarGerente(){
        //escribir el archivo de empleados
        
    }
    
    protected void eliminarEmpleado(){
        //escribir el archivo de empleados
        
    }
    
    public void ingresaDatosFactura(){
        
    }
    
    public void generarTicket(){
    
    
    
    }
    
    public void registrarVenta(){
    
    
    
    }
    
    
    
    
    
}