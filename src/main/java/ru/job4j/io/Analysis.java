package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            int i = 0;
            String res = "";
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] str = line.split(" ");
                if (i == 0 && ("400".equals(str[0]) || "500".equals(str[0]))) {
                    res = str[1] + ";";
                    i += 1;
                    continue;
                }
                if (i == 1 && (!"400".equals(str[0]) && !"500".equals(str[0]))) {
                    i = 0;
                    res += str[1] + ";";
                    out.println(res);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/server.log", "./data/target.csv");
    }

}
