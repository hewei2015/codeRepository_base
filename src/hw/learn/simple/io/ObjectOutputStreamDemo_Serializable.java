package hw.learn.simple.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 示范ObjectOutputStream
 * */
public class ObjectOutputStreamDemo_Serializable{
    public static void main(String[] args) throws IOException{
        File file = new File("d:" + File.separator + "hello.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                file));
        oos.writeObject(new Person_Serializable("rollen", 20));
        oos.close();
    }
}