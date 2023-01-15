package ru.job4j.io.scanner;

import ru.job4j.io.args.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class CSVReader {
    private static String pathArg;
    private static String delimiterArg;
    private static String outArg;
    private static String filterArg;

    private static void isValid(ArgsName argsName) {
        pathArg = argsName.get("path");
        delimiterArg = argsName.get("delimiter");
        outArg = argsName.get("out");
        filterArg = argsName.get("filter");

        Path path = Paths.get(pathArg);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", pathArg));
        }
        Pattern patternDelimetr = Pattern.compile("\\W");
        if (!patternDelimetr.matcher(delimiterArg).find()) {
            throw new IllegalArgumentException(String.format("No such delimiter %s", delimiterArg));
        }
        Pattern patternOutput = Pattern.compile("^.*\\.csv$");
        if (!patternOutput.matcher(outArg).find() && !"stdout".equals(outArg)) {
            throw new IllegalArgumentException(String.format("No such output %s", outArg));
        }
        String[] filter = filterArg.split(",");
        for (String f : filter) {
            if (!"name".equals(f) && !"age".equals(f) && !"last_name".equals(f) && !"education".equals(f)) {
                throw new IllegalArgumentException(String.format("No such filter %s", filterArg));
            }
        }
    }

    private static List<Integer> getFilteredIndex(String filter, List<String[]> list) {
        String[] listOfFilterVal = filter.split(",");
        String[] listOfMainVal = list.get(0);
        List<Integer> filterIndx = new ArrayList<>();
        for (String filterVal : listOfFilterVal) {
            for (int i = 0; i < listOfMainVal.length; i++) {
                if (listOfMainVal[i].equals(filterVal)) {
                    filterIndx.add(i);
                }
            }
        }
        return filterIndx;
    }

    private static List<StringJoiner> getFilteredData(String delimiter, List<String[]> list, List<Integer> filterIndx) {
        StringJoiner str = new StringJoiner(delimiter);
        List<StringJoiner> resOfFiltered = new ArrayList<>();
        for (String[] res : list) {
            for (Integer j : filterIndx) {
                for (int i = 0; i < res.length; i++) {
                    if (i == j) {
                        str.add(res[i]);
                        if ((filterIndx.get(filterIndx.size() - 1) == j)) {
                            resOfFiltered.add(str);
                            str = new StringJoiner(delimiter);
                        }
                        break;
                    }
                }
            }
        }
        return resOfFiltered;
    }

    private static void output(String out, List<StringJoiner> str) {
        if ("stdout".equals(out)) {
            str.forEach(r -> System.out.println(r.toString()));
        } else {
            try (PrintWriter outToCsv = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
                str.forEach(r -> outToCsv.println(r.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        isValid(argsName);

        List<String[]> list = new ArrayList<>();
        FileInputStream source = new FileInputStream(pathArg);
        try (var scanner = new Scanner(source).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                list.add(scanner.next().split(delimiterArg));
            }
        }

        List<Integer> filterIndx = getFilteredIndex(filterArg, list);
        List<StringJoiner> data = getFilteredData(delimiterArg, list, filterIndx);
        output(outArg, data);
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}
