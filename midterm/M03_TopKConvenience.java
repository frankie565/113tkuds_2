import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    /*
     * Time Complexity: O(n log K)
     * 說明：逐筆以大小為 K 的最小堆維護前 K 名，每次插入/替換成本 O(log K)，共 n 筆為 O(n log K)；最後對 K 筆做排序 O(K log K)，整體 O(n log K)。
     */
    static class Item {
        String name;
        int qty;
        Item(String name, int qty) { this.name = name; this.qty = qty; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer hk = new StringTokenizer(nextNonEmpty(br));
        int n = Integer.parseInt(hk.nextToken());
        int K = Integer.parseInt(hk.nextToken());

        PriorityQueue<Item> heap = new PriorityQueue<>(
            (a, b) -> {
                if (a.qty != b.qty) return Integer.compare(a.qty, b.qty);   
                return b.name.compareTo(a.name);                            
            }
        );

        for (int i = 0; i < n; i++) {
            String line = nextNonEmpty(br);
            StringTokenizer st = new StringTokenizer(line);
            String name = st.nextToken();
            int qty = Integer.parseInt(st.nextToken());
            Item cur = new Item(name, qty);

            if (heap.size() < K) {
                heap.offer(cur);
            } else {
                Item worst = heap.peek();
                if (qty > worst.qty || (qty == worst.qty && name.compareTo(worst.name) < 0)) {
                    heap.poll();
                    heap.offer(cur);
                }
            }
        }

        List<Item> ans = new ArrayList<>(heap);
        ans.sort((a, b) -> {
            if (a.qty != b.qty) return Integer.compare(b.qty, a.qty); 
            return a.name.compareTo(b.name);                           
        });

        StringBuilder sb = new StringBuilder();
        for (Item it : ans) {
            sb.append(it.name).append(' ').append(it.qty).append('\n');
        }
        System.out.print(sb.toString());
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
