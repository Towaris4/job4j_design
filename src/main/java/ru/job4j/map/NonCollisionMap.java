package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];


    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        int index = getIndex(key);
        if (table[index] == null) {
            result = true;
            table[index] = new MapEntry<K, V>(key, value);
        }
        count++;
        modCount++;
        return result;
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private boolean equalsKey(K key) {
        int hashcode = Objects.hashCode(key);
        int hash = hash(hashcode);
        int index = indexFor(hash(hash));
        return  (table[index] != null
                && Objects.hashCode(table[index].key) == hashcode
                && Objects.equals(table[index].key, key));
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> map : table) {
            if (map != null) {
                newTable[getIndex(map.key)] = map;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = getIndex(key);
        if (equalsKey(key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        if (equalsKey(key)) {
            table[getIndex(key)] = null;
            result = true;
        }
        count--;
        modCount--;
        return result;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {

            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while ((index < table.length) && (table[index] == null)) {
                    index++;
                }
                return index != table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}