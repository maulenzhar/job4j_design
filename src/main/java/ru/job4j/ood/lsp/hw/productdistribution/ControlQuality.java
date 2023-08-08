package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Shop;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Trash;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Warehouse;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {
    public Store storage;

    public ControlQuality(Store storage) {
        this.storage = storage;
    }

    public void add(String store, Food food) {
        this.storage.add(store, food);
    }

    public AbstractStore distribute(Food food, LocalDateTime dateNow) {
        double percentage = getPercentage(food, dateNow);
        Distribution distribution = new Distribution(food, percentage);
        AbstractStore storage = distribution.getStore();
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
