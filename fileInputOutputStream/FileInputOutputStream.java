/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates¡
 * and open the template in the editor.
 */
package fileInputOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author migue
 * Programa que leera los bytes de una foto contara el total y 
 * copiara la imagen a partir de un array con los bytes
 */
public class FileInputOutputStream {
    public static void main(String[]args) throws IOException{
        // Inializamos un contador con el que sabremos el tamaño de bytes
        int contador=0;
        // Creamos un array de tipo entero con el tamaño de la imagen para almacenar todos los bytes de la imagen 
        int datos_entrada[]=new int[311296];
        
        try {
        // Creamos la instacia de FileInputStream y añadimos la ruta de la imagen    
        FileInputStream archivo_lectura=new FileInputStream("C:\\Users\\migue\\OneDrive\\Documentos\\NetBeansProjects\\Ficheros1\\src\\ADD\\imagen.jpg");
        
        boolean final_ar=false;
        //Bucle para leer byte a byte el archivo que se repita mientras es diferente a final_ar
        while(!final_ar){
            //Utilizamos el metodo read para leer todos los bytes y cuando llegue al final nos devuelve -1
            int byte_entrada=archivo_lectura.read();
            //Guardaremos los bytes siempre y cuando sea -1
            if (byte_entrada!=-1)
                datos_entrada[contador]=byte_entrada;
            
            else 
                // Nos devolvera todos los bytes que lee vuelta por vuelta
                final_ar=true;
                
                System.out.println(datos_entrada[contador]);
            
            contador++;
        }
        //Cerramos el flujo de datos
        archivo_lectura.close();
        }catch(IOException e){
            System.out.println("Error al acceder a la imagen");
        }
            System.out.println(contador);
            
            crea_fichero(datos_entrada);
        
    }
    /*
    * Metodo que se encargara de crear al fichero
    * Static porque realizamos la llamada desde el main que tambie lo es
    * Recibira por parametro el array anterior
    */
    static void crea_fichero(int datos_nuevo_fichero[]){
        try{
            // Creamos la instancia y pondremos la ruta donde queremos que se copie la imagen con un nombre diferente
            FileOutputStream fichero_nuevo= new FileOutputStream("C:\\Users\\migue\\OneDrive\\Documentos\\NetBeansProjects\\Ficheros1\\src\\ADD\\imagen_copia.jpg");
            // Bucle for para leer todas las posiciones del array
            for(int i=0;i<datos_nuevo_fichero.length;i++){
                //Cada vuelta escribira un fichero de bytes 
                //con el metodo write utilizando la informacion del array
                fichero_nuevo.write(datos_nuevo_fichero[i]);
        }
            //Cerramos el flujo
            fichero_nuevo.close();
        }catch(IOException e){
            System.out.println("Error al crear e archivo");
        }
    }
}

