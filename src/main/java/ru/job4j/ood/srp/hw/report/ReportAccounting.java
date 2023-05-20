package ru.job4j.ood.srp.hw.report;

import ru.job4j.ood.srp.hw.report.currency.Currency;
import ru.job4j.ood.srp.hw.report.model.Employee;

import java.util.function.Predicate;

public interface ReportAccounting {
    String generate(Predicate<Employee> filter, Currency source, Currency target);
}
