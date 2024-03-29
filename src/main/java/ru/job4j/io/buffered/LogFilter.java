package ru.job4j.io.buffered;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> res = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] str = line.split(" ");
                if ("404".equals(str[str.length - 2])) {
                    res.add(line + System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> logs = logFilter.filter("log.txt");
        for (String log : logs) {
            System.out.println(log);
        }
    }

}