package ru.job4j.collection.queue;

import ru.job4j.collection.list.stack.SimpleStack;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int i;
    int o;

    public T poll() {
        for (int j = o; j <= i; j++) {
            final T pop = in.pop();
            out.push(pop);
            i--;
            o++;
        }

        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        i++;
    }
}
