package com.maeldonnart.twentytwo.daytwo;

import java.util.HashMap;
import java.util.Map;

import com.maeldonnart.twentytwo.common.Day;

public class DayTwo extends Day<Integer> {

    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSORS = 3;

    private static final int LOSE = 0;
    private static final int DRAW = 3;
    private static final int VICTORY = 6;

    public DayTwo() {
        super(2);
    }

    private static Map<String, Integer> getScoreByChoicesBeforeInstructions() {
        Map<String, Integer> scoreByChoices = new HashMap<>();

        scoreByChoices.put("A X", DRAW + ROCK);
        scoreByChoices.put("B X", LOSE + ROCK);
        scoreByChoices.put("C X", VICTORY + ROCK);
        scoreByChoices.put("A Y", VICTORY + PAPER);
        scoreByChoices.put("B Y", DRAW + PAPER);
        scoreByChoices.put("C Y", LOSE + PAPER);
        scoreByChoices.put("A Z", LOSE + SCISSORS);
        scoreByChoices.put("B Z", VICTORY + SCISSORS);
        scoreByChoices.put("C Z", DRAW + SCISSORS);

        return scoreByChoices;
    }

    private static Map<String, Integer> getScoreByChoicesAfterInstructions() {
        Map<String, Integer> scoreByChoices = new HashMap<>();

        scoreByChoices.put("A X", LOSE + SCISSORS);
        scoreByChoices.put("B X", LOSE + ROCK);
        scoreByChoices.put("C X", LOSE + PAPER);
        scoreByChoices.put("A Y", DRAW + ROCK);
        scoreByChoices.put("B Y", DRAW + PAPER);
        scoreByChoices.put("C Y", DRAW + SCISSORS);
        scoreByChoices.put("A Z", VICTORY + PAPER);
        scoreByChoices.put("B Z", VICTORY + SCISSORS);
        scoreByChoices.put("C Z", VICTORY + ROCK);

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
