package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        File file = new File(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        try (var scanner = new Scanner(file)) {
            String firstLine = scanner.nextLine();
            List<String> nameColumns = Arrays.asList(firstLine.split(delimiter));
            String[] filter = argsName.get("filter").split(",");
            StringBuilder stringBuilder = new StringBuilder();
            int[] index = new int[filter.length];
            for (int i = 0; i < filter.length; i++) {
                index[i] = nameColumns.indexOf(filter[i]);
            }
            stringBuilder.append(String.join(delimiter, filter)).append(System.lineSeparator());
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(delimiter);
                for (int i = 0; i < filter.length - 1; i++) {
                    stringBuilder.append(line[index[i]]);
                    stringBuilder.append(delimiter);
                }
                stringBuilder.append(line[index[filter.length - 1]]).append(System.lineSeparator());
            }
            if ("stdout".equals(argsName.get("out"))) {
                System.out.print(stringBuilder.toString());
            } else {
                try (PrintWriter writer = new PrintWriter(new FileWriter(argsName.get("out"), StandardCharsets.UTF_8, true))) {
                    writer.print(stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        if (!"stdout".equals(argsName.get("out")) || (new File(argsName.get("out"))).isFile()) {
            throw new IllegalArgumentException("stdout or path");
        }
        handle(argsName);
    }
}