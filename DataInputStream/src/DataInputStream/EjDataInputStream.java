/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataInputStream;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DATAINPUTSTREAM
 * La clase DataInputStream es útil para leer datos del tipo primitivo de una forma portable. 
 * Esta clase tiene un sólo constructor que toma un objeto de la clase InputStream o sus derivadas como parámetro.
 * DataInputStream < FilterInputStream < InputStream < Object.
 * @author Samuel Hermosilla Aguilera
 */
public class EjDataInputStream {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException { 
        InputStream inputS = null;
        DataInputStream distream = null;
        try{
            
        
        //Se crea un objeto de la clase DataInputStream vinculándolo a un un objeto FileInputStream para leer desde un archivo.txt
        inputS = new FileInputStream("ejemplo.txt"); 
        distream = new DataInputStream(inputS);  
        //DataInputStream distream = new DataInputStream(new FileInputStream("ejemplo.txt"));
        
        //El método available() devuelve una estimación del número de bytes que se pueden leer.
        int count = inputS.available();
        System.out.println("El fichero tiene "+count+" bytes");
        
        //ByteArray con la longitud que nos devuelve el contador realizado al InputStream.
        byte[] barray = new byte[count];  
        
        //El método read() lee el siguiente byte de datos de la secuencia de entrada.
        distream.read(barray); 
        
        /*
          *Foreach que recorre el ByteArray encontrando todos los bytes.
          *Dentro del for se crea una variable char y parseamos la variable byte a esa char creada anteriormente
           para poder imprimir por pantalla los caracteres por pantalla.
        */
        for (byte byt : barray) { 
          char ch = (char) byt;  
          System.out.print(ch);  
        }
        
        }catch(IOException ex){
            Logger.getLogger(EjDataInputStream.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(distream!=null){
               distream.close(); 
            }
            
            if(inputS!=null){
                inputS.close();
            }
        }    
    }
}
