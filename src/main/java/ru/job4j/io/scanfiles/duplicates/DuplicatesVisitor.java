package ru.job4j.io.scanfiles.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> result = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString());
        result.putIfAbsent(fileProp, new ArrayList<>());
        result.get(fileProp).add(file);
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getResult() {
        return result.entrySet().stream()
                .filter(v -> v.getValue().size() > 1)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

    public void printResult(DuplicatesVisitor visitor) {
        for (Map.Entry<FileProperty, List<Path>> f : visitor.getResult().entrySet()) {
            String r = String.format("%s - %s", f.getKey().getName(), f.getKey().getSize());
            System.out.println(r);
            f.getValue().forEach(System.out::println);
        }
    }
}

