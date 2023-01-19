package ru.job4j.serialization.json.jsonobject;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.json.Contact;
import ru.job4j.serialization.json.Person;
import ru.job4j.serialization.json.hw.Car;

import java.util.ArrayList;
import java.util.List;

public class MainCarToJsonObject {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject fuelType = new JSONObject("{\"type\":\"Electro\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("David");
        list.add("Maks");
        JSONArray formerOwners = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Car car = new Car(false, 2003, "Toyota", new Car.FuelType("Petrol"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSport", car.isSport());
        jsonObject.put("year", car.getYear());
        jsonObject.put("name", car.getName());
        jsonObject.put("fuelType", fuelType);
        jsonObject.put("formerOwners", formerOwners);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
