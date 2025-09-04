import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.hasNextInt() ? sc.nextInt() : 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int len = removeDuplicates(arr);

      
        System.out.println(len);
      
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i]);
            if (i < len - 1) System.out.print(" ");
        }
    }

    static int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;
        int write = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[write - 1]) {
                arr[write] = arr[i];
                write++;
            }
        }
        return write;
    }
}

/*
 * Time Complexity: O(n)
 *   單次遍歷陣列。
 *
 * Space Complexity: O(1)
 *   原地修改，不需額外空間。
 */
