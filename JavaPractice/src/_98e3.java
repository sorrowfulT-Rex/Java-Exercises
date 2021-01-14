import java.util.ArrayList;
import java.util.List;

public class _98e3 {

    private final List<Integer> collatzSeq = new ArrayList<>();

    public void genCollatz(int n) {
        if (n == 1) {
            return;
        }

        if (n % 2 == 0) {
            n = n / 2;
        } else {
            n = 3 * n + 1;
        }

        collatzSeq.add(n);
        genCollatz(n);
    }

    public static void main(String[] args) {
        final int input = Integer.parseInt(args[0]);
        final _98e3 ins = new _98e3();

        System.out.print(input + " ");

        ins.genCollatz(input);

        for (int i : ins.collatzSeq) {
            System.out.print(i + " ");
        }
    }

}
