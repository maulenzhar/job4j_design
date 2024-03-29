package ru.job4j.ood.srp.hw.report;

import ru.job4j.ood.srp.hw.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.hw.report.model.Employee;
import ru.job4j.ood.srp.hw.report.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHRImpl implements ReportHR {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportHRImpl(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter, Comparator<Employee> comparator) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());

        List<Employee> emp = store.findBy(filter);
        emp.sort(comparator);

        for (Employee employee : emp) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
