import java.util.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.hasNext() ? sc.next() : "";
        String needle = sc.hasNext() ? sc.next() : "";

        System.out.println(strStr(haystack, needle));
    }


    static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int n = haystack.length(), m = needle.length();
        if (m > n) return -1;

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) return i; 
        }
        return -1;
    }
}

/*
 * Time Complexity (暴力): O(n*m)
 *   每個起點最多比較 m 次。
 *
 * Time Complexity (KMP): O(n+m)
 *   若改用 KMP 需先建失敗函數，能避免重複比對。
 *
 * Space Complexity: O(1) (暴力) / O(m) (KMP)
 */
