import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

// 2d33: Reversed order of input
/*
    Write a program that reads a series of lines from standard input, then prints out these lines in reversed order.

    You should first implement a version of this program that reads lines of input into an array of strings,
    making the assumption that input will consist of 100 lines or fewer.
    You should then implement a more flexible version of this program where the number of input lines is not bounded,
    by storing input in a Deque object.
 */

public class _2d33 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] list = new String[100];
        String line;

        int count = 0;
        do {
            line = br.readLine();
            list[count] = line;
            count++;
        } while (!line.equals(""));

        for (int i = count - 2; i >= 0; i--) {
            System.out.println(list[i]);
        }
    }

}

class _2d33_2 {

    public static void main(String[] args) throws IOException {
        Deque<String> myDeque = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        do {
            line = br.readLine();
            myDeque.push(line);
        } while (!line.equals(""));

        if (!myDeque.isEmpty()) {
            myDeque.pop();
        }

        while (!myDeque.isEmpty()) {
            System.out.println(myDeque.pop());
        }
    }

}
