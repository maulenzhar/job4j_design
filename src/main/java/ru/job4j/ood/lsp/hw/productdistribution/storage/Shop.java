package ru.job4j.ood.lsp.hw.productdistribution.storage;

import ru.job4j.ood.lsp.hw.productdistribution.Discount;
import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {

    public List<Food> chooseFoodForShop(List<Food> foods) {
        List<Food> shopFoods = new ArrayList<>();
        for (Food food : foods) {
            if (25 < food.getProductSpoilagePercentage() && food.getProductSpoilagePercentage()  < 75) {
                shopFoods.add(food);
            } else if (food.getProductSpoilagePercentage() > 75) {
                Discount discount = new Discount(food.getPrice(), food.getDiscount());
                food.setPrice(discount.getDiscount());
                shopFoods.add(food);
            }
        }
        return shopFoods;
    }

    public void add(List<Food> foods) {
            add("Shop", chooseFoodForShop(foods));
    }

}
