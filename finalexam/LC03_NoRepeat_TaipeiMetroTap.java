import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.hasNext() ? sc.next() : "";  

    
        Map<Character, Integer> map = new HashMap<>();

        int left = 0;   
        int ans = 0;    

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

         
            if (map.containsKey(c) && map.get(c) >= left) {
                left = map.get(c) + 1;
            }

          
            map.put(c, right);

            ans = Math.max(ans, right - left + 1);
        }

        System.out.println(ans);
    }
}

/* 
 * Time Complexity: O(n)
 *   每個字元最多被左右指針訪問一次。
 *
 * Space Complexity: O(k)
 *   k = 字元種類數（ASCII 可見字集 → O(128)）。
 */
