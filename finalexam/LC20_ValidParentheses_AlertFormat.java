import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.hasNext() ? sc.next() : "";

        System.out.println(isValid(s));
    }

    static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) { // 遇到閉括號
                if (stack.isEmpty() || stack.peek() != map.get(c)) {
                    return false; // 不匹配或棧空
                }
                stack.pop();
            } else { // 開括號
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}

/*
 * Time Complexity: O(n)
 *   每個字元進棧/出棧最多一次。
 *
 * Space Complexity: O(n)
 *   最壞情況棧存放所有開括號。
 */
