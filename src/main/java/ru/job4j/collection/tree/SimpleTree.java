package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> findParent = findBy(parent);
        if (findParent.isPresent() && findBy(child).isEmpty()) {
            findParent.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> pr = node -> node.children.size() > 2;
        return findByPredicate(pr).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> pr = node -> node.value.equals(value);
        return findByPredicate(pr);
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
