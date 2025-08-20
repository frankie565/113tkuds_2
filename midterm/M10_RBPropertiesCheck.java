import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {
   
    static class Node {
        int val;
        char color ;
        int idx;
        Node(int v, char c, int i) { val = v; color = c; idx = i; }
    }

    static int n;
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(nextNonEmpty(br));
        StringTokenizer st = new StringTokenizer(nextNonEmpty(br));
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());
            char col = st.nextToken().charAt(0);
            if (val == -1) col = 'B';
            nodes[i] = new Node(val, col, i);
        }

        if (n == 0 || nodes[0].val == -1) {
            System.out.println("RB Valid"); 
            return;
        }
        if (nodes[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        String res = check(0).status;
        if (res.equals("OK")) System.out.println("RB Valid");
        else System.out.println(res);
    }

    static class Info {
        String status; 
        int blackHeight;
        Info(String s, int h) { status = s; blackHeight = h; }
    }

    private static Info check(int idx) {
        if (idx >= n || nodes[idx].val == -1) {
            return new Info("OK", 1); 
        }
        Node cur = nodes[idx];
        int left = 2 * idx + 1, right = 2 * idx + 2;

        if (cur.color == 'R') {
            if ((left < n && nodes[left].val != -1 && nodes[left].color == 'R') ||
                (right < n && nodes[right].val != -1 && nodes[right].color == 'R')) {
                return new Info("RedRedViolation at index " + idx, 0);
            }
        }

        Info L = check(left);
        if (!L.status.equals("OK")) return L;
        Info R = check(right);
        if (!R.status.equals("OK")) return R;

        if (L.blackHeight != R.blackHeight) {
            return new Info("BlackHeightMismatch", 0);
        }

        int bh = L.blackHeight + (cur.color == 'B' ? 1 : 0);
        return new Info("OK", bh);
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
