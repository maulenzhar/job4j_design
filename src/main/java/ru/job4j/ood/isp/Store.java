package ru.job4j.ood.isp;

/* метод getDiscount придется реализовать во всех методах*/
public interface Store {
    void add(Object object);

    Object get(String name);
    boolean delete(String name);
    Object find(String name);
    double getDiscount();
}
