package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
        row = 0;
        column = 0;
    }

    @Override
    public boolean hasNext() {
        int searchRow = row;
        int searchColumn = column;
        while ((searchRow < data.length)) {
            if (searchColumn >= data[searchRow].length) {
                searchRow++;
                searchColumn = 0;
            } else if (data[searchRow][searchColumn] == 0){
                searchColumn++;
                if (searchColumn == data[searchRow].length) {
                    searchRow++;
                    searchColumn = 0;
                }
            } else {
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
        while ((row < data.length)) {
            if (column >= data[row].length) {
                row++;
                column = 0;
            } else if (data[row][column] == 0){
                column++;
                if (column == data[row].length) {
                    row++;
                    column = 0;
                }
            } else {
                return data[row][column++];
            }
        }
        return 0;
    }
}