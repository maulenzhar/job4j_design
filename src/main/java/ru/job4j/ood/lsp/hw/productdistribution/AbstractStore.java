package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStore implements Store {
    private final Map<String, Food> storage = new HashMap<>();

    @Override
    public void add(String store, Food food) {
        storage.put(store, food);
    }


}
