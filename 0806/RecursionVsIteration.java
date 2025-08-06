
public class RecursionVsIteration {

    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }


    public static int binomialIterative(int n, int k) {
        int[][] C = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i)
                    C[i][j] = 1;
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        return C[n][k];
    }

    public static int productRecursive(int[] arr, int i) {
        if (i == arr.length) return 1;
        return arr[i] * productRecursive(arr, i + 1);
    }

    public static int productIterative(int[] arr) {
        int product = 1;
        for (int val : arr) {
            product *= val;
        }
        return product;
    }

    public static int countVowelsRecursive(String str, int i) {
        if (i == str.length()) return 0;
        char c = Character.toLowerCase(str.charAt(i));
        int add = "aeiou".indexOf(c) != -1 ? 1 : 0;
        return add + countVowelsRecursive(str, i + 1);
    }

    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }

    public static boolean checkBracketsRecursive(String str, int index, int balance) {
        if (balance < 0) return false;
        if (index == str.length()) return balance == 0;

        char c = str.charAt(index);
        if (c == '(') return checkBracketsRecursive(str, index + 1, balance + 1);
        else if (c == ')') return checkBracketsRecursive(str, index + 1, balance - 1);
        else return checkBracketsRecursive(str, index + 1, balance);
    }

    public static boolean checkBracketsIterative(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    public static void main(String[] args) {
        int n = 5, k = 2;
        System.out.println("1️⃣ 二項式係數 C(" + n + ", " + k + ")");
        System.out.println("遞迴：" + binomialRecursive(n, k));
        System.out.println("迭代：" + binomialIterative(n, k));

        int[] arr = {1, 2, 3, 4};
        System.out.println("\n2️⃣ 陣列乘積");
        System.out.println("遞迴：" + productRecursive(arr, 0));
        System.out.println("迭代：" + productIterative(arr));

        String str = "Recursion is powerful!";
        System.out.println("\n3️⃣ 元音字母數量 in \"" + str + "\"");
        System.out.println("遞迴：" + countVowelsRecursive(str, 0));
        System.out.println("迭代：" + countVowelsIterative(str));

        String brackets = "((())())";
        System.out.println("\n4️⃣ 括號配對 \"" + brackets + "\"");
        System.out.println("遞迴：" + checkBracketsRecursive(brackets, 0, 0));
        System.out.println("迭代：" + checkBracketsIterative(brackets));
    }
}
