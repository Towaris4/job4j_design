package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = -1;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int searchIndex = index;
        while (searchIndex < data.length - 1) {
            searchIndex++;
            if (data[searchIndex] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (index < data.length) {
            index++;
            if (data[index] % 2 == 0) {
                break;
            }
        }
        return data[index];
    }
}