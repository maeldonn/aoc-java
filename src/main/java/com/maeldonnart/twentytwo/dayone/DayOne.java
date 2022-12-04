package com.maeldonnart.twentytwo.dayone;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.maeldonnart.twentytwo.common.Day;

public class DayOne extends Day<Integer> {

    public DayOne() {
        super(1);
    }

    private List<Integer> getElfCalories() {
        String inputAsString = String.join(System.lineSeparator(), input);
        return Arrays.asList(inputAsString.split(System.lineSeparator() + System.lineSeparator())).stream()
             .map(this::sumMealsCalories)
             .toList();
    }

    private int sumMealsCalories(String meals) {
        return meals.lines()
             .mapToInt(Integer::valueOf)
             .sum();
    }

    @Override
    protected Integer solvePartOne() {
        return Collections.max(getElfCalories());
    }

    @Override
    protected Integer solvePartTwo() {
        return getElfCalories().stream()
           .sorted(Collections.reverseOrder())
           .limit(3)
           .reduce(0, Integer::sum);
    }
}
