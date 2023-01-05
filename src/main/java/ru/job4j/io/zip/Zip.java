package ru.job4j.io.zip;

import ru.job4j.io.args.ArgsName;
import ru.job4j.io.scanfiles.Search;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.Path;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArgsName isValidate(String[] args) {

        ArgsName jvm = ArgsName.of(args);
        String e = jvm.get("e");
        String o = jvm.get("o");
        String d = jvm.get("d");
        Pattern patternExclude = Pattern.compile("\\S+");
        Pattern patternOutput = Pattern.compile("^.\\S*.\\.zip");

        if (!patternExclude.matcher(jvm.get("e")).find()) {
            throw new IllegalArgumentException(String.format("No such exclude %s", e));
        }
        if (!patternOutput.matcher(o).find()) {
            throw new IllegalArgumentException(String.format("No such output %s", o));
        }
        if (!new File(d).isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", d));
        }

        return jvm;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }
        Zip zip = new Zip();
        /*zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );*/
        ArgsName jvm = isValidate(args);

        Path path = Paths.get(jvm.get("d"));
        List<Path> search = Search.search(path, p -> !p.toFile().getName().endsWith(jvm.get("e")));
        zip.packFiles(search, new File(jvm.get("o")));
    }


}
