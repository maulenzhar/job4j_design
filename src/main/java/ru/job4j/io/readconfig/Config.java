package ru.job4j.io.readconfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if ("".equals(line) || line.contains("#")) {
                    continue;
                }
                int entry = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '=') {
                        entry++;
                    }
                }

                String[] str = line.split("=");
                if (entry < 2
                        && str.length == 2
                        && str[0] != null
                        && str[1] != null
                        && !str[0].equals("")
                        && !str[1].equals("")
                ) {
                    values.put(str[0], str[1]);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
