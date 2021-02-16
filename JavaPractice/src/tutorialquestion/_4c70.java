package tutorialquestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 4c70: Lottery Numbers
/*
    In the Lotto game, part of the UK's National Lottery,
    seven distinct numbers are randomly chosen from the set {1, 2, ..., 49}, the seventh being called the Bonus Number.
    Write a program to simulate this.
 */

public class _4c70 {

    private final List<Integer> allNums = new ArrayList<>();
    private final Random gen = new Random();

    public _4c70() {
        for (int i = 1; i <= 49; i++) {
            allNums.add(i);
        }
    }

    public int roll() {
        final int len = allNums.size();
        final int res = allNums.get(gen.nextInt(len));
        allNums.remove(res);
        return res;
    }

    public static void main(String[] args) {
        final _4c70 ins = new _4c70();
        for (int i = 1; i <= 6; i++) {
            System.out.print("Number " + i + ": ");
            System.out.println(ins.roll());
        }
        System.out.print("Bonus Number: ");
        System.out.println(ins.roll());
    }

}
