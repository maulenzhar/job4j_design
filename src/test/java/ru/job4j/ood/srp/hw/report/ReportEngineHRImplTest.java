package ru.job4j.ood.srp.hw.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.hw.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.hw.report.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.hw.report.model.Employee;
import ru.job4j.ood.srp.hw.report.model.EmployeeDescBySalary;
import ru.job4j.ood.srp.hw.report.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReportEngineHRImplTest {
    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vasya", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker2);
        store.add(worker1);
        ReportHR engine = new ReportHRImpl(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true, new EmployeeDescBySalary())).isEqualTo(expect.toString());
    }
}