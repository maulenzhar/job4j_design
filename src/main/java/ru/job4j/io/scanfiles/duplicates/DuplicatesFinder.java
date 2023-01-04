package ru.job4j.io.scanfiles.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./data"), visitor);
        for (Map.Entry<FileProperty, List<Path>> f : visitor.getResult().entrySet()) {
            String r = String.format("%s - %s", f.getKey().getName(), f.getKey().getSize());
            System.out.println(r);
            f.getValue().forEach(System.out::println);
        }
    }
}