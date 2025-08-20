import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {
    
    static class Entry {
        int time, listIdx, pos;
        Entry(int t, int li, int p) { time = t; listIdx = li; pos = p; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(nextNonEmpty(br));
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(nextNonEmpty(br));
            StringTokenizer st = new StringTokenizer(nextNonEmpty(br));
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) arr[j] = Integer.parseInt(st.nextToken());
            lists.add(arr);
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        for (int i = 0; i < K; i++) {
            if (lists.get(i).length > 0) {
                pq.offer(new Entry(lists.get(i)[0], i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();
        while (!pq.isEmpty()) {
            Entry cur = pq.poll();
            merged.add(cur.time);
            int nextPos = cur.pos + 1;
            if (nextPos < lists.get(cur.listIdx).length) {
                pq.offer(new Entry(lists.get(cur.listIdx)[nextPos], cur.listIdx, nextPos));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < merged.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(merged.get(i));
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
}
