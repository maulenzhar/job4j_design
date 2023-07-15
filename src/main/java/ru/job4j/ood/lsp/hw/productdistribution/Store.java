package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

public interface Store {
    void add(Food food);
    Food get(String name);
}
