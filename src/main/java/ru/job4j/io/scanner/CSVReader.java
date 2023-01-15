package ru.job4j.io.scanner;

import ru.job4j.io.args.ArgsName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
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
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");

        List<String[]> list = new ArrayList<>();
        FileInputStream source = new FileInputStream(path);
        try (var scanner = new Scanner(source).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                list.add(scanner.next().split(delimiter));
            }
        }

        List<Integer> filterIndx = getFilteredIndex(filter, list);
        List<StringJoiner> data = getFilteredData(delimiter, list, filterIndx);
        output(out, data);
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}
