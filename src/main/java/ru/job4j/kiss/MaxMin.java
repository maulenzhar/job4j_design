package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> comp = (i, j) -> comparator.compare(i, j) > 0;
        return getMinMax(value, comp);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> comp = (i, j) -> comparator.compare(i, j) < 0;
        return getMinMax(value, comp);
    }

    private <T> T getMinMax(List<T> value, BiPredicate<T, T> comp) {
        int res = 0;
        for (int i = 0; i < value.size(); i++) {
            for (int j = 0; j < value.size(); j++) {
                if (comp.test(value.get(j), value.get(i))) {
                    res++;
                }
            }
            if (res == value.size() - 1) {
                res = i;
                break;
            }
            res = 0;
        }
        return value.get(res);
    }
}