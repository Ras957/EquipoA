/*
 * La clase RandomAccessFile se utiliza para acceder a un fichero de forma aleatoria.
 * Esta clase trata el archivo como un array de Bytes, pudiendo escribir y leer datos
 * desde cualquier posición de ese array. Para hacerlo utiliza un puntero que guarda
 * la posición actual.
 * 
 * Alumno: Jesús David Chicano Galindo
 */

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Jesús David Chicano Galindo
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Para que el ejemplo se corresponda con los comentarios hay que crear un
        //archivo de texto cuyo contenido sea "ABCDEFGH" sin las comillas

        try {
            //El contenido del archivo es "ABCDEFGH"
            String filePath = "/Users/mbp/Downloads/file.txt"; //Cargamos la ruta

            System.out.println(new String(readCharsFromFile(filePath, 1, 5)));

            writeData(filePath, "Data", 5);
            //Ahora el contenido del archivo es "ABCDEData"

            appendData(filePath, "HOLA");
            //Ahora el contenido del archivo es "ABCDEDataHOLA"
            
            byte[] bytes = readCharsFromFile(filePath, 5, 4);
            String decoded = new String(bytes);
            System.out.println(decoded);
            //Leemos la palabra Data --> desde la posicion 5 leemos 4 caracteres
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Método para añadir texto al final del archivo
     * @param filePath es la ruta del archivo
     * @param data es el contenido que queramos agregar
     * @throws IOException en caso de error
     */
    private static void appendData(String filePath, String data) throws IOException {
        RandomAccessFile raFile = new RandomAccessFile(filePath, "rw"); //Lo abrimos en modo lectura/escritura
        raFile.seek(raFile.length());
        System.out.println("current pointer = " + raFile.getFilePointer());
        raFile.write(data.getBytes());
        raFile.close();
    }

    /**
     * Método para escribir en el archivo
     * @param filePathes es la ruta del archivo
     * @param data es el contenido que queramos agregar
     * @param seek es la posición donde queremos escribir
     * @throws IOException en caso de error
     */
    private static void writeData(String filePath, String data, int seek) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw"); //Lo abrimos en modo lectura/escritura
        file.seek(seek);
        file.write(data.getBytes());
        file.close();
    }

    /**
     * Método para leer caracteres del archivo
     * @param filePath es la ruta del archivo
     * @param seekk es la posición donde queremos leer
     * @param chars
     * @return un array de tipo <code> byte </code> con los caracteres leídos
     * @throws IOException en caso de error
     */
    private static byte[] readCharsFromFile(String filePath, int seek, int chars) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "r"); //Lo abrimos en modo lectura
        file.seek(seek);
        byte[] bytes = new byte[chars];
        file.read(bytes);
        file.close();
        return bytes;
    }

}
