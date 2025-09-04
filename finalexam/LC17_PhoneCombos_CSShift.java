import java.util.*;

public class LC17_PhoneCombos_CSShift {
    static final String[] map = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.hasNext() ? sc.next() : "";
        List<String> res = letterCombinations(digits);
        for (String s : res) {
            System.out.println(s);
        }
    }

    static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;
        backtrack(ans, new StringBuilder(), digits, 0);
        return ans;
    }

    static void backtrack(List<String> ans, StringBuilder sb, String digits, int idx) {
        if (idx == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        String letters = map[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(ans, sb, digits, idx + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

/*
 * Time Complexity: O(∏ k)
 *   k = 每個 digit 的對應字母數量；總輸出組合數。
 *
 * Space Complexity: O(depth)
 *   遞迴深度 ≤ |digits|
 */
