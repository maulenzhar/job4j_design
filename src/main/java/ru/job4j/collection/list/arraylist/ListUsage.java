package ru.job4j.collection.list.arraylist;

public class ListUsage {
    public static void main(String[] args) {
        /*String[] array = {"one", "two", "three", "four", "five"};
        System.out.println(Arrays.toString(array));
        // индекс по которому удаляем
        int index = 2;
        System.arraycopy(
                array, // откуда копируем
                index + 1, // начиная с какого индекса
                array, // куда копируем
                index, // начиная с какого индекса
                array.length - index - 1 // сколько элементов копируем
        );
        // на последнее место ставим null, чтобы не было утечки памяти (если удаляем последний элемент)
        array[array.length - 1] = null;
        System.out.println(Arrays.toString(array));*/
        /*List<Long> li = new ArrayList<>();
        li.add(66l);
        li.add(70l);
        li.add(21l);
        System.out.println("The final list for long value is given as: ");
        System.out.println(li.iterator().next());
        System.out.println(li.iterator().next());
        System.out.println(li.iterator().next());

        LinkedList<String> s = new LinkedList<>();
        s.add("1");
        s.add("2");
        s.iterator();

        SimpleList<Integer> list = new SimpleArrayList<>(0);
        list.add(1);
        list.add(2);
        System.out.println(list);*/
        SList<String> a = new SList<String>("a");
        SLIterator<String> it = a.sliterator();
        it.add("b");
        it.add("c");
        System.out.println(a);
        it.remove();
        System.out.println(a);  //1
        while (it.hasNext()) {    //2
            System.out.println(it.next());
        }
    }
}

class SList<T> {
    class Node {
        T value;
        Node next;

        Node(T arg) {
            value = arg;
        }
    }

    Node tail;

    SList(T val) {
        tail = new Node(val);
    }

    public String toString() {
        String res = "";
        while (tail.next != null) {
            res += tail.value.toString() + " ";
            tail = tail.next;
        }
        res += tail.value.toString() + " ";
        return res;
    }

    SLIterator<T> sliterator() {
        return new SLIterator<T>(this);
    }
}

class SLIterator<T> {
    SList<T> iter;

    SLIterator(SList<T> arg) {
        iter = arg;
    }

    boolean hasNext() {
        return iter.tail.next == null;
    }

    T next() {
        T res = iter.tail.value;
        iter.tail = iter.tail.next;
        return res;
    }

    void add(T arg) {
        SList<T>.Node tmp = iter.tail;
        iter.tail = iter.new Node(arg);
        iter.tail.next = tmp;
    }

    void remove() {
        iter.tail = iter.tail.next;
    }
}