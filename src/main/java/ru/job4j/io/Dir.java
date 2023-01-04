package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        /*File file = new File("c:\\projects");*/
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            String format = String.format("name: %s, size: %s", subfile.getName(), subfile.length());
            System.out.println(format);
        }
    }
}
