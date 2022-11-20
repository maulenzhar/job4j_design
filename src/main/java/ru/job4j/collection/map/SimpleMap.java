package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (key == null && table[0] == null) {
            table[0] = new MapEntry(key, value);
            count++;
            modCount++;
            result = true;
        }
        if (key != null && table[indexFor(hash(key.hashCode()))] == null) {
            table[indexFor(hash(key.hashCode()))] = new MapEntry(key, value);
            count++;
            modCount++;
            result = true;
        }
        if (count == LOAD_FACTOR * capacity) {
            expand();
        }

        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> el : table) {
            if (el != null) {
                int i = el.key == null ? 0 : indexFor(el.key.hashCode());
                newTable[i] = el;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V res = null;
        int i = (key == null) ? 0 : indexFor(hash(key.hashCode()));
        if (key != null && table[i] != null && table[i].key != null
                && hash(table[i].key.hashCode()) == hash(key.hashCode())
                && table[i].key.equals(key)) {
            res = table[i].value;
        } else if (key == null && table[i].key == null) {
            res = table[i].value;
        }

        return res;
    }

    @Override
    public boolean remove(K key) {
        boolean res = false;
        if (key == null) {
            table[0] = null;
            res = true;
            modCount++;
            count++;
        }

        if (!res && table[indexFor(key.hashCode())] != null
                && hash(table[indexFor(key.hashCode())].key.hashCode()) == hash(key.hashCode())
                && table[indexFor(key.hashCode())].key.equals(key)) {
            table[indexFor(key.hashCode())] = null;
            res = true;
            modCount++;
            count++;
        }

        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}