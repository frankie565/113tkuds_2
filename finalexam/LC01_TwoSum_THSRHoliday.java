import java.util.*;


public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long target = sc.nextLong();

        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }

      
        Map<Long, Integer> map = new HashMap<>();

        int ans1 = -1, ans2 = -1;

        for (int i = 0; i < n; i++) {
            long x = seats[i];

        
            if (map.containsKey(x)) {
                ans1 = map.get(x);
                ans2 = i;
                break;
            }

           
            long need = target - x;
            map.put(need, i);
        }

        System.out.println(ans1 + " " + ans2);
    }
}

/* 
 * Time Complexity: O(n)
 *   每個元素僅被 HashMap 查詢/插入一次。
 *
 * Space Complexity: O(n)
 *   最多存放 n 個「需要的數」。
 */
