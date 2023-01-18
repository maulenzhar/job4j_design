package ru.job4j.serialization.json.hw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.json.Person;

import java.util.Arrays;

public class Car {
    private final boolean isSport;
    private final int year;
    private final String name;
    private final FuelType fuelType;
    private final String[] formerOwners;

    public Car(boolean isSport, int year, String name, FuelType fuelType, String[] formerOwners) {
        this.isSport = isSport;
        this.year = year;
        this.name = name;
        this.fuelType = fuelType;
        this.formerOwners = formerOwners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isSport=" + isSport
                + ", year=" + year
                + ", name='" + name + '\''
                + ", fuelType=" + fuelType
                + ", formerOwners=" + Arrays.toString(formerOwners)
                + '}';
    }

    public static class FuelType {
        private final String type;

        public FuelType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "FuelType{"
                    + "type='" + type + '\''
                    + '}';
        }
    }

    public static void main(String[] args) {
        final Car car = new Car(false, 2003, "Toyota Camry", new FuelType("Petrol"),
                new String[]{"Patrick", "Spongebob", "Squidward"});
        final Gson gson = new GsonBuilder().create();
        String toJson = gson.toJson(car);
        System.out.println(toJson);

        final Car fromJson = gson.fromJson(toJson, Car.class);
        System.out.println(fromJson);
    }
}
