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
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<>(value, null);
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
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.item;
        Node<T> node = head;
        head = head.next;
        node.item = null;
        node.next = null;
        size--;
        modCount++;
        return result;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            final int expectedModCount = modCount;
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = node.item;
                node = node.next;
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
