package com.maeldonnart.twentytwo.daysix;

import java.util.Set;
import java.util.stream.Collectors;

import com.maeldonnart.twentytwo.common.Day;

public class DaySix extends Day<Integer> {

    public DaySix() {
        super(6);
    }

    public Integer findStartOfMessage(int markerLength) {
        String datastream = input.get(0);
        for (int i = 0; i < datastream.length(); i++) {
            Set<Character> sequence = datastream.substring(i, i + markerLength).chars()
                 .mapToObj(e -> (char) e)
                 .collect(Collectors.toSet());
            if (sequence.size() == markerLength) {
                return i + markerLength; 
            }
        }

        return null;
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
