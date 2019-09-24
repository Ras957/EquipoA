/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectstreamexample;
import java.io.*;
/**
 *
 * @author jlove
 */
public class ObjectStreamExample {
    /**
     * clase persona que es el tipo de objeto que vamos a leer y escribir.
     */
    public static class Person implements Serializable {
        public String name = null;
        public int    age  =   0;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * Uso de ObjectOutputStream:
         * 
         */
        ObjectOutputStream oOS =
            new ObjectOutputStream(new FileOutputStream("person.bin"));

        Person person = new Person();
        person.name = "Jaime Lovera";
        person.age  = 23;

        oOS.writeObject(person);
        oOS.close();


        ObjectInputStream oIS =
            new ObjectInputStream(new FileInputStream("data/person.bin"));

        Person personRead = (Person) oIS.readObject();

        oIS.close();

        System.out.println(personRead.name);
        System.out.println(personRead.age);
    }
}
