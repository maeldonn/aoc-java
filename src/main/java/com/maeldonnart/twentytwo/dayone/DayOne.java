package com.maeldonnart.twentytwo.dayone;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.maeldonnart.twentytwo.common.Day;

public class DayOne extends Day<Integer> {

    public DayOne() {
        super(1);
    }

    private List<Integer> getElfCalories() {
        String inputAsString = input.stream().collect(Collectors.joining(System.getProperty("line.separator")));
        return Arrays.asList(inputAsString.split("\n\n")).stream()
             .map(this::sumMealsCalories)
             .toList();
    }

    private int sumMealsCalories(String line) {
        return Arrays.stream(line.split("\n"))
             .map(Integer::valueOf)
             .reduce(0, Integer::sum);
    }

    @Override
    protected Integer solvePartOne() {
        return getElfCalories().stream()
            .max(Integer::compare)
            .orElse(0);
    }

    @Override
    protected Integer solvePartTwo() {
        return getElfCalories().stream()
           .sorted(Collections.reverseOrder())
           .limit(3)
           .reduce(0, Integer::sum);
    }
}
