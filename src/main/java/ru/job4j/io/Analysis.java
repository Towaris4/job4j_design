package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> answerList = new ArrayList<>();
        int previousStatus = 0;
        String previousDate = null;
        int currentStatus = 0;
        String currentDate = null;
        StringJoiner answer = new StringJoiner(";");
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            for (String line : reader.lines().toList()) {
                String[] split = line.split(" ");
                currentStatus = Integer.parseInt(split[0]);
                currentDate = split[1];
                if (((currentStatus == 400) || (currentStatus == 500))
                    && ((previousStatus == 200) || (previousStatus == 300) || (previousStatus == 0))) {
                    answer.add(currentDate);
                }
                if (((currentStatus == 200) || (currentStatus == 300))
                        && ((previousStatus == 400) || (previousStatus == 500))) {
                    answer.add(currentDate);
                    answerList.add(answer.toString());
                    answer = new StringJoiner(";");
                }
                previousDate = currentDate;
                previousStatus = currentStatus;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String line : answerList) {
                output.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}