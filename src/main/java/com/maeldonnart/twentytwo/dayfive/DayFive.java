package com.maeldonnart.twentytwo.dayfive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.maeldonnart.twentytwo.common.Day;

public class DayFive extends Day<String> {

    public DayFive() {
        super(5);
    }

    private static class Instruction {
        private final int move;
        private final int from;
        private final int to;

        public Instruction(String instruction) {
            String[] array = instruction.split(" ");
            move = Integer.valueOf(array[1]);
            from = Integer.valueOf(array[3])- 1;
            to = Integer.valueOf(array[5]) - 1;
        }

        public void execute9000(List<LinkedList<Character>> stacks) {
            for (int i = 1; i <= move; i++) {
                stacks.get(to).addLast(stacks.get(from).removeLast());
            }
        }

        public void execute9001(List<LinkedList<Character>> stacks) {
            for (int i = 1; i <= move; i++) {
                // TODO: Part 2
            }
        }
    }

    private List<LinkedList<Character>> getStacks() {
        String inputAsString = String.join(System.lineSeparator(), input);
        String[] parts = inputAsString.split(System.lineSeparator() + System.lineSeparator())[0].split(System.lineSeparator());
        List<LinkedList<Character>> stacks = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
           stacks.add(new LinkedList<>());
        }

        for (int i = parts.length - 2; i >= 0; i--) {
            for (int j = 0; j < 9; j++) {
                Character letter = parts[i].charAt(4 * j + 1);
                if (!Character.isWhitespace(letter)) {
                    stacks.get(j).add(letter);
                }
            }
        }

        return stacks; 
    }

    private List<Instruction> getInstructions() {
        String inputAsString = String.join(System.lineSeparator(), input);
        return inputAsString.split(System.lineSeparator() + System.lineSeparator())[1].lines()
             .map(Instruction::new)
             .toList();
    }

    private static String getTopCrates(List<LinkedList<Character>> stacks) {
        return stacks.stream()
             .map(LinkedList::getLast)
             .map(String::valueOf)
             .collect(Collectors.joining());
    }


    @Override
    protected String solvePartOne() {
        var stacks = getStacks();
        var instructions = getInstructions();

        instructions.forEach(instruction -> instruction.execute9000(stacks));
        return getTopCrates(stacks);
    }

    @Override
    protected String solvePartTwo() {
        var stacks = getStacks();
        var instructions = getInstructions();

        instructions.forEach(instruction -> instruction.execute9001(stacks));
        return getTopCrates(stacks);
    }
}
