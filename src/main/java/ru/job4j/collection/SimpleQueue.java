package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        int count = 0;
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        while (count != size) {
            output.push(input.pop());
            count++;
        }
        count = 0;
        T result = output.pop();
        size--;
        while (count != size) {
            input.push(output.pop());
            count++;
        }
        return result;
    }

    public void push(T value) {
            input.push(value);
            size++;
        }
    }