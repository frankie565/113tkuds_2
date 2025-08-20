import java.io.*;

public class M04_TieredTaxSimple {
    /*
     * Time Complexity: O(n)
     * 說明：逐一處理 n 筆收入，每筆收入計算稅額時僅需常數時間的區段比較與加總，因此每筆 O(1)，整體 O(n)。
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(nextNonEmpty(br));
        long total = 0;

        for (int i = 0; i < n; i++) {
            long income = Long.parseLong(nextNonEmpty(br));
            long tax = calcTax(income);
            System.out.println("Tax: " + tax);
            total += tax;
        }
        System.out.println("Average: " + (total / n));
    }

    private static long calcTax(long income) {
        long tax = 0;
        long[][] brackets = {
            {0, 120000, 5},
            {120001, 500000, 12},
            {500001, 1000000, 20},
            {1000001, Long.MAX_VALUE, 30}
        };

        for (long[] b : brackets) {
            long low = b[0], high = b[1], rate = b[2];
            if (income >= low) {
                long taxable = Math.min(income, high) - low + 1;
                if (taxable > 0) tax += taxable * rate / 100;
            } else break;
        }
        return tax;
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
