package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class InvertedIndexDriver {

    public static void main(String[] args) throws IOException {
        // Check if the folder is provided as an argument
        if (args.length != 1) {
            System.out.println("Usage: java InvertedIndexMain <input_folder>");
            return;
        }

        String inputFolder = args[0];
        File folder = new File(inputFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("The provided path is not a valid directory.");
            return;
        }

        // Create an inverted index using a HashMap
        Map<String, Map<String, Integer>> invertedIndex = new HashMap<>();

        // Process each file in the folder
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    processFile(file, invertedIndex);
                }
            }
        }

        // Output the inverted index
        printInvertedIndex(invertedIndex);
    }

    private static void processFile(File file, Map<String, Map<String, Integer>> invertedIndex) throws IOException {
        // Read file content
        List<String> lines = Files.readAllLines(file.toPath());

        // Process each line in the file
        for (String line : lines) {
            String[] words = line.split("\\W+"); // Split by non-word characters

            for (String word : words) {
                if (word.isEmpty()) continue;

                // Convert to lowercase for case-insensitivity
                word = word.toLowerCase();

                // Add word to the inverted index (track frequency per file)
                invertedIndex.putIfAbsent(word, new HashMap<>());
                Map<String, Integer> fileMap = invertedIndex.get(word);
                fileMap.put(file.getName(), fileMap.getOrDefault(file.getName(), 0) + 1);
            }
        }
    }

    private static void printInvertedIndex(Map<String, Map<String, Integer>> invertedIndex) {
        // Print the inverted index in the format: word => "file:count"
        for (Map.Entry<String, Map<String, Integer>> entry : invertedIndex.entrySet()) {
            String word = entry.getKey();
            Map<String, Integer> fileMap = entry.getValue();

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Integer> fileEntry : fileMap.entrySet()) {
                sb.append(fileEntry.getKey()).append(":").append(fileEntry.getValue()).append(" ");
            }

            System.out.println(word + " => " + sb.toString().trim());
        }
    }
}
