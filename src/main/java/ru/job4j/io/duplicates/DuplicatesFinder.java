package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        Map<FileProperty, List<String>> map = duplicatesVisitor.getDuplicates();
        for (FileProperty fileProperty : duplicatesVisitor.getDuplicates().keySet()) {
            StringJoiner output = new StringJoiner(" - ");
            output.add(fileProperty.getName());
            output.add(String.valueOf(fileProperty.getSize()));
            System.out.println(output.toString());
            for (String str : map.get(fileProperty)) {
                System.out.println(str);
            }
        }
    }
}