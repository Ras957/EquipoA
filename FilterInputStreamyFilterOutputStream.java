/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Main que usa filter
 *
 * @author Lidia Garrido Moreno
 * @version 1.0
 */
public class FilterInputStreamyFilterOutputStream {

    public static void main(String[] args) throws IOException {

        // OutputStream, FileInputStream, FilterOutputStream  y FilterOutputStream
        // se inicializa a null 
        OutputStream out = null;
        FilterOutputStream filter = null;
        FileInputStream input = null;

        //Caracteres en ASCII
        byte[] buffer = {65, 66, 76, 73, 68, 73, 65};
        try {
            // se crean los output streams 
            out = new FileOutputStream("ABC.txt");
            filter = new FilterOutputStream(out);

            // escribe el búfer en la secuencia de salida
            // el 2 no leerá los primeros dos bytes
            // el 5 imprimirá maximo 5 caracteres 
            filter.write(buffer, 2, 5);

            // obliga a los contenidos de bytes a escribirse en la secuencia 
            filter.flush();

            // se crea los input streams (para leer)
            input = new FileInputStream("ABC.txt");
            FilterInputStream filterIn = new BufferedInputStream(input);

            int i = 0;
            while ((i = filterIn.read()) != -1) {
                System.out.print((char) i);
            }

        } catch (IOException e) {
            // ocurre cualquier I/O error
            System.out.print("Error al escribir");
        } finally {
            // libera cualquier recurso del sistema asociado
            if (out != null) {
                out.close();
            }
            if (filter != null) {
                filter.close();
            }
            if (input != null) {
                input.close();
            }

        }
    }
}
