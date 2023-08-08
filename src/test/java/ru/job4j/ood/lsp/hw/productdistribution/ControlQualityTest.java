package ru.job4j.ood.lsp.hw.productdistribution;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.hw.productdistribution.food.Food;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Shop;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Trash;
import ru.job4j.ood.lsp.hw.productdistribution.storage.Warehouse;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;


class ControlQualityTest {
    final LocalDateTime now = LocalDateTime.of(2023, Month.JULY, 15, 10, 10, 30);

    @Test
    public void whenShopStore() {
        ControlQuality controlQuality = new ControlQuality(new Shop("Shop"));
        LocalDateTime start = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2023, Month.DECEMBER, 31, 10, 10, 30);
        Food milk = new Food("Milk", start, end, 100, 5.0);
        AbstractStore distribute = controlQuality.distribute(milk, now);

        assertThat(distribute.getName()).isEqualTo("Shop");
    }

    @Test
    public void whenWarehouseStore() {
        ControlQuality controlQuality = new ControlQuality(new Warehouse("Warehouse"));
        LocalDateTime start = LocalDateTime.of(2023, Month.JULY, 10, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2023, Month.AUGUST, 31, 10, 10, 30);
        Food milk = new Food("Milk", start, end, 100, 5.0);
        AbstractStore distribute = controlQuality.distribute(milk, now);

        assertThat(distribute.getName()).isEqualTo("Warehouse");
    }

    @Test
    public void whenTrashStore() {
        ControlQuality controlQuality = new ControlQuality(new Trash());
        LocalDateTime start = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 10, 30);
        LocalDateTime end = LocalDateTime.of(2023, Month.JUNE, 1, 10, 10, 30);
        Food milk = new Food("Milk", start, end, 100, 5.0);
        AbstractStore distribute = controlQuality.distribute(milk, now);

        assertThat(distribute.getName()).isEqualTo("Trash");
    }
}
