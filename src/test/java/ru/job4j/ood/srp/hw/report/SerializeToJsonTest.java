package ru.job4j.ood.srp.hw.report;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.hw.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.hw.report.formatter.ReportDateTimeParser;
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
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        SerializeReport engine = new SerializeToJson(new GsonBuilder().create(), dateTimeParser);
        final String expect = "[\"{\\\"fired\\\":\\\"" + dateTimeParser.parse(now) + "\\\",\\\"name\\\":\\\"Ivan\\\",\\\"hired\\\":\\\"" + dateTimeParser.parse(now) + "\\\",\\\"salary\\\":100}\",\"{\\\"fired\\\":\\\"" + dateTimeParser.parse(now) + "\\\",\\\"name\\\":\\\"Dima\\\",\\\"hired\\\":\\\"" + dateTimeParser.parse(now) + "\\\",\\\"salary\\\":101}\"]";
        assertThat(engine.serialize(store.findBy(em -> true))).isEqualTo(expect.toString());
    }
}