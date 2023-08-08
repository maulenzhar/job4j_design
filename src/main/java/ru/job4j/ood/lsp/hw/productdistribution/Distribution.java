package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Shop;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Trash;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Warehouse;

public class Distribution {
    private Food food;
    private double productSpoilagePercentage;

    public Distribution(Food food, double productSpoilagePercentage) {
        this.food = food;
        this.productSpoilagePercentage = productSpoilagePercentage;
    }

    public AbstractStore getStore() {
        AbstractStore storage = null;
        if (Distribution.this.productSpoilagePercentage < 25) {
            storage = new Warehouse("Warehouse");
        } else if (25 < Distribution.this.productSpoilagePercentage && Distribution.this.productSpoilagePercentage < 75) {
            Discount discount = new Discount(food.getPrice(), food.getDiscount());
            food.setPrice(discount.getDiscount());
            storage = new Shop("Shop");
        } else if (Distribution.this.productSpoilagePercentage > 100) {
            storage = new Trash("Trash");
        } else if (Distribution.this.productSpoilagePercentage > 75) {
            storage = new Shop("Shop");
        }
        return storage;
    }
}
