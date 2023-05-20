package ru.job4j.ood.srp.hw.report.store;


import ru.job4j.ood.srp.hw.report.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    void add(Employee em);

    List<Employee> findBy(Predicate<Employee> filter);
}
