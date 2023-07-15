package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStore implements Store {
    private final Map<String, Food> storage = new HashMap<>();

    @Override
    public void add(Food food) {
        storage.put(food.getName(), food);
    }
    @Override
    public Food get(String name) {
        return storage.get(name);
    }
}
