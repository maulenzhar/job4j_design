package ru.job4j.io.scanfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {
    public static Path path;
    public static String extension;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        isValid(args);
        /*Files.walkFileTree(start, new PrintFiles());*/
        search(path, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    private static void isValid(String[] args) {
        path = Paths.get(args[0]);
        extension = args[1];
        File file = Search.path.toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", args[0]));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",  args[0]));
        }
        Pattern pattern = Pattern.compile("\\..*");
        Matcher matcher = pattern.matcher(extension);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("No such extension %s", extension));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
