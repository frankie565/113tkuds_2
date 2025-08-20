import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {
    /*
     * Time Complexity: O(n log n)
     * 說明：建堆 O(n)，每次取出最大元素並調整堆 O(log n)，共執行 n 次，總和 O(n log n)。
     */
    static class Item {
        int score, idx;
        Item(int s, int i) { score = s; idx = i; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(nextNonEmpty(br));
        StringTokenizer st = new StringTokenizer(nextNonEmpty(br));
        Item[] arr = new Item[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Item(Integer.parseInt(st.nextToken()), i);
        }

        heapSort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i].score);
        }
        System.out.println(sb.toString());
    }

    private static void heapSort(Item[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(a, n, i);
        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);
            heapify(a, i, 0);
        }
    }

    private static void heapify(Item[] a, int size, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < size && better(a[l], a[largest])) largest = l;
        if (r < size && better(a[r], a[largest])) largest = r;
        if (largest != i) {
            swap(a, i, largest);
            heapify(a, size, largest);
        }
    }

    private static boolean better(Item x, Item y) {
        if (x.score != y.score) return x.score > y.score;
        return x.idx < y.idx; // tie: earlier index is "greater" to keep order
    }

    private static void swap(Item[] a, int i, int j) {
        Item tmp = a[i]; a[i] = a[j]; a[j] = tmp;
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
