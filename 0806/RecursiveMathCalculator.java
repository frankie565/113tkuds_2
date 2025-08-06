public class RecursiveMathCalculator {

    
    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }


    public static int catalan(int n) {
        if (n == 0) return 1;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += catalan(i) * catalan(n - 1 - i);
        }
        return sum;
    }

 
    public static int hanoi(int n) {
        if (n == 1) return 1;
        return 2 * hanoi(n - 1) + 1;
    }

    
    public static boolean isPalindrome(int n) {
        return isPalindromeHelper(String.valueOf(n), 0, String.valueOf(n).length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }

 
    public static void main(String[] args) {
        int n = 5, k = 2;
        System.out.println("C(" + n + ", " + k + ") = " + combination(n, k));  // 10

        int cat = 4;
        System.out.println("Catalan(" + cat + ") = " + catalan(cat));  // 14

        int h = 3;
        System.out.println("Hanoi(" + h + ") steps = " + hanoi(h));  // 7

        int num1 = 12321;
        int num2 = 12345;
        System.out.println(num1 + " is palindrome? " + isPalindrome(num1));  // true
        System.out.println(num2 + " is palindrome? " + isPalindrome(num2));  // false
    }
}
