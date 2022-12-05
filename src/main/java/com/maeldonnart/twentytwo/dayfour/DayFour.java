package com.maeldonnart.twentytwo.dayfour;

import com.maeldonnart.twentytwo.common.Day;

public class DayFour extends Day<Long> {

    public DayFour() {
        super(4);
    }

    private static class Pair {
        private final Integer x;
        private final Integer y;

        public Pair(String[] pair) {
            x = Integer.valueOf(pair[0]);
            y = Integer.valueOf(pair[1]);
        }

        public static boolean isContaining(String[] pairs) {
            Pair a = new Pair(pairs[0].split("-"));
            Pair b = new Pair(pairs[1].split("-"));
            return (a.x >= b.x && a.y <= b.y) || (b.x >= a.x && b.y <= a.y);
        }

        public static boolean isOverlaping(String[] pairs) {
            Pair firstPair = new Pair(pairs[0].split("-"));
            Pair secondPair = new Pair(pairs[1].split("-"));
            return !(firstPair.x > secondPair.y || secondPair.x > firstPair.y);
        }
    }

    @Override
    protected Long solvePartOne() {
        return input.stream()
             .map(pairs -> pairs.split(","))
             .filter(Pair::isContaining)
             .count();
    }

    @Override
    protected Long solvePartTwo() {
        return input.stream()
             .map(pairs -> pairs.split(","))
             .filter(Pair::isOverlaping)
             .count();
    }
}
