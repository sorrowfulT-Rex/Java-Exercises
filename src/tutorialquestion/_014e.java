package tutorialquestion;// 014e: Random numbers
/*
    Write a program that takes a positive integer command-line argument x and continuously generates random integers
    between 0 and x-1 (inclusive) until each integer in this range has been generated at least once.
    The program should display all the numbers that are generated,
    and finally report how many numbers were generated in total.
*/

import java.util.*;

public class _014e {

    private final int max;
    private final List<Integer> randomList = new ArrayList<>();
    private final Set<Integer> appeared = new HashSet<>();
    private final Set<Integer> allNums = new HashSet<>();
    private final Random randGen = new Random();

    public List<Integer> getRandomList() {
        return randomList;
    }

    public _014e(int max) {
        this.max = max;

        for (int i = 0; i < max; i++) {
            allNums.add(i);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int n : randomList) {
            sb.append(", ").append(n);
        }

        return sb.toString().substring(2);
    }

    public void appendRandom() {
        final int randInt = randGen.nextInt(max);
        randomList.add(randInt);
        appeared.add(randInt);
    }

    public boolean allIn() {
        return appeared.equals(allNums);
    }

    public static void main(String[] args) {
        final int input = Integer.parseInt(args[0]);
        final _014e ins = new _014e(input);

        System.out.println("Generating random numbers:");

        while (!ins.allIn()) {
            ins.appendRandom();
        }

        System.out.println(ins);
        System.out.println("I had to generate " +
                ins.getRandomList().size() +
                " random numbers between 0 and " +
                (input - 1) +
                " to have produced all such numbers at least once.");
    }

}
