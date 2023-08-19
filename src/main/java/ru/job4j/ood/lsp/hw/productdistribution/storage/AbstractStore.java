package ru.job4j.ood.lsp.hw.productdistribution.storage;

import ru.job4j.ood.lsp.hw.productdistribution.Store;
import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractStore implements Store {
    protected List<Food> storage = new ArrayList<>();
    protected final static int MIN_EXPIRY_PERCENTAGE = 25;
    protected final static int MAX_EXPIRY_PERCENTAGE = 75;
    protected final static int EXPIRED = 100;

    public void add(List<Food> foods) {
        for (Food food : foods) {
            storage.add(food);
        }
    }

    public List<Food> get(List<Food> foods) {
        List<Food> foodsFromStorage = new ArrayList<>();
        for (Food food : foods) {
            foodsFromStorage.add(food);
        }
        return foodsFromStorage;
    }
}
