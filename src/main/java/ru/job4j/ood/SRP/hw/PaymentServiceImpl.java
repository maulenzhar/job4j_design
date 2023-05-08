package ru.job4j.ood.SRP.hw;

import java.util.Arrays;
import java.util.List;

public class PaymentServiceImpl implements PaymentService<Long> {
    List<Long> payments = Arrays.asList(1L, 2L, 3L, 56L, 78L, 55L, 123L, 66L, 100L);

    @Override
    public Long search(long paymentId) {
        return payments.stream()
                .filter(p -> paymentId == p)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void save(long paymentId) {
        payments.add(paymentId);
    }

    @Override
    public void print(List<Long> payments) {
        payments.forEach(System.out::println);
    }

    /* нарушение принципа SRP
     * 1. У класса должна быть только одна цель
     * 2. Класс умеет объекты создавать и инициализировать
     * 3. Способ сохранения может измениться
     * */
}
