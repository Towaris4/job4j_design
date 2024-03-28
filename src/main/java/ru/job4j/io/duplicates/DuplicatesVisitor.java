package ru.job4j.io.duplicates;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, List<String>> map = new HashMap<>();

    public HashMap<FileProperty, List<String>> getDuplicates() {
        HashMap<FileProperty, List<String>> cloneMap = new HashMap<>(map);
        for (FileProperty fileProperty : cloneMap.keySet()) {
            if (cloneMap.get(fileProperty).size() == 1) {
                map.remove(fileProperty);
            }
        }
        return map;
    }
    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        map.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file.toAbsolutePath().toString());
        return super.visitFile(file, attributes);
    }
}