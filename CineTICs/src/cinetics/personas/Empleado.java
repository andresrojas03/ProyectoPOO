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
    
    public void setNumTrabajador(String numTrabajador){
        this.numTrabajador = numTrabajador;
    }
    
    public void setTipoTrabajador(String tipoTrabajador){
        this.tipoTrabajador = tipoTrabajador;
    }
    
    public void setSucursalTrabajo(String sucursalTrabajo){
        this.sucursalTrabajo = sucursalTrabajo;
    }
    
}
