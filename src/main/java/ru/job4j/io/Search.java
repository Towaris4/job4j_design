package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        for (Path path : search(start, path -> path.toFile().getName().endsWith(args[1]))) {
            System.out.println(path.toFile().getName());
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
     private static void validateArgs(String[] args) {
         if (args.length == 0) {
             throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
         }
         if (args.length !=  2) {
             throw new IllegalArgumentException("invalid number of parameters");
         }
         File file = new File(args[0]);
         if (!file.isDirectory()) {
             throw new IllegalArgumentException(String.format("Not directory %s", args[0]));
         }
         if (!args[1].startsWith(".")) {
             throw new IllegalArgumentException(String.format("Not expansion %s", args[1]));
         }
     }
}