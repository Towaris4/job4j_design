package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Analysis {
    private boolean serverStatus = true;

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            reader.lines().forEach(line -> {
                String[] array = line.split(" ");
                StringJoiner out = new StringJoiner("");
                if ((array[0].equals("500") || array[0].equals("400")) && serverStatus) {
                    output.append(array[1]).append(";");
                    serverStatus = false;
                }
                if ((array[0].equals("200") || array[0].equals("300")) && !serverStatus) {
                    output.append(array[1]).append(";").append(System.lineSeparator());
                    serverStatus = true;
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");

    }
}