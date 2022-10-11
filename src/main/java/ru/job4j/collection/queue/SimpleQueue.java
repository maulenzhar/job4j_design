package ru.job4j.collection.queue;

import ru.job4j.collection.list.stack.SimpleStack;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int sizeInput;
    int sizeOutput;

    public T poll() {
        if (sizeOutput == 0) {
            while (sizeOutput <= sizeInput) {
                out.push(in.pop());
                sizeInput--;
                sizeOutput++;
            }
        }
        sizeOutput--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        sizeInput++;
    }
}
