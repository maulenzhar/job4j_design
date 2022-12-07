package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("result.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] nums = text.toString().split(System.lineSeparator());
            for (String num : nums) {
                if (Integer.parseInt(num) % 2 == 0)
                System.out.println("четное " + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
