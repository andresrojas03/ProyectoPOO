/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinetics.personas;

/**
 *
 * @author Andres
 */
import java.util.*;
public class Administrador {
    private String usuario = "administrador1234";
    private String psswrd = "^@(cBHP8dq76!P%E";
    
    public boolean validarSesion(){
        Scanner scanner = new Scanner(System.in);
        String usuario;
        String pssword;
        System.out.print("Ingrese el usuario: ");
        usuario = scanner.nextLine();
        System.out.print("Ingrese la contrasena: ");
        pssword = scanner.nextLine();
        if(this.usuario.equals(usuario) && this.psswrd.equals(pssword)){
            return true;
        }else{
            return false;
        }
    }
}
