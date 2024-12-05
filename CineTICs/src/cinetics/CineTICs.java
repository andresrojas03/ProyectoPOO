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
import Simulacion.*;
import java.io.*;
import java.util.*;



public class CineTICs {

    /**
     * @param args the command line arguments
     */
    
    private static final String CLIENTES_FILE = "src/cinetics/archivos/clientes/clientes.txt";
    private static final String EMPLEADOS_FILE = "src/cinetics/archivos/empleados.txt";
    private static final String GERENTES_FILE = "src/cinetics/archivos/gerentes.txt";
    private static final String FUNCIONES_FILE = "src/cinetics/archivos/funciones.txt";
    private static final String PRODUCTOS_FILE = "src/cinetics/archivos/productos.txt";
    
    private int contTrabajadores = 1;
    private ArrayList<Sucursal> sucursales = new ArrayList<Sucursal>();
    private ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private ArrayList<Persona> clientes = new ArrayList<Persona>();
    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    private ArrayList<Gerente> gerentes = new ArrayList<Gerente>();
    private ArrayList<Ticket> ventas = new ArrayList<Ticket>();
    private ArrayList<Map<String, Integer>> reporteProductos = new ArrayList<>();
    private ArrayList<Map<String, Integer>> reporteFunciones = new ArrayList<>();
    private ArrayList<Map<String, Integer>> reporteSucursales = new ArrayList<>();
    
    
    
    public static void main(String[] args) {
        
        
        Scanner scanner = new Scanner(System.in);
        CineTICs main = new CineTICs();
        Persona cliente = new Persona();
        Administrador admin = new Administrador();
        Simulacion simulacion = new Simulacion();
        ROB rob = new ROB();
        
        Thread hiloIniciarSucursales = new Thread(()->main.iniciarSucursales());
        Thread hiloCargarUsuarios = new Thread(() ->main.cargarUsuarios());
        Thread hiloCargarEmpleados = new Thread(() -> main.cargarEmpleados());
        Thread hiloCargarFunciones = new Thread(() -> main.cargarFunciones());
        Thread hiloCargarProductos = new Thread(() -> main.cargarProductos());
        Thread hiloCargarGerentes = new Thread(() -> main.cargarGerentes());
        
        int seleccion = 0;
        String sucursalSeleccionada = "";
        
        hiloIniciarSucursales.start();
        hiloCargarUsuarios.start();
        hiloCargarEmpleados.start();
        hiloCargarGerentes.start();
        hiloCargarFunciones.start();
        hiloCargarProductos.start();
        
        main.seleccionarSucursal(cliente);
        
        //esperamos al hilo de las sucursales a que termine
        try{
            hiloIniciarSucursales.join();
            hiloCargarUsuarios.join();
            hiloCargarEmpleados.join();
            hiloCargarGerentes.join();
            hiloCargarFunciones.join();
            hiloCargarProductos.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        if(admin.validarSesion()){
            while(true){
                //simulacion del sistema de entrega de productos
                while(true){
            
            String iniciarSimulacion;
            System.out.println("Desea iniciar una simulacion del sistema? [S/N]");
            iniciarSimulacion = scanner.nextLine().toLowerCase();
            if(iniciarSimulacion.equals("s")){
                
                if(!simulacion.leerPeticiones(main.sucursales)){
                    main.actualizarInventarios();
                }
            
            }else{
                break;
            }
            
        }
                main.menuAdministrador(admin, main, rob);
                return;
            }
        }
        
        
        
        //menu del cliente
        while(true){
            if(cliente.sesionActiva()){
                main.menuClienteCuenta(main, cliente, sucursalSeleccionada, rob);
                return;
            }else{
                main.menuClienteSinCuenta(main, cliente, sucursalSeleccionada, rob);
                return;
            }
        }
        
    }
    
    public ArrayList<Pelicula> getPeliculas(){
        return this.peliculas;
    }
    
    public ArrayList<Producto> getProductos(){
        return this.productos;
    }
    
    public ArrayList<Sucursal> getSucursales(){
        return this.sucursales;
    }
    
    public void seleccionarSucursal(Persona cliente){
        Scanner scanner = new Scanner(System.in);
        String sucursalSeleccionada;
        int seleccion = 0;
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
        
    }
    
    public void menuClienteCuenta(CineTICs main, Persona cliente, String sucursalSeleccionada, ROB rob){
        Scanner scanner = new Scanner(System.in);
        while(true){
            String seleccionUsuario;
            int selUsuarioInt = 0;
            if(cliente.sesionActiva()){
                while(true){
                    //mostar menu cuando ya se inicio sesion
                    //consumir la ultima seleccion
                    scanner.nextLine();
                    this.mostrarHeader(cliente.getSucursal());
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
                            System.out.println("Mostrando cartelera de la sucursal " + cliente.getSucursal());
                            this.mostrarCarteleraSucursal(cliente.getSucursal(), cliente);
                            System.out.println("Pedir ayuda a ROB? [S/N]");
                            String ayudaPelicula = scanner.nextLine().toLowerCase();
                            if(ayudaPelicula.equals("s")){
                                rob.recomendarPelicula(main, cliente);
                            }
                            continue;
                        case 2:
                            //agregar metodo para buscar pelicula
                            Pelicula peliculaABuscar = cliente.buscarPelicula(this.peliculas);
                            this.buscarPelicula(peliculaABuscar, cliente);
                            continue;
                        case 3:
                            this.seleccionarSucursal(cliente);
                            continue;
                        case 4:
                            //agregar metodo para buscar producto
                            System.out.println("Pedir ayuda a ROB? [S/N]");
                            String ayudaProducto = scanner.nextLine().toLowerCase();
                            if(ayudaProducto.equals("s")){
                                rob.ayudarCompra(main, cliente);
                            }else{
                                this.buscarProducto(cliente.getSucursal(), cliente);
                            }
                            continue;
                        case 5:
                            for(Sucursal s: this.sucursales){
                                if(s.getNombre().equals(cliente.getSucursal())){
                                    cliente.verCarrito(s, cliente);
                                    break;
                                }
                            }
                            continue;
                        case 6:
                            //agregar metodo para sobreescribir un cliente
                            continue;
                        case 7:
                            this.registrarCliente(sucursalSeleccionada, cliente);
                            continue;
                        case 8: 
                            //ver los puntos del cliente
                            cliente.verMisPuntos();
                            continue;
                        case 9: 
                            //ver las compras del cliente
                            cliente.verMisCompras();
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
    }
    
    public void menuClienteSinCuenta(CineTICs main, Persona cliente, String sucursalSeleccionada, ROB rob){
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            String seleccionUsuario;
            int selUsuarioInt= 0;
            
            //mostrar menu cuando no se ha iniciado sesion
            if(!cliente.sesionActiva()){
                while(true){
                    this.mostrarHeader(sucursalSeleccionada);
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
                        this.registrarCliente(cliente.getSucursal(), cliente);
                        System.out.println("Mostrando cartelera de la sucursal " + cliente.getSucursal());
                        this.mostrarCarteleraSucursal(cliente.getSucursal(), cliente);
                        System.out.println("Pedir ayuda a ROB? [S/N]");
                        String ayudaPelicula = scanner.nextLine().toLowerCase();
                        if(ayudaPelicula.equals("s")){
                            rob.recomendarPelicula(main, cliente);
                        }
                        continue;
                    case 2:
                        //agregar metodo para buscar pelicula
                        this.registrarCliente(cliente.getSucursal(), cliente);
                        Pelicula peliculaABuscar = cliente.buscarPelicula(this.peliculas);
                         this.buscarPelicula(peliculaABuscar, cliente);
                        continue;
                    case 3:
                        this.seleccionarSucursal(cliente);
                        continue;
                    case 4:
                        Persona respaldoCliente = cliente;
                        try {
                            cliente = this.comprobarInicioSesion();
                            
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
                        System.out.println("Pedir ayuda a ROB? [S/N]");
                        String ayudaProducto = scanner.nextLine().toLowerCase();
                        if(ayudaProducto.equals("s")){
                            rob.ayudarCompra(main, cliente);
                        }else{
                            this.buscarProducto(cliente.getSucursal(), cliente);
                        }
                        continue;
                    case 6:
                        for(Sucursal s: this.sucursales){
                            if(s.getNombre().equals(cliente.getSucursal())){
                                cliente.verCarrito(s, cliente);
                                break;
                            }
                        }
                        continue;
                    case 7:
                        //agregar metodo para sobreescribir un cliente
                        continue;
                    case 8:
                        this.registrarCliente(sucursalSeleccionada, cliente);
                        continue;
                    case 9:
                        System.out.println("Saliendo del sistema");
                        return;
                    default:
                        System.out.println("Ingrese un indice valido");
                        continue;
                }
            
            
            }
            
        }
    }
    
    public void menuAdministrador(Administrador admin, CineTICs main, ROB rob){
        Scanner scanner = new Scanner(System.in);
        while(true){
            String seleccionAdministrador;
            int selAdministradorInt = 0;
            if(admin.validarSesion()){
                while(true){
                    System.out.println("1.Actualizar Cartelera\n2.Ver Productos\n3.Actualizar Stock de Productos\n" + 
                            "\n4.Agregar Producto\n5.Ver Empleados\n"+
                            "6.Agregar Empleados\n7.Ver estadisticas de ventas\n8.Generar Reporte de Ventas\n" +
                            "9.Salir\n");
                    seleccionAdministrador = scanner.nextLine();
                        try{
                            selAdministradorInt = Integer.parseInt(seleccionAdministrador);
                        }catch(Exception e){
                            System.out.println("Entrada invalida");
                            scanner.nextLine();
                        }
                        break;
                }
                switch(selAdministradorInt){
                        case 1:
                            this.actualizarCarteleras();
                            this.cargarFunciones();
                            continue;
                        case 2:
                            this.listarProductos();
                            continue;
                        case 3:
                            this.actualizarInventarios();
                            continue;
                        case 4:
                            this.agregarProducto();
                            this.cargarProductos();
                            continue;
                        case 5:
                            this.listarEmpleados();
                            continue;
                        case 6:
                            this.agregarEmpleado();
                            continue;
                        case 7:
                            //ver estadisticas de venta
                            this.verReporteVentas();
                            continue;
                        case 8:
                            //generar reporte de ventas
                            System.out.println("Pedir ayuda a ROB? [S/N]");
                            String ayudaReporte = scanner.nextLine().toLowerCase();
                            if(ayudaReporte.equals("s")){
                                rob.ayudarEstadisticas(main);
                            }else{
                                this.generarReporteVentas();
                            }
                            continue;
                        case 9:
                            break;
                        default:
                            System.out.println("Ingrese un indice valido");
                            continue;
                    }
            }
        }
    }
    
    public void buscarPelicula(Pelicula peliculaABuscar, Persona cliente){
        Scanner scanner = new Scanner(System.in);
        Ticket nuevoTicket;
        String continuarCompra;
        
        for(Sucursal s: this.sucursales){
            for(Pelicula p: s.getCartelera()){
                if(p.getNombre().equals(peliculaABuscar.getNombre())){
                    System.out.println("La pelicula " + peliculaABuscar.getNombre() + " se encuentra en la sucursal: " + s.getNombre());
                    System.out.print("Deseas comprar boletos en esta sucursal? [S/N]: ");
                    continuarCompra = scanner.nextLine().toLowerCase();
                   
                    if(continuarCompra.equals("s")){
                        //funcion para el proceso de compra con el agregar productos de la sucursal
                        System.out.println("Redirigiendo al proceso de compra en la sucursal " + s.getNombre());
                        nuevoTicket = s.procesoCompra(p, cliente);
                        this.registrarVenta(FUNCIONES_FILE, nuevoTicket);
                        //creamos un nuevo carrito despues de la compra
                        cliente.nuevoCarrito();
                        break;
                    } else if(continuarCompra.equals("n")){
                        continue;
                    } else{
                        System.out.println("Entrada invalida");
                        
                    }
                
                }
            }
        }
        
        
        
        
    }
    
    public void buscarProducto(String nombreSucursal, Persona cliente){
        Scanner scanner = new Scanner(System.in);
        Producto productoSeleccionado;
        String continuar;
        int seleccion = 0;
        int indiceProducto = 1;
        
        for(Sucursal s: this.sucursales){
            if(s.getNombre().equals(nombreSucursal)){
                
                for(Producto p: s.getInventario()){
                    System.out.println(indiceProducto + "."+p.getNombre());
                    }
                
                while(true){
                    
                    try{
                        System.out.print("Ingrese el indice del producto que desea: ");
                        seleccion = scanner.nextInt();
                        if(seleccion-1 > s.getInventario().size()){
                            System.out.println("Indice invalido, intentelo de nuevo");
                            continue;
                        }
                        productoSeleccionado = s.getInventario().get(seleccion-1);
                        break;
                    }catch(Exception e){
                        System.out.println("Entrada invalida, intentelo de nuevo");
                        scanner.nextLine();
                    }
                }
                
                System.out.print("Proceder con la compra? [S/N]]");
                continuar = scanner.nextLine();
                if(continuar.equalsIgnoreCase("s")){
                    s.agregarAlimentos(cliente);
                }else if(continuar.equalsIgnoreCase("n")){
                    continue;
                }
                return;
                
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
    
    public void mostrarCarteleraSucursal(String sucursalSeleccionada, Persona cliente) {

        for (Sucursal sucursal : this.sucursales) {
            if (sucursal.getNombre().equals(sucursalSeleccionada)) {
                sucursal.mostrarCartelera(cliente);
                break;
            }
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
    
    private void cargarProductos(){
        try(BufferedReader br = new BufferedReader(new FileReader(PRODUCTOS_FILE))){
            String linea;
            while((linea = br.readLine()) != null){
                try{
                    
                    String[] datos = linea.split(".-/-.");
                    
                    if(datos.length != 5){
                        System.err.println("Linea mal formada: " + linea);
                        continue;
                    }
                    
                    String nombreProducto = datos[0];
                    String codigoProducto = datos[1];
                    String precioProducto = datos[2];
                    String categoriaProducto = datos[3];
                    String stockProducto = datos[4];
                    Producto nuevoProducto = new Producto(nombreProducto, codigoProducto, precioProducto, categoriaProducto, Integer.parseInt(stockProducto));
                    this.productos.add(nuevoProducto);
                }catch(NumberFormatException e){
                    System.err.println("Error de formato en la linea: " + linea);
                    e.printStackTrace();
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    private void cargarFunciones(){
        try(BufferedReader br = new BufferedReader(new FileReader(FUNCIONES_FILE))){
            String linea;
            while((linea = br.readLine()) != null){
                try{
                    
                    String[] datos = linea.split(".-/-.");
                    
                    if(datos.length != 4){
                        System.err.println("Linea mal formada: " + linea);
                        continue;
                    }
                    
                    String nombreFuncion = datos[0];
                    String clasificacionFuncion = datos[1];
                    String duracionFuncion = datos[2];
                    String directorFuncion = datos[3];
                    Pelicula nuevaPelicula = new Pelicula(nombreFuncion, clasificacionFuncion, " ", " ", directorFuncion, duracionFuncion);
                    this.peliculas.add(nuevaPelicula);
                }catch(NumberFormatException e){
                    System.err.println("Error de formato en la linea: " + linea);
                    e.printStackTrace();
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void actualizarCarteleras(){
        Scanner scanner = new Scanner(System.in);
        
        String nombre;
        String clasificacion;
        String duracion;
        String director;
        
        System.out.println();
        nombre = scanner.nextLine();
        System.out.println();
        clasificacion = scanner.nextLine();
        System.out.println();
        duracion = scanner.nextLine();
        System.out.println();
        director = scanner.nextLine();
        
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(FUNCIONES_FILE, true))) {
            // Escribiendo los datos del usuario en el siguiente formato
            // nombre.-/-.clasificacion.-/-.duracion.-/-.director
            
            writer.write(nombre + ".-/-." + clasificacion + ".-/-." + duracion + ".-/-."
                    + director + "\n");

            
            Pelicula pelicula = new Pelicula(nombre, clasificacion, " ", " ", director, duracion);
            this.peliculas.add(pelicula);
            System.out.println("Datos registrados");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void agregarProducto(){
        Scanner scanner = new Scanner(System.in);
        
        String nombre;
        String codigo;
        String precio;
        String categoria;
        String stockTotal;
        
        System.out.println("Ingrese el nombre del producto: ");
        nombre = scanner.nextLine();
        System.out.println("Ingrese el codigo del producto: ");
        codigo = scanner.nextLine();
        System.out.println("Ingrese el precio del producto: ");
        precio = scanner.nextLine();
        System.out.println("Ingrese la categoria del producto: ");
        categoria = scanner.nextLine();
        System.out.println("Ingrese el Stock total del producto: ");
        stockTotal = scanner.nextLine();
        
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCTOS_FILE, true))) {
            // Escribiendo los datos del usuario en el siguiente formato
            // nombre.-/-.codigo.-/-.precio.-/-.categoria.-/-.stockTotal
            
            writer.write(nombre + ".-/-." + codigo + ".-/-." + precio + ".-/-."
                    + categoria + ".-/-." + stockTotal + ".-/-." + "\n");

            
            Producto producto = new Producto(nombre, codigo, precio, categoria, Integer.parseInt(stockTotal));
            this.productos.add(producto);
            System.out.println("Datos registrados");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
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
        String carteleraCU = "src/cinetics/archivos/sucursalCU/carteleraCU.txt";
        String carteleraDelta = "src/cinetics/archivos/sucursalDelta/carteleraDelta.txt";
        String carteleraUniversidad= "src/cinetics/archivos/sucursalUniversidad/carteleraUniversidad.txt";
        String carteleraXochi = "src/cinetics/archivos/sucursalXochimilco/carteleraXochimilco.txt";
        
        
        //crea las carteleras
        cu.cartelera(carteleraCU);
        delta.cartelera(carteleraDelta);
        universidad.cartelera(carteleraUniversidad);
        xochi.cartelera(carteleraXochi);
        
        String inventarioCU = "src/cinetics/archivos/sucursalCU/inventarioCU.txt";
        String inventarioDelta = "src/cinetics/archivos/sucursalDelta/inventarioDelta.txt";
        String inventarioUniversidad= "src/cinetics/archivos/sucursalUniversidad/inventarioUniversidad.txt";
        String inventarioXochi = "src/cinetics/archivos/sucursalXochimilco/inventarioXochimilco.txt";
        
        cu.inventario(inventarioCU);
        delta.inventario(inventarioDelta);
        universidad.inventario(inventarioUniversidad);
        xochi.inventario(inventarioXochi);
        
        int[][] lugaresSalas = {
            {120, 150, 200}, 
            {100, 160, 175},
            {80, 100, 150},
            {90, 125, 140}} ;
        
        cu.crearSalas(lugaresSalas[0]);
        delta.crearSalas(lugaresSalas[1]);
        universidad.crearSalas(lugaresSalas[2]);
        xochi.crearSalas(lugaresSalas[3]);
        
        
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
    
    protected void listarProductos(){
        for(Producto p: this.productos){
            System.out.println(p.getCodigo() + " " + p.getNombre() + " " + p.getCategoria() + " " + p.getPrecio());
        }
    }
    
    protected void listarEmpleados(){
        for(Empleado empleado: this.empleados){
            System.out.println("Sucursal donde trabaja el empleado: " + empleado. getSucursal() + " " + 
                    empleado.getNombre() + " " + empleado.getAPaterno()
            + " " + empleado.getCorreo() + " " + empleado.getCelular() );
        }
    
    }
    
    protected void listarGerentes(){
        for(Gerente gerente: this.gerentes){
            System.out.println("Sucursal donde trabaja el gerente: " + gerente. getSucursal() + " " + 
                    gerente.getNombre() + " " + gerente.getAPaterno()
            + " " + gerente.getCorreo() + " " + gerente.getCelular() );
        }
    }
    
    protected void listarClientes(){
        for(Persona cliente: this.clientes){
            System.out.println("Sucursal del cliente: " + cliente. getSucursal() + " " + 
                    cliente.getNombre() + " " + cliente.getAPaterno()
            + " " + cliente.getCorreo() + " " + cliente.getCelular());
        }
    }
    
    protected void agregarGerente(){
        this.contTrabajadores += 1;
        Scanner scanner = new Scanner(System.in);
        
        
        String numTrabajador;
        String nombre;
        String aPaterno;
        String aMaterno;
        String direccion;
        String correo;
        String celular;
        String password;
        String noTarjeta;
        String RFC;
        String direccionFiscal;
        String tipoTrabajador;
        int selSucursal;
        String sucursal;
        
        
        System.out.println("Ingrese los datos del nuevo gerente");
        numTrabajador = "34897"+Integer.toString(this.contTrabajadores);
        System.out.print("Nombre del gerente: ");
        nombre = scanner.nextLine();
        System.out.print("Apellido paterno del gerente: ");
        aPaterno = scanner.nextLine();
        System.out.print("Apellido materno del gerente: ");
        aMaterno = scanner.nextLine();
        System.out.print("Direccion del gerente: ");
        direccion = scanner.nextLine();
        System.out.print("Correo del gerente: ");
        correo = scanner.nextLine();
        System.out.print("Celular del gerente: ");
        celular = scanner.nextLine();
        //entrada de numero de celular valido
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
        //entrada de contraseña valida
        while(true){
            System.out.print("Ingrese la contrasena del gerente (al menos 8 caracteres): ");
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
        
        
        System.out.print("Numero de tarjeta del gerente: ");
        noTarjeta = scanner.nextLine();
        System.out.print("RFC del gerente: ");
        RFC = scanner.nextLine();
        System.out.print("Direccion fiscal del gerente: ");
        direccionFiscal = scanner.nextLine();
        
        //colocar el tipo de trabajador
        tipoTrabajador = "Gerente";

        //seleccionar la sucursal de trabajo
        while(true){
            try{
                System.out.println("Seleccione la sucursal donde trabaja el gerente: ");
                System.out.println("1.CU\n2.Delta\n3.Universidad\n4.Xochimilco");
                System.out.print("Tu seleccion: ");
                selSucursal = scanner.nextInt();
                
                switch(selSucursal){
                    case 1:
                        sucursal = "CU";
                        break;
                     case 2:
                        sucursal = "Delta";
                        break;
                     case 3:
                        sucursal = "Universidad";
                        break;
                     case 4:
                        sucursal = "Xochimilco";
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
        
        //sobreescribirmos el archivo de gerentes en la sucursal elegida
        String archivoSucursales = "src/cinetics/archivos/sucursal"+sucursal+"/archivoGerentes"+sucursal;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSucursales, true))) {
            // Escribiendo los datos del usuario en el siguiente formato
            // numTrabajador.---.nombre.---.aP.---.aM.---.direccion.---.correo.---.celular.---.password
            //.---.noTarjeta.---.RFC.---.direccionFiscal.---.sucursal.---.tipoTrabajador.---.
            
            writer.write(numTrabajador + ".---." + nombre + ".---." + aPaterno + ".---." + aMaterno + ".---."
                    + direccion + ".---." + correo + ".---." + celular + ".---." + password
                    + ".---." + noTarjeta + ".---." + RFC + ".---." + direccionFiscal + ".---." +
                    sucursal + ".---." + tipoTrabajador +  "\n");
            
            
            Gerente nuevoGerente = new Gerente(numTrabajador, nombre, aPaterno, aMaterno, direccion, correo, celular, password, noTarjeta, RFC, direccionFiscal, tipoTrabajador,sucursal);
            this.gerentes.add(nuevoGerente);
            for(Sucursal s: this.sucursales){
                if(s.getNombre().equals(sucursal)){
                    s.cargarEmpleados(nuevoGerente);
                }
            }
            System.out.println("Datos registrados");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    protected void agregarEmpleado(){
        this.contTrabajadores += 1;
        Scanner scanner = new Scanner(System.in);
        
        String numTrabajador;
        String nombre;
        String aPaterno;
        String aMaterno;
        String direccion;
        String correo;
        String celular;
        String password;
        String noTarjeta;
        String RFC;
        String direccionFiscal;
        String tipoTrabajador;
        int selSucursal;
        String sucursal;
        
        
        System.out.println("Ingrese los datos del nuevo empleado");
        numTrabajador = "34897"+Integer.toString(this.contTrabajadores);
        System.out.print("Nombre del empleado: ");
        nombre = scanner.nextLine();
        System.out.print("Apellido paterno del empleado: ");
        aPaterno = scanner.nextLine();
        System.out.print("Apellido materno del empleado: ");
        aMaterno = scanner.nextLine();
        System.out.print("Direccion del empleado: ");
        direccion = scanner.nextLine();
        System.out.print("Correo del empleado: ");
        correo = scanner.nextLine();
        System.out.print("Celular del empleado: ");
        celular = scanner.nextLine();
        //entrada de numero de celular valido
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
        //entrada de contraseña valida
        while(true){
            System.out.print("Ingrese la contrasena del empleado (al menos 8 caracteres): ");
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
        
        
        System.out.print("Numero de tarjeta del empleado: ");
        noTarjeta = scanner.nextLine();
        System.out.print("RFC del empleado: ");
        RFC = scanner.nextLine();
        System.out.print("Direccion fiscal del empleado: ");
        direccionFiscal = scanner.nextLine();
        
        //colocar el tipo de trabajador
        while(true){
            try{
                int selTipoTrabajador;
                System.out.println("Seleccione el tipo de trabajo: ");
                System.out.println("1.Cajero\n2.Limpieza\n");
                System.out.print("Tu seleccion: ");
                selTipoTrabajador = scanner.nextInt();
                scanner.nextLine();
                switch(selTipoTrabajador){
                    case 1:
                        tipoTrabajador = "Cajero";
                        break;
                    case 2:
                        tipoTrabajador = "Limpieza";
                        break;
                    default:
                        System.out.println("Indice invalido, intentelo de nuevo");
                        continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Entrada invalida, intentelo de nuevo");
                scanner.nextLine();
                
            }
            
        }

        //seleccionar la sucursal de trabajo
        while(true){
            try{
                System.out.println("Seleccione la sucursal donde trabaja el gerente: ");
                System.out.println("1.CU\n2.Delta\n3.Universidad\n4.Xochimilco");
                System.out.print("Tu seleccion: ");
                selSucursal = scanner.nextInt();
                
                switch(selSucursal){
                    case 1:
                        sucursal = "CU";
                        break;
                     case 2:
                        sucursal = "Delta";
                        break;
                     case 3:
                        sucursal = "Universidad";
                        break;
                     case 4:
                        sucursal = "Xochimilco";
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
        
        //sobreescribirmos el archivo de gerentes en la sucursal elegida
        String archivoSucursales = "src/cinetics/archivos/sucursal"+sucursal+"archivoEmpleados"+sucursal;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSucursales, true))) {
            // Escribiendo los datos del usuario en el siguiente formato
            // numTrabajador.---.nombre.---.aP.---.aM.---.direccion.---.correo.---.celular.---.password
            //.---.noTarjeta.---.RFC.---.direccionFiscal.---.sucursal.---.tipoTrabajador.---.
            
            writer.write(numTrabajador + ".---." + nombre + ".---." + aPaterno + ".---." + aMaterno + ".---."
                    + direccion + ".---." + correo + ".---." + celular + ".---." + password
                    + ".---." + noTarjeta + ".---." + RFC + ".---." + direccionFiscal + ".---." +
                    sucursal + ".---." + tipoTrabajador +  "\n");
            
            Empleado nuevoEmpleado = new Empleado(numTrabajador, nombre, aPaterno, aMaterno, direccion, correo, celular, password, noTarjeta, RFC, direccionFiscal, tipoTrabajador,sucursal);
            
            this.empleados.add(nuevoEmpleado);
            for(Sucursal s: this.sucursales){
                if(s.getNombre().equals(sucursal)){
                    s.cargarEmpleados(nuevoEmpleado);
                }
            }
            System.out.println("Datos registrados");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void cargarEmpleados(){
        try(BufferedReader br = new BufferedReader(new FileReader(EMPLEADOS_FILE))){
            String linea;
            while((linea = br.readLine()) != null){
                try{
                    
                    String[] datos = linea.split("./-/.");
                    
                    if(datos.length != 13){
                        System.err.println("Linea mal formada: " + linea);
                        continue;
                    }
                    
                    String numTrabajador = datos[0]; 
                    String nombre = datos[1];
                    String aPaterno = datos[2];
                    String aMaterno = datos[3];
                    String direccion = datos[4];
                    String correo  = datos[5];
                    String celular = datos[6];
                    String password = datos[7];
                    String noTarjeta = datos[8];
                    String RFC = datos[9];
                    String direccionFiscal = datos[10];
                    String puesto = datos[11];
                    String sucursal = datos[12];
                    
                    Empleado nuevoEmpleado = new Empleado(numTrabajador, nombre, aPaterno, aMaterno, direccion, correo, celular, password, noTarjeta, RFC, direccionFiscal, puesto,sucursal);
                    
                    for(Sucursal s: this.sucursales){
                        if(s.getNombre().equals(sucursal)){
                            s.cargarEmpleados(nuevoEmpleado);
                        }
                    }
                    
                    
                    
                }catch(NumberFormatException e){
                    System.err.println("Error de formato en la linea: " + linea);
                    e.printStackTrace();
                }
            } 
        }catch(IOException e){
            e.printStackTrace();
                    }
    }
    
    private void cargarGerentes(){
        try(BufferedReader br = new BufferedReader(new FileReader(EMPLEADOS_FILE))){
            String linea;
            while((linea = br.readLine()) != null){
                try{
                    
                    String[] datos = linea.split("./-/.");
                    
                    if(datos.length != 13){
                        System.err.println("Linea mal formada: " + linea);
                        continue;
                    }
                    
                    String numTrabajador = datos[0]; 
                    String nombre = datos[1];
                    String aPaterno = datos[2];
                    String aMaterno = datos[3];
                    String direccion = datos[4];
                    String correo  = datos[5];
                    String celular = datos[6];
                    String password = datos[7];
                    String noTarjeta = datos[8];
                    String RFC = datos[9];
                    String direccionFiscal = datos[10];
                    String puesto = datos[11];
                    String sucursal = datos[12];
                    
                    Gerente nuevoEmpleado = new Gerente(numTrabajador, nombre, aPaterno, aMaterno, direccion, correo, celular, password, noTarjeta, RFC, direccionFiscal, puesto,sucursal);
                    
                    for(Sucursal s: this.sucursales){
                        if(s.getNombre().equals(sucursal)){
                            s.cargarEmpleados(nuevoEmpleado);
                        }
                    }
                    
                    
                    
                }catch(NumberFormatException e){
                    System.err.println("Error de formato en la linea: " + linea);
                    e.printStackTrace();
                }
            } 
        }catch(IOException e){
            e.printStackTrace();
                    }
    }
    
    protected void actualizarInventarios(){
        String nombreSucursal;
        for(Sucursal s:  this.sucursales){
            nombreSucursal = s.getNombre();
            s.incrementarInventario("src/cinetics/archivos/sucursal"+nombreSucursal+"/inventario"+nombreSucursal+".txt");
        }
    }
    
    public void ingresaDatosFactura(Persona cliente){
        Scanner scanner = new Scanner(System.in);
        String RFC;
        String direccionFiscal;
        
        System.out.print("Ingrese su RFC: ");
        RFC = scanner.nextLine();
        System.out.print("Ingrese su direccion fiscal: ");
        direccionFiscal = scanner.nextLine();
        
        cliente.setRFC(RFC);
        cliente.setDireccionFiscal(direccionFiscal);
        
    }
    
    public void generarTicket(ArrayList<Producto> carrito, ArrayList<Boleto> boletos, String sucursal){
        Ticket nuevoTicket = new Ticket();
        nuevoTicket.setBoletos(boletos);
        nuevoTicket.setProductos(carrito);
        nuevoTicket.imprimirTicket(sucursal);
        
        this.registrarVenta(sucursal, nuevoTicket);
    }
    
    private void generarReporteVentas(){
        Scanner scanner = new Scanner(System.in);
        int seleccion;
        
        while(true){
            try{
                System.out.println("1.Reporte por sucursal\n2.Reporte por funcion\n3.Reporte por producto\n4.Reporte total");
                seleccion = scanner.nextInt();
                switch(seleccion){
                    case 1:
                        this.reporteSucursal();
                        break;
                    case 2:
                        this.reporteFuncion();
                        break;
                    case 3:
                        this.reporteProducto();
                        break;
                    case 4:
                        this.reporteSucursal();
                        this.reporteFuncion();
                        this.reporteProducto();
                        break;
                    default: 
                        System.out.println("Ingrese un indice valido");
                        continue;
                }
                
            }catch(Exception e){
                System.out.println("Entrada invalida, intentelo de nuevo");
                scanner.nextLine();
            }
        }
        
        
        
    }
    
    private void verReporteVentas(){
        Scanner scanner = new Scanner(System.in);
        int seleccion;
        
        while(true){
            try{
                System.out.println("1.Reporte por sucursal\n2.Reporte por funcion\n3.Reporte por producto\n4.Reporte total");
                seleccion = scanner.nextInt();
                switch(seleccion){
                    case 1:
                        this.verReporteSucursal();
                        break;
                    case 2:
                        this.verReporteFuncion();
                        break;
                    case 3:
                        this.verReporteProducto();
                        break;
                    case 4:
                        this.verReporteSucursal();
                        this.verReporteFuncion();
                        this.verReporteProducto();
                        break;
                    default: 
                        System.out.println("Ingrese un indice valido");
                        continue;
                }
                
            }catch(Exception e){
                System.out.println("Entrada invalida, intentelo de nuevo");
                scanner.nextLine();
            }
        }
        
        
        
    }
    
    public Map<String, Integer> reporteFuncion(){
        Map<String, Integer> ventasPorFuncion = new HashMap<>();
        
        for (Ticket venta : this.ventas) {
            for (Boleto boletoVendido : venta.getBoletos()) {
                String nombreProducto = boletoVendido.getFuncion();
                int cantidadVendida = boletoVendido.getVenta();
                
                ventasPorFuncion.put(nombreProducto, ventasPorFuncion.getOrDefault(nombreProducto, 0) + cantidadVendida);
            }
        }
        
        // Imprimir el reporte
        
        this.reporteFunciones.add(ventasPorFuncion);
        return ventasPorFuncion;
    }
    
    public Map<String, Integer> reporteProducto() {
        Map<String, Integer> ventasPorProducto = new HashMap<>();
        
        for (Ticket venta : this.ventas) {
            for (Producto productoVendido : venta.getProductos()) {
                String nombreProducto = productoVendido.getNombre();
                int cantidadVendida = productoVendido.getVenta();
                
                ventasPorProducto.put(nombreProducto, ventasPorProducto.getOrDefault(nombreProducto, 0) + cantidadVendida);
            }
        }
        
        this.reporteProductos.add(ventasPorProducto);
        return ventasPorProducto;
    }
    
    public  Map<String, Integer>  reporteSucursal(){
     Map<String, Integer> ventasPorSucursal = new HashMap<>();
        
        for (Sucursal sucursal: this.sucursales) {
            for (Ticket  venta : sucursal.getVentas()) {
                String nombreSucursal = sucursal.getNombre();
                int cantidadVentas = sucursal.getVentas().size();
                
                ventasPorSucursal.put(nombreSucursal, ventasPorSucursal.getOrDefault(nombreSucursal, 0) + cantidadVentas);
            }
        }
        
       
        this.reporteSucursales.add(ventasPorSucursal);
        return ventasPorSucursal;
    }
    
    public void verReporteFuncion(){
        for(Map<String, Integer> ventasPorFuncion: this.reporteFunciones){
            for (Map.Entry<String, Integer> entry : ventasPorFuncion.entrySet()) {
                System.out.println("El producto " + entry.getKey() + " vendió: " + entry.getValue() + " unidades");
            }
        }
    }
    
    public void verReporteProducto(){
        for(Map<String, Integer> ventasPorProducto: this.reporteFunciones){
             // Imprimir el reporte
            for (Map.Entry<String, Integer> entry : ventasPorProducto.entrySet()) {
                System.out.println("El producto " + entry.getKey() + " vendió: " + entry.getValue() + " unidades");
            }
        }
    }
    
    public void verReporteSucursal(){
        for(Map<String, Integer> ventasPorSucursal: this.reporteSucursales){
             // Imprimir el reporte
            for (Map.Entry<String, Integer> entry : ventasPorSucursal.entrySet()) {
                System.out.println("El producto " + entry.getKey() + " vendió: " + entry.getValue() + " unidades");
            }
        }
    }
    
    public void registrarVenta(String sucursal, Ticket nuevoTicket){
        for(Sucursal s: this.sucursales){
                if(s.getNombre().equals(sucursal)){
                    s.getVentas().add(nuevoTicket);
                    break;
                }
            }

            ventas.add(nuevoTicket);
    
    
    }
    
}