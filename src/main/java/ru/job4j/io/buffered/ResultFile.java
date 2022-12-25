package ru.job4j.io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/*буфер записи*/
public class ResultFile {
    public static void main(String[] args) {
        /*Первая обертка - это BufferedOutputStream. Это буфер, который собираем переданные
        в него байты. Аккумулирует их и постепенно отдает их в FileOutputStream. В этом случае
        программа не блокируется до тех пока в буфере есть место.
        Вторая обертка над буфером - это PrintWriter. Мы знаем, что будем записывать текст. В Java
        есть удобное АПИ для этого, например, PrintWriter поддерживает метод println() для записи данных
         с последующим переходом на новую строку.*/
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("result.txt")
                ))) {
            out.println("Hello, world!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
