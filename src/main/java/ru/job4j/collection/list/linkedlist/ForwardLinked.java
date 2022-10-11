package ru.job4j.collection.list.linkedlist;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw  new NoSuchElementException();
        }
        Node<T> f = head;
        T element = f.value;
        final Node<T> next = f.next;
        f.value = null;
        f.next = null;
        head = next;

        return element;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public boolean revert() {
        boolean res;
        if (head == null || head.next == null) {
            res = false;
        } else {
            Node<T> prev = null;
            Node<T> current = null;
            while (iterator().hasNext()) {
                current = new Node(head.value, prev);
                head = head.next;
                prev = current;
            }
            head = current;
            res = true;
        }

        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
