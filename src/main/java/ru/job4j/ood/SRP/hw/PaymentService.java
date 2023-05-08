package ru.job4j.ood.SRP.hw;

import java.util.List;

public interface PaymentService<T> {
    T search(long paymentId);

    void save(long paymentId);

    void print(List<Long> payments);
}
