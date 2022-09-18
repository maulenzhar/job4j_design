package ru.job4j.collection.queue;

import ru.job4j.collection.list.stack.SimpleStack;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        while (true) {
            try {
                final T pop = in.pop();
                out.push(pop);

            } catch (NoSuchElementException e) {
                break;
            }
        }

        return out.pop();
    }

    public void push(T value) {
        while (true) {
            try {
                final T pop = out.pop();
                in.push(pop);
            } catch (NoSuchElementException e) {
                break;
            }
        }
        in.push(value);
    }
}
