package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answerList = readPhrases();
        boolean run = true;
        boolean bot = true;
        do {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            log.add(command);
            switch (command) {
                case OUT:
                    run = false;
                    break;
                case STOP:
                    bot = false;
                    break;
                case CONTINUE:
                    bot = true;
                    break;
                default:
                    break;
            }
            if (run && bot) {
                Random random = new Random();
                String answer = answerList.get(random.nextInt(answerList.size()));
                log.add(answer);
                System.out.println(answer);
            }
        } while (run);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/consoleChat.txt", "data/botAnswers.txt");
        consoleChat.run();
    }
}