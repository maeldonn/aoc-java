package com.maeldonnart.twentytwo.common;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public abstract class Day<T> {

    private final int day;
    
    protected final List<String> input;

    protected final String inputAsString;

    protected Day(int day) {
        this.day = day;
        this.input = getInput();
        this.inputAsString = String.join(System.lineSeparator(), input);
    }

    private List<String> getInput() {
        List<String> input = new ArrayList<>();
        try (Stream<String> lines = Files.lines(getInputPath())) {
            input = lines.toList();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    private Path getInputPath() throws URISyntaxException, IOException {
        String filename = "input" + day + ".txt";
        URI uri = getClass().getClassLoader().getResource(filename).toURI();
        createFileSystemIfNeeded(uri);
        return Paths.get(uri);
    }

    private void createFileSystemIfNeeded(URI uri) throws IOException {
        try {
            FileSystems.getFileSystem(uri);
        } catch (FileSystemNotFoundException exception) {
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            FileSystems.newFileSystem(uri, env);
        }
    }

    protected abstract T solvePartOne();

    protected abstract T solvePartTwo();

    public void resolve() {
        System.out.print("Day " + day + " : ");
        System.out.print("part one = " + solvePartOne() + " ");
        System.out.print("part two = " + solvePartTwo() + "\n");
    }
}
