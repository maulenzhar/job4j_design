package ru.job4j.ood.lsp.hw.productdistribution.storage;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {
    public void addAll(List<Food> foods) {
        add(chooseFoodForShop(foods));
    }

    public List<Food> getAll(List<Food> foods) {
        return get(chooseFoodForShop(foods));
    }

    private List<Food> chooseFoodForShop(List<Food> foods) {
        List<Food> shopFoods = new ArrayList<>();
        for (Food food : foods) {
            if (food.getProductSpoilagePercentage() < MIN_EXPIRY_PERCENTAGE) {
                shopFoods.add(food);
            }
        }
        return shopFoods;
    }
}
