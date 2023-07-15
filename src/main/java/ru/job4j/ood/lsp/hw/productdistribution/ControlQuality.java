package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ControlQuality {
    public HashMap<String, Store> storage;

    public ControlQuality(HashMap<String, Store> storage) {
        this.storage = storage;
    }

    public void distribute(Food food, LocalDateTime dateNow) {
        double percentage = getPercentage(food, dateNow);

        if (percentage < 25) {
            storage.get("Warehouse").add(food);
        } else if (25 < percentage && percentage < 75) {
            double discountPrice = food.getPrice() - (food.getPrice() * (food.getDiscount() / 100));
            food.setPrice(discountPrice);
            storage.get("Shop").add(food);
        } else if (percentage > 100) {
            storage.get("Trash").add(food);
        } else if (percentage > 75) {
            storage.get("Shop").add(food);
        }
    }

    private double getPercentage(Food food, LocalDateTime dateNow) {
        long total = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long residue = ChronoUnit.DAYS.between(dateNow, food.getExpiryDate());
        double usedUp = total - residue;
        return usedUp / total * 100;
    }
}
