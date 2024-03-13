package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];


    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            result = true;
            table[index] = new MapEntry<K, V>(key, value);
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
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