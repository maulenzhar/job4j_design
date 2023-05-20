package ru.job4j.ood.srp.hw.report;

import ru.job4j.ood.srp.hw.report.currency.Currency;
import ru.job4j.ood.srp.hw.report.currency.CurrencyConverter;
import ru.job4j.ood.srp.hw.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.hw.report.model.Employee;
import ru.job4j.ood.srp.hw.report.store.MemStore;
import ru.job4j.ood.srp.hw.report.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccountingImpl implements ReportAccounting {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter currencyConverter;

    public ReportAccountingImpl(Store store, DateTimeParser<Calendar> dateTimeParser, CurrencyConverter currencyConverter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currencyConverter = currencyConverter;
    }

    @Override
    public String generate(Predicate<Employee> filter, Currency source, Currency target) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(currencyConverter.convert(source, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
