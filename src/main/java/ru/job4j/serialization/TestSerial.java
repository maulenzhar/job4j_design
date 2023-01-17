package ru.job4j.serialization;

import java.io.*;

class TestSerial implements Serializable {
    public byte version = 100;
    public byte count = 0;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("./data/temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        TestSerial tsWrite = new TestSerial();
        /*сериализация делается вызовом метода writeObject()*/
        oos.writeObject(tsWrite);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("./data/temp.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        /*восстановление объекта происходит с помощью вызова метода oin.readObject()*/
        TestSerial tsRead = (TestSerial) oin.readObject();
        System.out.println("version=" + tsRead.version);
    }
}