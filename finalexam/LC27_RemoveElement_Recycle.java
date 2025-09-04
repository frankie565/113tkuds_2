import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.hasNextInt() ? sc.nextInt() : 0;
        int val = sc.hasNextInt() ? sc.nextInt() : 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int len = removeElement(arr, val);

  
        System.out.println(len);
      
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i]);
            if (i < len - 1) System.out.print(" ");
        }
    }

    static int removeElement(int[] arr, int val) {
        int write = 0;
        for (int x : arr) {
            if (x != val) {
                arr[write++] = x;
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
 *   原地覆寫，不需額外空間。
 */
