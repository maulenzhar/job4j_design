package ru.job4j.ood.srp.hw.report;

import com.google.gson.Gson;
import ru.job4j.ood.srp.hw.report.model.Employee;

import java.util.List;

public class SerializeToJson implements SerializeReport {
    public Gson gson = null;

    public SerializeToJson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String serialize(List<Employee> employees) {
        return gson.toJson(employees);
    }
}
