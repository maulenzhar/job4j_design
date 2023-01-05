package ru.job4j.io.zip;

import ru.job4j.io.args.ArgsName;
import ru.job4j.io.scanfiles.Search;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.Path;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        /*zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );*/
        ArgsName jvm = ArgsName.of(args);
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }
        if (!new File(jvm.get("d")).isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",  args[0]));
        }

        Path path = Paths.get(jvm.get("d"));
        List<File> search = Search.search(path, p -> !p.toFile().getName().endsWith(jvm.get("e")))
                .stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        zip.packFiles(search, new File(jvm.get("o")));
    }
}
