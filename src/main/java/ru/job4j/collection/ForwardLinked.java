package ru.job4j.collection;

import com.sun.source.tree.IfTree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> node = head;
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            for (int i = 0; i < size - 1; i++) {
                node = node.next;
            }
            node.next = new Node<>(value, node);
        }
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> result = head;
        for (int i = 1; i <= index; i++) {
            result = result.next;
        }
        return result.item;
    }

    public T deleteFirst() {
        T result = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        result = head.item;
        head = head.next;
        size--;
        modCount++;
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int count = 0;
            final int expectedModCount = modCount;
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = node.item;
                node = node.next;
                count++;
                return item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
