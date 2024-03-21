package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            list = input.lines()
                    .filter(line -> line.split(" ")[line.split(" ").length - 2].equals("404"))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}