package com.maeldonnart.twentytwo.dayseven;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.maeldonnart.twentytwo.common.Day;

public class DaySeven extends Day<Integer> {

    public DaySeven() {
        super(7);
    }

    private static record Directory(int size, Directory parent, Map<String, Directory> childrens) {
        Directory processCommand(String line) {
            if (line.startsWith("$ cd")) {
                String directoryName = line.substring(5);
                return changeDirectory(directoryName); 
            }
            var fileMatcher = Pattern.compile("(\\d+) ([\\w.]+)").matcher(line);
            if (fileMatcher.matches()) {
                return add(fileMatcher.group(2), fileMatcher.group(1));
            }
            return this;
        }

        Directory changeDirectory(String name) {
            return switch (name) {
                case "/" -> parent == null ? this : parent.changeDirectory("/");
                case ".." -> parent;
                default -> childrens.computeIfAbsent(name, k -> new Directory(0, this, new TreeMap<>()));
            };
        }

        Directory add(String name, String size) {
            childrens.put(name, new Directory(Integer.parseInt(size), this, new TreeMap<>()));
            return this;
        }

        Stream<Directory> dirs() {
            Stream<Directory> childrenDirectories = childrens.values().stream().flatMap(Directory::dirs);
            return Stream.concat(Stream.of(this), childrenDirectories).filter(node -> node.size == 0);
        }

        public int size() {
            return size > 0 ? size : childrens.values().stream()
                 .mapToInt(Directory::size)
                 .sum();
        }
    }

    private static Directory createFileSystem(List<String> steps) {
        Directory root = new Directory(0, null, new TreeMap<>());
        steps.stream().reduce(root, (directory, line) -> directory.processCommand(line), (a, b) -> null);
        return root;
    }

    private static int getSpaceNeededToDelete(int rootSize) {
        int totalDiskSpace = 70000000;
        int spaceNeededForUpdate = 30000000;
        return spaceNeededForUpdate - (totalDiskSpace - rootSize);
    }

    @Override
    protected Integer solvePartOne() {
        Directory root = createFileSystem(input);
        return root.dirs()
             .filter(directory -> directory.size() <= 100000)
             .mapToInt(Directory::size)
             .sum();
    }

    @Override
    protected Integer solvePartTwo() {
        Directory root = createFileSystem(input);
        int spaceNeededToDelete = getSpaceNeededToDelete(root.size());
        return root.dirs()
             .filter(n -> n.size() >= spaceNeededToDelete)
             .mapToInt(Directory::size)
             .sorted()
             .findFirst()
             .getAsInt();
    }
}
