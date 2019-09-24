/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printstream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import static java.sql.DriverManager.println;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PRINTSTREAM
 * La clase PrintStream de Java, nos permite agregar la capacidad de imprimir datos a una corriente (flujo) de datos determinada.
 * La clase Printstream de Java funciona con archivos para recuperar información y generar los resultados para el usuario.
 * Un aspecto único de printstream al generar texto es que no crea una excepción si ocurre un error. 
 * Si la salida de datos falla, un error del compilador interno es registrado, pero el usuario no ve ninguna excepción. 
 * Usar la clase printstream tan solo requiere unas cuantas líneas de código Java.
 * PrintStream < FilterOutputStream < OutputStream < Object.
 * @author Samuel Hermosilla Aguilera
 */
public class EjPrintStream {

    /**
     * Este programate permite escribir por la terminal y guardar lo escrito en un archivo.txt
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        PrintStream ps=null;
        FileOutputStream fos=null;
        boolean isOpen = false;
        
        try{
        //FileOutputStream es para recuperar los datos de archivos.
        fos= new FileOutputStream("ejemplo2.txt");
        isOpen=true;
        
        //PrintStream se utiliza para desplegar los resultados.
        ps = new PrintStream(fos);
        
        System.out.println ("Introduzca por teclado lo que quieras guardar el el archivo.txt");

        String entradaTeclado = "";

        //Creación de un objeto Scanner.
        Scanner entradaEscaner = new Scanner (System.in); 
        
        //Invocamos un método sobre un objeto Scanner.
        entradaTeclado = entradaEscaner.nextLine (); 
        
        //imprime lo que has escrito por teclado.
        ps.println(entradaTeclado);
        
        //Imprime la dirección de memoria donde esta creado el objeto.
        ps.println(fos);
        
        }catch(IOException ex){
            Logger.getLogger(EjPrintStream.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps!=null){
               ps.close();
            }
            
            if(isOpen==true){
                fos.close();
            }    
        }       
    } 
}
