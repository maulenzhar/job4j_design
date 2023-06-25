package ru.job4j.ood.lsp.hw;

/*Инварианты (Invariants) — все условия базового класса также должны быть сохранены и в подклассе. Поле age выступает инвариантом.*/
public class Employee {
    public int age;

    public Employee(int age) throws Exception {
        if (age < 18) {
            throw new Exception("возраст меньше нуля");
        }
        this.age = age;
    }

    public void valid(int age) throws Exception {
        if (age < 18) {
            throw new Exception("возраст меньше нуля");
        }
    }
}

class Job {
    public Employee employee;

    public Employee hire(Employee employee) {
        /**/
        return employee;
    }
}
