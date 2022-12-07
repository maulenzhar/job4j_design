package ru.job4j.io;

import java.io.FileOutputStream;

public class Multiple {
    public static void main(String[] args) {
        try {
            FileOutputStream out = new FileOutputStream("result.txt");
            for (int i = 0; i < 10; i++) {
                int res = 2 * i;
                out.write(String.valueOf(res).getBytes() );
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
