package com.maeldonnart.twentytwo.daythree;

import java.util.stream.IntStream;

import com.maeldonnart.twentytwo.common.Day;

public class DayThree extends Day<Integer> {

    public DayThree() {
        super(3);
    }

    private static Character findDuplicateItem(String rucksack) {
        String firstCompartment = rucksack.substring(0, rucksack.length() / 2);
        String secondCompartment = rucksack.substring(rucksack.length() / 2);

        return firstCompartment.chars()
            .mapToObj(i -> (char) i)
            .filter(c -> secondCompartment.indexOf(c) != -1)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    private static Character findBadge(String firstRucksack, String secondRucksack, String thirdRucksack) {
        return firstRucksack.chars()
            .mapToObj(i -> (char) i)
            .filter(c -> secondRucksack.indexOf(c) != -1 && thirdRucksack.indexOf(c) != -1)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    private static int prioritize(Character letter) {
        if (Character.isUpperCase(letter)) {
            return letter - 'A' + 27;
        }
        return letter - 'a' + 1;
    }

    @Override
    protected Integer solvePartOne() {
        return input.stream()
             .map(DayThree::findDuplicateItem)
             .mapToInt(DayThree::prioritize)
             .sum();
    }

    @Override
    protected Integer solvePartTwo() {
        return IntStream.iterate(0, i -> i + 3)
             .takeWhile(i -> i < input.size())
             .mapToObj(i -> findBadge(input.get(i), input.get(i + 1), input.get(i + 2)))
             .mapToInt(DayThree::prioritize)
             .sum();
    }
}
