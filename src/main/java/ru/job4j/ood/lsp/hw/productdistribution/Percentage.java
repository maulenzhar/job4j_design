package ru.job4j.ood.lsp.hw.productdistribution;

import ru.job4j.ood.lsp.hw.productdistribution.food.Food;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Percentage {
    public List<Food> getPercentage(List<Food> foods, LocalDateTime dateNow) {
        List<Food> res = new ArrayList<>();
        for (Food food : foods) {
            long total = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
            long residue = ChronoUnit.DAYS.between(dateNow, food.getExpiryDate());
            double usedUp = total - residue;
            double percentage = usedUp / total * 100;
            food.setProductSpoilagePercentage(percentage);
            res.add(food);
        }
       return res;
    }
}
