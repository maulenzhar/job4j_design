package ru.job4j.ood.lsp.hw.productdistribution.storage;

import ru.job4j.ood.lsp.hw.productdistribution.AbstractStore;

public class Trash extends AbstractStore {

    public String name;

    public Trash() {
    }

    public Trash(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
