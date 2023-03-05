package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DebugCity {

    private int id;
    private String name;
    private int population;

    public DebugCity(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public DebugCity(int id, String name, int population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    @Override
    public String toString() {
        return String.format("City{id= %s, name= %s, population= %s}",
                id, name, population);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}

class DebugDemo {
    private Connection con;

    public DebugDemo() throws Exception {
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/test5";
        String login = "postgres";
        String password = "root";
        con = DriverManager.getConnection(url, login, password);
    }

    public void createTable() {
        try (Statement statement = con.createStatement()) {
            statement.execute(
                    "create table if not exists cities(id serial primary key, name text, population int);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DebugCity insert(DebugCity city) {
        try (PreparedStatement statement = con.prepareStatement(
                "insert into cities(name, population) values (?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public List<DebugCity> findAll() {
        List<DebugCity> cities = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement("select * from cities;");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                cities.add(new DebugCity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("population")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws Exception {
        DebugCity first = new DebugCity("DebugCityOne", 100);
        DebugCity second = new DebugCity("DebugCityTwo", 200);
        DebugDemo debugDemo = new DebugDemo();
        debugDemo.createTable();
        debugDemo.insert(first);
        debugDemo.insert(second);
        for (DebugCity city : debugDemo.findAll()) {
            System.out.println("city = " + city);
        }
    }
}
