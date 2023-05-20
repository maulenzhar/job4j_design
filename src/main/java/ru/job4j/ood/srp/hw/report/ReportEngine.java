package ru.job4j.ood.srp.hw.report;

import ru.job4j.ood.srp.hw.report.model.Employee;

import java.util.function.Predicate;

public interface ReportEngine {
    String generate(Predicate<Employee> filter);
}
