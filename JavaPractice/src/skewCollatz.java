import java.util.*;

public class skewCollatz {
    private Set<Integer> set = new HashSet<>();

    private int next(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return 3 * n - 1;
        }
    }

    @Override
    public String toString() {
        return set.toString();
    }

    public void genSeq(int n) {
        while (!set.contains(n)) {
            set.add(n);
            n = next(n);
        }
    }

    public void clearSet() {
        set = new HashSet<>();
    }

    public static void main(String[] args) {
        Map<Integer, Integer> dict = new HashMap();
        skewCollatz ins = new skewCollatz();

        for (int i = 1; i <= 100000; i++) {
            ins.genSeq(i);
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
