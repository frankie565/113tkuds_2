import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] h = new long[n];
        for (int i = 0; i < n; i++) h[i] = sc.nextLong();
        System.out.println(maxArea(h));
    }

    static long maxArea(long[] h) {
        int l = 0, r = h.length - 1;
        long max = 0;
        while (l < r) {
            long area = (long)(r - l) * Math.min(h[l], h[r]);
            max = Math.max(max, area);
            if (h[l] < h[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}

/*
 * Time Complexity: O(n)
 *   雙指針各只移動一次。
 *
 * Space Complexity: O(1)
 *   只需常數額外變數。
 */
