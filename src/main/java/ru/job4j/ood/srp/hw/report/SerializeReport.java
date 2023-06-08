package ru.job4j.ood.srp.hw.report;

import ru.job4j.ood.srp.hw.report.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface SerializeReport {
    String  serialize(List<Employee> employees);
}
