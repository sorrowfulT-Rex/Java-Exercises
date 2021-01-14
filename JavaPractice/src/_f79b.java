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
