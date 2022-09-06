package ru.job4j.list.arraylist;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        addVal();
        container[size++] = value;
        modCount++;
    }

    private void addVal() {
        if (container.length == 0) {
            container = (T[]) new Object[size + 1];
        }
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        T old = get(index);
        container[index] = newValue;
        return old;
    }

    @Override
    public T remove(int index) {
        T old = get(index);
        if ((size - 1) > index) {
            System.arraycopy(container, index + 1, container, index, size - 1 - index);
        }
        size--;
        modCount++;
        container[size] = null;
        return old;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return container[point++];
            }
        };
    }


}
