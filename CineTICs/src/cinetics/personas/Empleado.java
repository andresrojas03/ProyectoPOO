/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.personas;

/**
 *
 * @author Andres
 */
public class Empleado extends Persona {
    
    private String numTrabajador = "";
    private String tipoTrabajador = "";
    private String sucursalTrabajo = "";
    
    public Empleado(String numTrabajador, String nombre, String aPaterno, String aMaterno, String direccion, String correo, String celular, String password, String noTarjeta, String RFC, String direccionFiscal, String tipoTrabajo, String sucursalTrabajo){
        this.numTrabajador = numTrabajador;
        this.setNombre(nombre) ;
        this.setAPaterno(aPaterno);
        this.setAMaterno(aMaterno);
        this.setDireccion(direccion);
        this.setCorreo(correo);
        this.setCelular(celular);
        this.setPassword(password);
        this.setNoTarjeta(noTarjeta);
        this.setRFC(RFC);
        this.setDireccionFiscal(direccionFiscal);
        this.tipoTrabajador = tipoTrabajo;
        this.sucursalTrabajo = sucursalTrabajo;
        
    }
    
    public void setNumTrabajador(String numTrabajador){
        this.numTrabajador = numTrabajador;
    }
    
    public void setTipoTrabajador(String tipoTrabajador){
        this.tipoTrabajador = tipoTrabajador;
    }
    
    public void setSucursalTrabajo(String sucursalTrabajo){
        this.sucursalTrabajo = sucursalTrabajo;
    }
    
    public void atenderCliente(){
        System.out.println("Hola, soy " + this.getNombre() + " cual es su orden?");
        
        
        
        
        
        
    }

    
    public void entregarOrden(){
        //mostramos en pantalla la orden del cliente además de entregar el ''ticket de venta'' llamamos finalmente a
        //la función registrarVenta
        
    }
    
    public void registrarVenta(){
        //lamamos al metodo registrar venta de la clase caja para que escriba el archivo de ventas con la sucursal
        //y los datos del ticket de la venta realizada
        
    }
    
}
