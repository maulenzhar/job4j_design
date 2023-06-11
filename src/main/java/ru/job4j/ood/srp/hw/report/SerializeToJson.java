package ru.job4j.ood.srp.hw.report;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.ood.srp.hw.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.hw.report.model.Employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SerializeToJson implements SerializeReport {
    public Gson gson = null;
    DateTimeParser<Calendar> dateTimeParser;
    public SerializeToJson(Gson gson, DateTimeParser<Calendar> dateTimeParser) {
        this.gson = gson;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String serialize(List<Employee> employees) {
        List<String> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (Employee e : employees) {
            jsonObject.put("name", e.getName());
            jsonObject.put("hired",  dateTimeParser.parse(e.getHired()));
            jsonObject.put("fired", dateTimeParser.parse(e.getFired()));
            jsonObject.put("salary", e.getSalary());
            list.add(jsonObject.toString());
        }
        JSONArray jsonStatuses = new JSONArray(list);

        return jsonStatuses.toString();
    }
}
