package ru.job4j.ood.lsp.hw.productdistribution;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.hw.productdistribution.food.Food;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Shop;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Warehouse;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;


class ControlQualityTest {
    final HashMap<String, Store> stores = new HashMap<>();
    final LocalDateTime now = LocalDateTime.of(2023, Month.JULY, 15, 10, 10, 30);

    @Test
    public void whenShopStore() {
        stores.put("Shop", new Shop("Shop"));
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDateTime start = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2023, Month.DECEMBER, 31, 10, 10, 30);
        Food milk = new Food("Milk", start, end, 100, 5.0);
        controlQuality.distribute(milk, now);

        assertThat(stores.get("Shop").get("Milk").getName()).isEqualTo("Milk");
    }

    @Test
    public void whenWarehouseStore() {
        stores.put("Warehouse", new Warehouse("Warehouse"));
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDateTime start = LocalDateTime.of(2023, Month.JULY, 10, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2023, Month.AUGUST, 31, 10, 10, 30);
        Food milk = new Food("Milk", start, end, 100, 5.0);
        controlQuality.distribute(milk, now);

        assertThat(stores.get("Warehouse").get("Milk").getName()).isEqualTo("Milk");
    }

    @Test
    public void whenTrashStore() {
        stores.put("Trash", new Warehouse("Trash"));
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDateTime start = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2023, Month.JUNE, 1, 10, 10, 30);
        Food milk = new Food("Milk", start, end, 100, 5.0);
        controlQuality.distribute(milk, now);

        assertThat(stores.get("Trash").get("Milk").getName()).isEqualTo("Milk");
    }
}