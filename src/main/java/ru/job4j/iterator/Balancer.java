package ru.job4j.iterator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        CyclicIterator<ArrayList<Integer>> cyclicIterator = new CyclicIterator<>(nodes);
        ArrayList<Integer> cursor;
        while (source.hasNext()) {
            cursor = cyclicIterator.next();
            cursor.add(source.next());
        }
    }
}