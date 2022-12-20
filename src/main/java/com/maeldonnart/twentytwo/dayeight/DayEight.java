package com.maeldonnart.twentytwo.dayeight;

import java.util.Collections;
import java.util.List;
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
            return isVisibleInLine(trees.get(row), column) || isVisibleInLine(getColumn(trees), row);
        }

        long getScenicScore(List<String> trees) {
            return getScenicScoreInLine(trees.get(row), column) * getScenicScoreInLine(getColumn(trees), row);
        }

        String getColumn(List<String> trees) {
            return IntStream.range(0, trees.size())
                 .map(i -> trees.get(i).charAt(column))
                 .mapToObj(Character::toString)
                 .collect(Collectors.joining());
        }

        boolean isVisibleInLine(String trees, int treePosition) {
            boolean visibleFromLeft = isBigger(trees.substring(0, treePosition));
            boolean visibleFromRight = isBigger(trees.substring(treePosition + 1));
            return visibleFromLeft || visibleFromRight;
        }

        boolean isBigger(String trees) {
            return trees.chars().allMatch(tree -> tree < height);
        }

        long getScenicScoreInLine(String trees, int treePosition) {
            long leftScenicScore = getViewingDistance(new StringBuilder(trees.substring(0, treePosition)).reverse().toString());
            long rightScenicScore = getViewingDistance(trees.substring(treePosition + 1));
            return leftScenicScore * rightScenicScore;
        }

        long getViewingDistance(String trees) {
            for (int i = 1; i < trees.length(); i++) {
                if (trees.charAt(i - 1) >= height) {
                    return i;
                }
            }
            return trees.isBlank() ? 0 : trees.length();
        }
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
