/*
 * Un Streams es un medio utilizado para leer datos de una fuente y para escribir datos en un destino.
 * Los buffers son subclases que cumplen el mismo objetivo, pero son más eficientes
 * ya que tienen como objetivo guardar en un buffer los caracteres de lectura/escritura
 */
package streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Francisco
 */
public class Buffereds {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
      leerBufferedInputStream();
      leerBufferedOutputStream();
   }
    /*El objetivo de la clase BufferedInputStream es le mismo que el de la clase
    BufferedReader, la diferencia radica en que el tratamiento del información es
    a nivel bytes, y no a nivel caracteres. */
    private static void leerBufferedInputStream() throws IOException {
        InputStream inStream = null;
        BufferedInputStream bis = null;

        try {
          //BUFFEREDINPUTSTREAM
         // abre test.txt para leerlo
         inStream = new FileInputStream("test.txt");

         // convierte el FleInputStream en BufferedInputStream
         bis = new BufferedInputStream(inStream);

         // lee siempre que haya un byte disponible
         while(bis.available()>0) {

            // lee el byte y lo convierte de integer a character
            char c = (char)bis.read();

            // imprime por pantalla los caracteres
            System.out.println("Char: "+c);
         }
         } catch(Exception e) {
         // excepciones
         e.printStackTrace();
      } finally {
         // cierre de todos los streams dentro
         // de un finally para que lo haga siempre.
         if(inStream!=null)
            inStream.close();
         if(bis!=null)
            bis.close();
      }
    }

    /* El objetivo de la clase BufferedOutputStream es le mismo que el de la clase
    BufferedWriter, la diferencia radica en que el tratamiento del información
    es a nivel bytes, y no a nivel caracteres. */
    private static void leerBufferedOutputStream() throws IOException {
        BufferedOutputStream bos = null;
        try{
            // BUFFEREDOUTPUTSTREAM
         // abrimos test2.txt para escribir en el archivo haciendo la conversion
         bos = new BufferedOutputStream(new FileOutputStream("test2.txt"));
         // el texto a escribir será el siguiente:
         bos.write("Hola Mundo!".getBytes());
         bos.write(System.lineSeparator().getBytes());
         bos.write("Esto es un ejemplo de BufferedOutputStream".getBytes());

        } catch(Exception e) {
         // excepciones
         e.printStackTrace();
      } finally {
         // cierre de todos los streams dentro
         // de un finally para que lo haga siempre.
            bos.close();
      }
    }
}
