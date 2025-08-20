import java.io.*;

public class M02_YouBikeNextArrival {
   
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(nextNonEmpty(br));
        int[] t = new int[n];
        for (int i = 0; i < n; i++) t[i] = toMin(nextNonEmpty(br));
        int q = toMin(nextNonEmpty(br));

        int idx = firstGreater(t, q);
        if (idx == -1) System.out.println("No bike");
        else System.out.println(toHHMM(t[idx]));
    }

    private static String nextNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (!s.isEmpty()) return s;
        }
        return "";
    }

    private static int toMin(String hhmm) {
        int hh = Integer.parseInt(hhmm.substring(0, 2));
        int mm = Integer.parseInt(hhmm.substring(3, 5));
        return hh * 60 + mm;
    }

    private static String toHHMM(int m) {
        int hh = m / 60, mm = m % 60;
        return String.format("%02d:%02d", hh, mm);
    }

    private static int firstGreater(int[] a, int x) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (a[mid] > x) r = mid;
            else l = mid + 1;
        }
        return l == a.length ? -1 : l;
    }
}
