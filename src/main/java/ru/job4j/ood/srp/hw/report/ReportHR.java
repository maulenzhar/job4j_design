package ru.job4j.ood.srp.hw.report;

import ru.job4j.ood.srp.hw.report.model.Employee;

import java.util.Comparator;
import java.util.function.Predicate;

public interface ReportHR {
    String generate(Predicate<Employee> filter, Comparator<Employee> comparator);
}
