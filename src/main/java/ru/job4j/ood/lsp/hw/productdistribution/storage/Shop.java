package ru.job4j.ood.lsp.hw.productdistribution.storage;

import ru.job4j.ood.lsp.hw.productdistribution.Discount;
import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    public void addAll(List<Food> foods) {
        add(chooseFoodForShop(foods));
    }

    public List<Food> getAll(List<Food> foods) {
        return get(chooseFoodForShop(foods));
    }


    private List<Food> chooseFoodForShop(List<Food> foods) {
        List<Food> shopFoods = new ArrayList<>();
        for (Food food : foods) {
            if (MIN_EXPIRY_PERCENTAGE < food.getProductSpoilagePercentage()
                    && food.getProductSpoilagePercentage()  < MAX_EXPIRY_PERCENTAGE) {
                shopFoods.add(food);
            } else if (food.getProductSpoilagePercentage() > MAX_EXPIRY_PERCENTAGE) {
                Discount discount = new Discount(food.getPrice(), food.getDiscount());
                food.setPrice(discount.getDiscount());
                shopFoods.add(food);
            }
        }

        return shopFoods;
    }
}
