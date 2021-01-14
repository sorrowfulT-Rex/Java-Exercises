import java.util.Random;

public class Dice {

    static final int NUM_SIDES = 6;

    private int top;
    final Random generator;

    public Dice() {
        generator = new Random();
        roll();
    }

    public int top() {
        return top;
    }

    public int bottom() {
        return NUM_SIDES - top + 1;
    }

    @Override
    public String toString() {
        return "Top: " + top() + "\nBottom: " + bottom();
    }

    public void roll() {
        top = generator.nextInt(NUM_SIDES) + 1;
    }

    public static void main(String[] args) {
        Dice d = new Dice();
        System.out.println(d);
        d.roll();
        System.out.println(d);
    }
}
