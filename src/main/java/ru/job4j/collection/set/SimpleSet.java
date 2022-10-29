package ru.job4j.collection.set;


import ru.job4j.collection.list.arraylist.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean res = true;
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            if(Objects.equals(value, iterator.next())) {
               res = false;
           }
        }
        if (res) {
            set.add(value);
        }
        return res;
    }

    @Override
    public boolean contains(T value) {
        boolean res = false;
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            if(Objects.equals(value, iterator.next())) {
                res = true;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
