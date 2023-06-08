package ru.job4j.ood.srp.hw.report;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.hw.report.model.Employee;
import ru.job4j.ood.srp.hw.report.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SerializeToJsonTest {
    @Test
    public void whenSerializeToJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Dima", now, now, 101);
        store.add(worker1);
        store.add(worker2);
        SerializeReport engine = new SerializeToJson(new GsonBuilder().create());
        final String expect =
                "[{\"name\":\"Ivan\","
                        +
                        "\"hired\":{\"year\":2023,"
                        +
                        "\"month\":" + now.getTime().getMonth() + ","
                        +
                        "\"dayOfMonth\":" + now.getTime().getDate() + ","
                        +
                        "\"hourOfDay\":" + now.getTime().getHours() + ","
                        +
                        "\"minute\":" + now.getTime().getMinutes() + ","
                        +
                        "\"second\":" + now.getTime().getSeconds() + "},"
                        +
                        "\"fired\":{"
                        +
                        "\"year\":2023,"
                        +
                        "\"month\":" + now.getTime().getMonth() + ","
                        +
                        "\"dayOfMonth\":" + now.getTime().getDate() + ","
                        +
                        "\"hourOfDay\":" + now.getTime().getHours() + ","
                        +
                        "\"minute\":" + now.getTime().getMinutes() + ","
                        +
                        "\"second\":" + now.getTime().getSeconds() + "},"
                        +
                        "\"salary\":100.0},"
                        +
                        "{\"name\":\"Dima\","
                        +
                        "\"hired\":{"
                        +
                        "\"year\":2023,"
                        +
                        "\"month\":" + now.getTime().getMonth() + ","
                        +
                        "\"dayOfMonth\":" + now.getTime().getDate() + ","
                        +
                        "\"hourOfDay\":" + now.getTime().getHours() + ","
                        +
                        "\"minute\":" + now.getTime().getMinutes() + ","
                        +
                        "\"second\":" + now.getTime().getSeconds() + "},"
                        +
                        "\"fired\":{"
                        +
                        "\"year\":2023,"
                        +
                        "\"month\":" + now.getTime().getMonth() + ","
                        +
                        "\"dayOfMonth\":" + now.getTime().getDate() + ","
                        +
                        "\"hourOfDay\":" + now.getTime().getHours() + ","
                        +
                        "\"minute\":" + now.getTime().getMinutes() + ","
                        +
                        "\"second\":" + now.getTime().getSeconds() + "},"
                        +
                        "\"salary\":101.0}]";
        assertThat(engine.serialize(store.findBy(em -> true))).isEqualTo(expect.toString());
    }
}