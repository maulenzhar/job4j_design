package ru.job4j.ood.srp.hw.report.formatter;


public interface DateTimeParser<T> {
    String parse(T t);
}