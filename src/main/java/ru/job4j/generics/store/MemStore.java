package ru.job4j.generics.store;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        final T replace = storage.replace(id, model);
        return replace != null && !model.equals(replace);
    }

    @Override
    public boolean delete(String id) {
        final T value = storage.get(id);
        return value != null && storage.remove(storage.get(id).getId(), value);
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
