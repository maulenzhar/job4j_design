package ru.job4j.ood.srp.hw.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.hw.report.currency.Currency;
import ru.job4j.ood.srp.hw.report.currency.CurrencyConverter;
import ru.job4j.ood.srp.hw.report.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.hw.report.formatter.DateTimeParser;
import ru.job4j.ood.srp.hw.report.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.hw.report.model.Employee;
import ru.job4j.ood.srp.hw.report.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReportEngineAccountingImplTest {
    @Test
    public void whenAccountingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        CurrencyConverter currency = new InMemoryCurrencyConverter();
        store.add(worker);
        ReportAccountingImpl engine = new ReportAccountingImpl(store, parser, currency);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(currency.convert(Currency.RUB, worker.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true, Currency.RUB, Currency.USD)).isEqualTo(expect.toString());
    }
}