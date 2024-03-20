package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {

        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder numStr = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                numStr.append((char) read);
            }
            String[] lines = numStr.toString().split(System.lineSeparator());
            for (String num : lines) {
                int number = Integer.parseInt(num);
                boolean result = number % 2 == 0;
                System.out.println(num + " is even: " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}