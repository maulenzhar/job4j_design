package ru.job4j.assertj;


import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> array = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(array)
                .hasSize(5)
                .startsWith("first", "second")
                .endsWith("five")
                .doesNotContain("ten", "nine");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> array = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(array)
                .isNotEmpty()
                .contains("first")
                .doesNotContainNull()
                .containsSequence("first", "second");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> array = simpleConvert.toMap("1", "2", "3", "4", "5");
        assertThat(array)
                .hasSize(5)
                .containsKeys("1", "2")
                .containsValues(2, 1)
                .containsEntry("2", 1);
    }
}