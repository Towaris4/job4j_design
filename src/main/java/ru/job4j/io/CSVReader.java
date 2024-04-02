package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        /*try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(argsName.get("path")));
        BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("path"), StandardCharsets.UTF_8))) {
            var lines = new Scanner(new ByteArrayInputStream(input.readAllBytes()))
                    .useDelimiter(System.lineSeparator());
            String firstLine = lines.nextLine();
            Scanner lineScanner = new Scanner(new ByteArrayInputStream(firstLine.getBytes()))
                    .useDelimiter(";");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        File file = new File(argsName.get("path"));
        try (var scanner = new Scanner(file).useDelimiter(System.lineSeparator())) {
            List<Integer> columns = new ArrayList<>();
            String nameColumns = scanner.next();
            String[] array = nameColumns.split(argsName.get("delimiter"));
            for (int i = 0; i < array.length; i++) {
                if (argsName.get("filter").contains(array[i])) {
                    columns.add(i);
                }
            }
            while (scanner.hasNext()) {
                String[] line = scanner.next().split(argsName.get("delimiter"));
                for (int i = 0; i < columns.size() - 1; i++) {
                    System.out.print(line[columns.get(i)]);
                    System.out.print(argsName.get("delimiter"));
                }
                System.out.print(line[columns.get(columns.size() - 1)]);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        File file = new File("path");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!argsName.get("path").endsWith(".csv")) {
                throw new IllegalArgumentException("The file name does not have a \".csv\" extension");
        }
        if (!argsName.get("out").equals("stdout") || (new File(argsName.get("out"))).isFile()) {
            throw new IllegalArgumentException("The delimiter must be \";\"");
        }
        handle(argsName);
    }
}