package server;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        InputStream inputStream = null;

        try {
            // Creamos un servidor el cuál espera una petición
            serverSocket = new ServerSocket(2050);

            clientSocket = serverSocket.accept();

            inputStream = clientSocket.getInputStream();

            /*En este bloque se crea un ByteArrayOutputStream contenedor de bytes
            para posteriormente obtener de él los bytes a escribir cómo imagen*/
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int reads = 0;
            while ((reads = inputStream.read()) != -1) {
                baos.write(reads);
            }

            /*A continuación creamos un byteArrayInputStream a partir de nuestro baos
            que nos servirá tanto para leer los bytes que representan la imagen
            junto con su posible extensión*/
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

            /*Leemos a través del ByteArrayInputStream los bytes 
            escritos anteriormente y los agregamos a un BufferedImage.*/
            BufferedImage image = ImageIO.read(bais);
            
            /*Utilizamos el método reset para situar el marcador en la posicion 0
            y poder leer de nuevo todo el byteArrayInputStream*/
            bais.reset();

            /*En el siguiente bloque obtenemos el posible contenido de nuestro 
            ByteArrayInputStream con el método URLConnection.guessContentTypeFromStream(InputStream is)
            método no fiable aunque nos bastará para esta práctica.
            Para un uso mas correcto usar el método FilenameUtils.getExtension 
            del paquete Apache Commons IO.*/
            String contentType = URLConnection.guessContentTypeFromStream(bais);
            /*La siguiente línea es necesaria ya que ya que el formato devuelto de
            contentType sera image/<formato>*/
            String[] format = contentType.split("/");

            /*A continuación creamos la imagen obteniendo los bytes del búfer, añadiendo la extension y 
            asignando la ruta del archivo.*/
            ImageIO.write(image, format[1], new File("ejemplo." + format[1]));

        } catch (UnknownHostException uhe) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, uhe);
        } catch (IOException ix) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ix);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                    if (clientSocket != null) {
                        clientSocket.close();
                        if (serverSocket != null) {
                            serverSocket.close();
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
