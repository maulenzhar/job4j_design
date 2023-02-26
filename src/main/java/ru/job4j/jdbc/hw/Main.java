package ru.job4j.jdbc.hw;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
            TableEditor tableEditor = new TableEditor(properties);

            String tableName = "createdTestTable";

            tableEditor.createTable(tableName);
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.addColumn(tableName, "name", "text");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.renameColumn(tableName, "name", "new_name");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.dropColumn(tableName, "new_name");
            System.out.println(tableEditor.getTableScheme(tableName));

            tableEditor.dropTable(tableName);
            System.out.println(tableEditor.getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
