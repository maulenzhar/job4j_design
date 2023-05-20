package ru.job4j.ood.srp.hw.report.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
