package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MaxMinTest {
    @Test
    public void whenGetMax() {
        List<Integer> lists = Arrays.asList(1, 2, 3, 56, 78, 55, 123, 66, 100);
        Comparator<Integer> comparator = (left, right) -> {
            return Integer.compare(right, left);
        };
        MaxMin maxMin = new MaxMin();
        Integer max = maxMin.max(lists, comparator);
        assertThat(max, is(123));
    }

    @Test
    public void whenGetMin() {
        List<Integer> lists = Arrays.asList(1, 2, 3, 56, 78, 55, 123, 66, 100);
        Comparator<Integer> comparator = (left, right) -> {
            return Integer.compare(right, left);
        };
        MaxMin maxMin = new MaxMin();
        Integer max = maxMin.min(lists, comparator);
        assertThat(max, is(1));
    }
}