package ru.job4j.ood.lsp.hw.productdistribution;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.hw.productdistribution.food.Food;
import ru.job4j.ood.lsp.hw.productdistribution.storage.AbstractStore;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Shop;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Trash;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Warehouse;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ControlQualityTest {
    final LocalDateTime now = LocalDateTime.of(2023, Month.JULY, 15, 10, 10, 30);

    @Test
    public void whenShopStore() {
        AbstractStore shop = new Shop();
        List<Store> stores = List.of(shop, new Trash());
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDateTime start = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2023, Month.DECEMBER, 31, 10, 10, 30);
        List<Food> foods = List.of(new Food("Milk", start, end, 100, 5.0));
        controlQuality.distribute(foods, now);
        List<Food> shopFoods = shop.get("Shop");
        assertThat(shopFoods.get(0).getName()).isEqualTo("Milk");
    }

    @Test
    public void whenWarehouseStore() {
        AbstractStore shop = new Warehouse();
        List<Store> stores = List.of(shop, new Trash());
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDateTime start = LocalDateTime.of(2023, Month.JULY, 10, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2023, Month.AUGUST, 31, 10, 10, 30);
        List<Food> foods = List.of(new Food("Milk", start, end, 100, 5.0));
        controlQuality.distribute(foods, now);
        List<Food> shopFoods = shop.get("Warehouse");
        assertThat(shopFoods.get(0).getName()).isEqualTo("Milk");
    }

    @Test
    public void whenTrashStore() {
        AbstractStore shop = new Trash();
        List<Store> stores = List.of(shop, new Warehouse());
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDateTime start = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2023, Month.JUNE, 1, 10, 10, 30);
        List<Food> foods = List.of(new Food("Milk", start, end, 100, 5.0));
        controlQuality.distribute(foods, now);
        List<Food> shopFoods = shop.get("Trash");
        assertThat(shopFoods.get(0).getName()).isEqualTo("Milk");
    }
}
