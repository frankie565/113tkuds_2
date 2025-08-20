import java.io.*;

public class M06_PalindromeClean {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        String cleaned = sb.toString();
        int l = 0, r = cleaned.length() - 1;
        boolean ok = true;
        while (l < r) {
            if (cleaned.charAt(l) != cleaned.charAt(r)) {
                ok = false;
                break;
            }
            l++;
            r--;
        }

        System.out.println(ok ? "Yes" : "No");
    }
}
