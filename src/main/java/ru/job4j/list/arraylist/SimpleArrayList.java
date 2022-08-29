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
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size] = value;
        modCount++;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T old = container[index];
        container[index] = newValue;
        return old;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T old = container[index];
        modCount++;
        final int newSize;
        newSize = size - 1;
        if ((newSize) > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
        }
        size = newSize;
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
                return point < size;
            }

            @Override
            public T next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return container[point++];
            }
        };
    }


}
