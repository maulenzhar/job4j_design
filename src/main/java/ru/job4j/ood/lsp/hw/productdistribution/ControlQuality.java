package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;
import java.time.LocalDateTime;
import java.util.List;

public class ControlQuality {
    public List<Store> storage;

    public ControlQuality(List<Store> storage) {
        this.storage = storage;
    }

    public void distribute(List<Food> food, LocalDateTime dateNow) {
        Percentage percentage = new Percentage();
        List<Food> resPercentage = percentage.getPercentage(food, dateNow);
        for (Store store : storage) {
            store.addAll(resPercentage);
        }
    }
}
