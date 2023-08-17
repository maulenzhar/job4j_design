package ru.job4j.ood.lsp.hw.productdistribution.storage;

import ru.job4j.ood.lsp.hw.productdistribution.Store;
import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractStore implements Store {
    private final Map<String, List<Food>> storage = new HashMap<>();

    public void add(String key, List<Food> value) {
        storage.put(key, value);
    }

    public List<Food> get(String key) {
        return storage.get(key);
    }
}
