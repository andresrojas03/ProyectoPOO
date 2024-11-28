/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.personas;

/**
 *
 * @author Andres
 */

import cinetics.sistema.Producto;
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
    private ArrayList<Producto> carrito = new ArrayList<Producto>();
    
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
    
    
    public void iniciarSesion(){}
    
    public void registroProgramaLealtad(){}
    
    public void buscarPelicula(){}
    
    public void verCartelera(){}
    
    public void verMisPuntos(){}
    
    public void verMisCompras(){}
    
    public void verCarrito(){}
    
    public void realizarCompra(){}
    
    
    
    
    
}
