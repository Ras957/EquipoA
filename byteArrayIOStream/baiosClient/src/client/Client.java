package client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) throws Exception {

        if (args.length == 2) {
            String path = args[0];

            Socket clientSocket = null;
            OutputStream outputStream = null;
            try {
                clientSocket = new Socket("localhost", 2050);

                //Obtenermos un OutputStream Objeto de el socket definido previamente.
                outputStream = clientSocket.getOutputStream();

                /*Aqui definimos la clase a estudiar. Es definida en este ejemplo,
                ya que como veremos líneas mas adelante es el wrapper más cercano
                al tipo de implementación que deseamos ya que, queremos transmitir
                la imagen en bytes y esta clase trabaja con ellos.*/
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //Aqui vemos un objeto BufferedImage el cuál contendrá nuestra imagen.                
                BufferedImage image = ImageIO.read(new File(args[0]));

                /*A continuación escribimos una imágen gracias al BufferedImage,
                a la extensión de la imágen(args[1]) en nuestro byteArrayOutputStream.*/
                ImageIO.write(image, args[1], baos);

                /*En este momento extraemos un byteArray de nuestro byteArrayOutputStream
                y lo enviamos a traves del socket*/
                outputStream.write(baos.toByteArray());

            } catch (UnknownHostException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            } catch (IOException ix) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ix);
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                    if (clientSocket != null) {
                        clientSocket.close();
                    }
                }
            }
        } else {
            throw new Exception("Formato: client <ruta> <formato>");
        }
    }
}
