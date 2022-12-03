package com.maeldonnart.twentytwo.daytwo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maeldonnart.twentytwo.common.Day;

public class DayTwo extends Day<Integer> {

    public DayTwo() {
        super(2);
    }

    private static Map<String, Integer> getScoreByChoicesBeforeInstructions() {
        Map<String, Integer> scoreByChoices = new HashMap<>();

        scoreByChoices.put("A X", 3 + 1);
        scoreByChoices.put("B X", 0 + 1);
        scoreByChoices.put("C X", 6 + 1);
        scoreByChoices.put("A Y", 6 + 2);
        scoreByChoices.put("B Y", 3 + 2);
        scoreByChoices.put("C Y", 0 + 2);
        scoreByChoices.put("A Z", 0 + 3);
        scoreByChoices.put("B Z", 6 + 3);
        scoreByChoices.put("C Z", 4 + 3);

        return scoreByChoices;
    }

    private static Map<String, Integer> getScoreByChoicesAfterInstructions() {
        Map<String, Integer> scoreByChoices = new HashMap<>();

        scoreByChoices.put("A X", 0 + 3);
        scoreByChoices.put("B X", 0 + 1);
        scoreByChoices.put("C X", 0 + 2);
        scoreByChoices.put("A Y", 3 + 1);
        scoreByChoices.put("B Y", 3 + 2);
        scoreByChoices.put("C Y", 3 + 3);
        scoreByChoices.put("A Z", 6 + 2);
        scoreByChoices.put("B Z", 6 + 3);
        scoreByChoices.put("C Z", 6 + 1);

        return scoreByChoices;
    }

    @Override
    protected Integer solvePartOne() {
        Map<String, Integer> scoreByChoices = getScoreByChoicesBeforeInstructions();
        return input.stream()
             .map(scoreByChoices::get)
             .reduce(0, Integer::sum);
    }

    @Override
    protected Integer solvePartTwo() {
        Map<String, Integer> scoreByChoices = getScoreByChoicesAfterInstructions();
        return input.stream()
             .map(scoreByChoices::get)
             .reduce(0, Integer::sum);
    }
}
