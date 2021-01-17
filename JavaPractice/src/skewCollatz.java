import java.math.BigInteger;
import java.util.*;

public class skewCollatz {
    private Set<BigInteger> set = new HashSet<>();

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

    public void genSeq(BigInteger n) {
        while (!set.contains(n)) {
            set.add(n);
            n = next(n);
        }
    }

    public void clearSet() {
        set = new HashSet<>();
    }

    public static void main(String[] args) {
        Map<BigInteger, Integer> dict = new HashMap();
        skewCollatz ins = new skewCollatz();

        for (int i = 1; i <= 150000; i++) {
            ins.genSeq(BigInteger.valueOf(i));
            var minned = Collections.min(ins.set);
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
