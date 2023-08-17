package ru.job4j.ood.lsp.hw.productdistribution.storage;

import ru.job4j.ood.lsp.hw.productdistribution.Discount;
import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {
    public List<Food> chooseFoodForShop(List<Food> foods) {
        List<Food> shopFoods = new ArrayList<>();
        for (Food food : foods) {
            if (food.getProductSpoilagePercentage() > 100) {
                shopFoods.add(food);
            }
        }
        return shopFoods;
    }

    public void add(List<Food> foods) {
        add("Trash", chooseFoodForShop(foods));
    }
}
