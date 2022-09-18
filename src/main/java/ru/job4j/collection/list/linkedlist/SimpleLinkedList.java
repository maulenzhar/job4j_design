package ru.job4j.collection.list.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    transient int size = 0;
    transient Node<E> node;
    private int modCount;

    @Override
    public void add(E e) {
        Node<E> f = node;
        if (f == null) {
            node = new Node<>(e, f);
        } else {
            while (f.next != null) {
                f = f.next;
             }
            f.next = new Node<>(e, null);
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return node(index).value;
    }

    private Node<E> node(int index) {
        Node<E> last = node;
        for (int i = 0; i < index; i++) {
            last = last.next;
        }
        return last;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        int expectedModCount = modCount;
        Node<E> point = node;

        public boolean hasNext() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            return point != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E item = point.value;
            point = point.next;
            return item;
        }
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E element, Node<E> node) {
            this.value = element;
            this.next = node;
        }
    }
}
