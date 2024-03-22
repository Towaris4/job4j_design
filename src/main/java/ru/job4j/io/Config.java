package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        for (String line : toString().split(System.lineSeparator())) {
            if ((!line.isEmpty()) && (!line.startsWith("#"))) {
                int index = line.indexOf("=");
                if ((index - 1 <= 0) || (index + 1 == line.length())) {
                    throw new IllegalArgumentException("Нарушение шаблона ключ=значение");
                }
                String key = line.substring(0, index);
                String value = line.substring(index + 1);
                values.put(key, value);
            }
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}