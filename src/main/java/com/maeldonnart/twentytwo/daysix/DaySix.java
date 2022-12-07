package com.maeldonnart.twentytwo.daysix;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.maeldonnart.twentytwo.common.Day;

public class DaySix extends Day<Integer> {

    public DaySix() {
        super(6);
    }

    private int findStartOfMessage(int markerLength) {
        String datastream = input.get(0);
        return IntStream.range(0, datastream.length() - 1)
             .filter(i -> datastream.substring(i, i + markerLength).chars().distinct().count() == markerLength)
             .findFirst()
             .getAsInt() + markerLength;
    }

    @Override
    protected Integer solvePartOne() {
        return findStartOfMessage(4);
    }

    @Override
    protected Integer solvePartTwo() {
        return findStartOfMessage(14);
    }
}
