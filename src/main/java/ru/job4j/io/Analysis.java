package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Analysis {
    boolean serverStatus = true;


    public void unavailable2(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            reader.lines().forEach(line -> {
                String[] array = line.split(" ");
                if ((array[0].equals("500") || array[0].equals("400")) && serverStatus) {
                    output.print(array[1] + ";");
                    serverStatus = false;
                }
                if ((array[0].equals("200") || array[0].equals("300")) && !serverStatus) {
                    output.print(array[1] + ";\n");
                    serverStatus = true;
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable2("data/server.log", "data/target.csv");
    }
}