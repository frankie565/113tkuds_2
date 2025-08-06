import java.util.*;

public class AdvancedStringRecursion {

    public static void generatePermutations(String str) {
        generatePermutationsHelper("", str);
    }

    private static void generatePermutationsHelper(String prefix, String remaining) {
        if (remaining.length() == 0) {
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            String newPrefix = prefix + remaining.charAt(i);
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            generatePermutationsHelper(newPrefix, newRemaining);
        }
    }

    
    public static boolean isMatch(String text, String pattern) {
        return matchHelper(text, pattern, 0, 0);
    }

    private static boolean matchHelper(String text, String pattern, int ti, int pi) {
        if (pi == pattern.length()) return true;
        if (ti == text.length()) return false;

        if (text.charAt(ti) == pattern.charAt(pi)) {
            return matchHelper(text, pattern, ti + 1, pi + 1);
        } else {
            return matchHelper(text, pattern, ti + 1, pi);
        }
    }

   
    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, 0, new HashSet<>());
    }

    private static String removeDuplicatesHelper(String str, int index, Set<Character> seen) {
        if (index == str.length()) return "";

        char c = str.charAt(index);
        if (seen.contains(c)) {
            return removeDuplicatesHelper(str, index + 1, seen);
        } else {
            seen.add(c);
            return c + removeDuplicatesHelper(str, index + 1, seen);
        }
    }

 
    public static void generateAllSubstrings(String str) {
        generateAllSubstringsHelper(str, 0, "");
    }

    private static void generateAllSubstringsHelper(String str, int index, String current) {
        if (index == str.length()) {
            if (!current.isEmpty()) {
                System.out.println(current);
            }
            return;
        }

    
        generateAllSubstringsHelper(str, index + 1, current + str.charAt(index));
        
        generateAllSubstringsHelper(str, index + 1, current);
    }

    
    public static void main(String[] args) {
        String str = "abc";
        String text = "xabcyz";
        String pattern = "abc";
        String dupStr = "banana";

        System.out.println("1️⃣ 所有排列組合 of " + str + ":");
        generatePermutations(str);

        System.out.println("\n2️⃣ 字串匹配: 是否 '" + pattern + "' 存在於 '" + text + "' 中?");
        System.out.println(isMatch(text, pattern));  // true

        System.out.println("\n3️⃣ 移除重複字元: " + dupStr);
        System.out.println(removeDuplicates(dupStr));  // "ban"

        System.out.println("\n4️⃣ 所有子字串組合 of " + str + ":");
        generateAllSubstrings(str);
    }
}
