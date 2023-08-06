package ru.job4j.ood.isp;

import java.util.List;

// не верно выделена абстракция
public interface Payment {
    Object status();
    List<Object> getPayments();
    void getLoanPayments();
}
