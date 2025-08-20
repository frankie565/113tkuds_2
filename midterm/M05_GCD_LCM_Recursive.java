import java.io.*;
import java.util.*;

public class M05_GCD_LCM_Recursive {
    /*
     * Time Complexity: O(log min(a, b))
     * 說明：遞迴歐幾里得演算法每次以餘數縮小，數值規模呈指數下降，故時間複雜度為 O(log min(a, b))。
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(nextNonEmpty(br));
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long g = gcd(a, b);
        long l = (a / g) * b;

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    private static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }

    private static String nextNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (!s.isEmpty()) return s;
        }
        return "";
    }
}
