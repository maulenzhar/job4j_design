package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Shop;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Trash;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Warehouse;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ControlQuality {
    public AbstractStore storage;

    public ControlQuality(AbstractStore storage) {
        this.storage = storage;
    }

    public void add(String store, Food food) {
        this.storage.add(store, food);
    }

    public AbstractStore distribute(Food food, LocalDateTime dateNow) {
        double percentage = getPercentage(food, dateNow);
        AbstractStore storage = null;
        if (percentage < 25) {
            storage = new Warehouse("Warehouse");
        } else if (25 < percentage && percentage < 75) {
            Discount discount = new Discount(food.getPrice(), food.getDiscount());
            food.setPrice(discount.getDiscountNumber());
            storage = new Shop("Shop");
        } else if (percentage > 100) {
            storage = new Trash("Trash");
        } else if (percentage > 75) {
            storage = new Shop("Shop");
        }
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.add(storage.getName(), food);

        return storage;
    }

    private double getPercentage(Food food, LocalDateTime dateNow) {
        long total = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long residue = ChronoUnit.DAYS.between(dateNow, food.getExpiryDate());
        double usedUp = total - residue;
        return usedUp / total * 100;
    }
}
