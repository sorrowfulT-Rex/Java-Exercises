package tutorialquestion;// f79b: Perfect palindromic cubes
/*
    A number x is a perfect palindromic cube if it is a perfect cube (i.e., x=y^3 for some integer y),
    and it is a palindrome when written in decimal (i.e., it appears the same backwards).
    For example, 1030301 is a perfect palindromic cube: it is clearly a palindrome, and 1013 = 1030301.

    Write a program that enumerates the first 1500 non-negative integers, and indicates which integers yield palindromic
    numbers when they are cubed.
 */

public class _f79b {

    public static boolean isPalindrome(int n) {
        final String str = String.valueOf(n);
        final int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1500; i++) {
            final int cubed = (int)Math.pow(i, 3);
            if (isPalindrome(cubed)) {
                System.out.println(i + " cubed is " + cubed);
            }
        }
    }

}
