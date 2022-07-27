package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data.length > row) {
            if (data[row].length == 0) {
                row++;
                if (!hasNext()) {
                    break;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int res = 0;
        if (data[row].length > column) {
            res = data[row][column++];
        }
        if (data[row].length <= column) {
            column = 0;
            row++;
        }
        return res;
    }
}
