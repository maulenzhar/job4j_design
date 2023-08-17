package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;
import ru.job4j.ood.lsp.hw.productdistribution.storage.AbstractStore;

import java.util.List;

public interface Store {
    void add(List<Food> foods);
}
