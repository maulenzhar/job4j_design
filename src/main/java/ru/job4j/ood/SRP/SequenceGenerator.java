package ru.job4j.ood.SRP;

import java.util.List;

public interface SequenceGenerator<T> {
    List<T> generate(int size);
}
