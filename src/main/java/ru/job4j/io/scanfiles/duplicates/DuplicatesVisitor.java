package ru.job4j.io.scanfiles.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<Path> paths = new ArrayList<>();
    private Map<FileProperty, List<Path>> result = new HashMap<>();
    private Set<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString(), file.toAbsolutePath());

        if (!files.add(fileProp)) {
            paths.add(fileProp.getPath());
            result.put(fileProp, paths);
        }

        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getResult() {

        List<FileProperty> firstFile = files.stream().filter(file -> result.containsKey(file)).collect(Collectors.toList());

        for (FileProperty file : firstFile) {
            paths.add(file.getPath());
            result.put(file, paths);
        }

        return result;
    }


}

