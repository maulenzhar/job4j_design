package ru.job4j.collection.queue;

import ru.job4j.collection.list.stack.SimpleStack;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int i;
    int o;

    public T poll() {
        while (o <= i) {
            final T pop = in.pop();
            out.push(pop);
            i--;
            o++;
        }
        o--;
        return out.pop();
    }

    public void push(T value) {
        while (o > 0) {
            final T pop = out.pop();
            in.push(pop);
            i++;
            o--;
        }
        in.push(value);
        i++;
    }
}
