package equipoa;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 *
 * @author Juan Antonio
 */
public class PipedStreams {
    public static void main(String[] args) {
        PipedInputStream input = new PipedInputStream();
        PipedOutputStream output = new PipedOutputStream();
        try {
            /**
             * Conectamos el input con el output. El input se encargará de leer
             * los datos
             */
            input.connect(output);
            for (int i = 65; i < 91; i++) {
                //Con el método write() introducimos los bytes.
                output.write(i);
            }
            //Con el método flush() cerramos el stream y forzamos a que los bytes sean escritos
            output.flush();
            //Cerramos la conexión.
            output.close();
            int b;
            /*Con el método read vamos leyendo todos los bytes.
            El método read devolverá el siguiente byte cada vez que lo llamemos,
            hasta que no haya más bytes y devuelva -1
             */
            while ((b = input.read()) != -1) {
                System.out.println((char) b);
                //El métoo available() nos devuelve el número de bytes que quedan por leer
                System.out.println("Quedan " + input.available() + " bytes");
            }
            //Con el método close() cerramos la conexión
            input.close();
        } catch (IOException excpt) {
            excpt.printStackTrace();
        }
    }
}