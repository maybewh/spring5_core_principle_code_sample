package com.wanghui.design.pattern.singleton;

import java.io.*;

public class SerializableTest {

    public static void main(String[] args) throws IOException {

        SerializableSingleton s1 = null;
        SerializableSingleton s2 = SerializableSingleton.getInstance();

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;



        try {

            fos = new FileOutputStream("Serializable.obj");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);

            FileInputStream inputStream = new FileInputStream("Serializable.obj");
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            s1 = (SerializableSingleton) ois.readObject();

            inputStream.close();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }

            if (oos != null) {
                oos.close();
            }
        }
    }

}
