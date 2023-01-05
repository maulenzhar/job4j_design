package ru.job4j.io.args;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String res = values.get(key);
        if (res == null) {
            throw new IllegalArgumentException();
        }
        return res;
    }

    private void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            isValidate(args, i);
            String[] str = args[i].split("=", 2);
            values.put(str[0].split("-", 2)[1], str[1]);
        }
    }

    private void isValidate(String[] args, int i) {
        Pattern pattern = Pattern.compile("^-.\\S*=.\\S*$");
        Matcher matcher = pattern.matcher(args[i]);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("No such extension %s", args[i]));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
