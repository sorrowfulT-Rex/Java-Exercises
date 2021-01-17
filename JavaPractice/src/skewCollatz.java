import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

// Skew Collatz Problem
/*
  For any positive integer n, define next(n) to be n/2 if n is even, or 3n-1 if n is odd.
  Let's call this sequence the Skew Collatz Sequence from n.
  It is conjectured that the Collatz Sequence (replace 3n-1 with 3n+1) always reaches 1,
  but it is not the case for the skewed one. For example, the minimum of the skewed sequence from 7
  is 5 (7 20 10 5 14 7 20 10 5 ...).
  I conjecture that the minimum of Skew Collatz Sequence is always 1, 5 or 17.
 */

public class skewCollatz {
    // The set that stores the Skew Collatz Sequence. We don't care about the order, only the numbers and the minimum.
    private Set<BigInteger> set = new HashSet<>();
    // The map that stores entries, each containing a minimum and a set of number that will reach that min value.
    // If a sequence reaches a value in the map, we can jump to the minimum directly without repetitive calculation.
    final private Map<Integer, Set<BigInteger>> minMap = new HashMap<>();

    private BigInteger next(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return n.divide(BigInteger.TWO);
        } else {
            return n.multiply(BigInteger.valueOf(3)).add(BigInteger.valueOf(-1));
        }
    }

    @Override
    public String toString() {
        return set.toString();
    }

    public Integer genSeq(BigInteger n) {
        while (true) {
            // If the sequence is unbounded, then the program will terminate not!
            set.add(n);
            n = next(n);

            if (set.contains(n)) {
                var minned = Collections.min(set).intValue();
                minMap.put(minned, set);
                return minned;
            }

            for (var key : minMap.keySet()) {
                if (minMap.get(key).contains(n)) {
                    minMap.get(key).addAll(set);
                    return key;
                }
            }
        }
    }

    public void clearSet() {
        set = new HashSet<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        try {
            input = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("Please enter an integer!");
            System.out.println("Full error: " + e);
            return;
        }
        Map<Integer, Integer> dict = new HashMap<>();
        skewCollatz ins = new skewCollatz();

        for (int i = 1; i <= input; i++) {
            final var minned = ins.genSeq(BigInteger.valueOf(i));
            ins.clearSet();

            if (!dict.containsKey(minned)) {
                dict.put(minned, 1);
            } else {
                dict.put(minned, dict.get(minned) + 1);
            }
        }

        System.out.println(dict);
    }
}
