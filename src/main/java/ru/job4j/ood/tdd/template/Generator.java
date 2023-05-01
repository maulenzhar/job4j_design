package ru.job4j.ood.tdd.template;

import ru.job4j.collection.map.Map;

public interface Generator {
    String produce(String template, Map<String, String> args);
}
