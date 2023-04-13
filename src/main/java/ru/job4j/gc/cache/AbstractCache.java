package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key,  new SoftReference<>(value));
    }

    public final V get(K key) {
        SoftReference<V> res = cache.get(key);
        return res != null ? res.get() : this.load(key);
    }

    protected abstract V load(K key);

}
