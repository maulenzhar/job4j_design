package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.AbstractCache;
import ru.job4j.gc.cache.DirFileCache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {
   
    public static void main(String[] args) {
        AbstractCache<String, String> abstractCache = null;
        String content = "";
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            showMenu();
            int select = Integer.parseInt(scanner.nextLine());
            if (select == 0) {
                System.out.print("указать директорию: ");
                String dir = scanner.nextLine();
                Path path = Paths.get("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\gc\\cache\\" + dir);
                abstractCache = new DirFileCache(path.toString() + "\\");
            } else if (select == 1) {
                System.out.print("загрузить содержимое файла в кэш, указать файл:");
                String file = scanner.nextLine();
                content = abstractCache.get(file);
                if (content.length() != 0) {
                    System.out.print("Успешно загружено".concat(System.lineSeparator()));
                }
            } else if (select == 2) {
                System.out.println(content);
            } else if (select == 3) {
                run = false;
            }
        }
    }

    private static void showMenu() {
        String[] menu = {
                "указать кэшируемую директорию", "загрузить содержимое файла в кэш", "получить содержимое файла из кэша", "Выход"
        };
        System.out.println("Menu.");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
    }
}
