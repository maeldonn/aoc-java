package com.maeldonnart.twentytwo.dayeight;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.maeldonnart.twentytwo.common.Day;

public class DayEight extends Day<Long> {

    public DayEight() {
        super(8);
    }

    private static record Tree(int row, int column, int height) {

        boolean isVisible(List<String> trees) {
            return isVisibleInLine(trees.get(row), column) || isVisibleInLine(getColumn(trees, column), row);
        }

        long getScenicScore(List<String> trees) {
            return getScenicScoreInLine(trees.get(row), column) * getScenicScoreInLine(getColumn(trees, column), row);
        }
    }

    private static String getColumn(List<String> trees, int column) {
        return IntStream.range(0, trees.size())
        .map(i -> trees.get(i).charAt(column))
        .mapToObj(Character::toString)
        .collect(Collectors.joining());
    }


    private static boolean isVisibleInLine(String trees, int treePosition) {
        boolean visibleFromLeft = isBigger(trees.substring(0, treePosition), trees.charAt(treePosition));
        boolean visibleFromRight = isBigger(trees.substring(treePosition + 1), trees.charAt(treePosition));
        return visibleFromLeft || visibleFromRight;
    }

    private static long getScenicScoreInLine(String trees, int treePosition) {
        // TODO: Calculate scenic score in line
        return 0;
    }

    private static boolean isBigger(String trees, int treeSize) {
        return trees.chars().allMatch(tree -> tree < treeSize);
    }

    private Stream<Tree> parseTree(int row) {
        return IntStream.range(0, input.get(row).length())
             .mapToObj(column -> new Tree(row, column, input.get(row).charAt(column)));
    }

    @Override
    protected Long solvePartOne() {
        return IntStream.range(0, input.size())
             .mapToObj(this::parseTree)
             .flatMap(tree -> tree)
             .filter(tree -> tree.isVisible(input))
             .count();
    }

    @Override
    protected Long solvePartTwo() {
        return IntStream.range(0, input.size())
             .mapToObj(this::parseTree)
             .flatMap(tree -> tree)
             .map(tree -> tree.getScenicScore(input))
             .sorted(Collections.reverseOrder())
             .findFirst()
             .get();
    }
}
