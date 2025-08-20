import java.io.*;
import java.util.*;

public class M01_BuildHeap {
    /*
     * Time Complexity: O(n)
     * 說明：自底向上從最後一個非葉節點開始對每個節點做 heapifyDown。高度為 h 的節點約有 n/2^{h+1} 個，
     *      每個節點下沉最多 h 層，總成本為 ∑_{h=0}^{⌊log n⌋} (n/2^{h+1})·h = O(n)，故整體線性時間。
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String type = nextNonEmpty(br);
        int n = Integer.parseInt(nextNonEmpty(br));
        int[] a = new int[n];
        int filled = 0;
        String line;
        while (filled < n && (line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens() && filled < n) {
                a[filled++] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isMax = "max".equalsIgnoreCase(type);
        buildHeap(a, isMax);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    private static String nextNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (!s.isEmpty()) return s;
        }
        return "";
    }

    private static void buildHeap(int[] a, boolean isMax) {
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            heapifyDown(a, i, a.length, isMax);
        }
    }

    private static void heapifyDown(int[] a, int i, int size, boolean isMax) {
        while (true) {
            int left = i * 2 + 1;
            if (left >= size) break;
            int right = left + 1;

            int bestChild = left;
            if (right < size && better(a[right], a[left], isMax)) {
                bestChild = right;
            }

            if (better(a[bestChild], a[i], isMax)) {
                int tmp = a[i];
                a[i] = a[bestChild];
                a[bestChild] = tmp;
                i = bestChild;
            } else {
                break;
            }
        }
    }

    private static boolean better(int x, int y, boolean isMax) {
        return isMax ? x > y : x < y;
    }
}
