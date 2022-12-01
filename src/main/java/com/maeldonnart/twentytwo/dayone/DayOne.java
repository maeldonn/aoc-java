package com.maeldonnart.twentytwo.dayone;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.maeldonnart.twentytwo.common.Day;

public class DayOne extends Day<Integer> {

    public DayOne() {
        super(1);
    }

    private List<Integer> getElfCalories() {
        List<Integer> elfCalories = IntStream.of(new int[input.size()])
             .boxed()
             .collect(Collectors.toList());

        int currentElf = 1;
        for (String mealCalorie : input) {
            if (mealCalorie.isBlank()) {
                currentElf++;
            } else {
                elfCalories.set(currentElf - 1, elfCalories.get(currentElf - 1) + Integer.parseInt(mealCalorie));
            }
        }

        return elfCalories;
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
