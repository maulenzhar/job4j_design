package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

public interface Store<T extends AbstractStore> {
    void add(String store, Food food);
    String getName();
}
