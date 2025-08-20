import java.io.*;
import java.util.*;

public class M09_AVLValidate {
    /*
     * Time Complexity: O(n)
     * 說明：每個節點僅訪問一次；檢查 BST 性質與計算 AVL 高度皆可於一次 DFS 完成，因此整體 O(n)。
     */
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(nextNonEmpty(br));
        StringTokenizer st = new StringTokenizer(nextNonEmpty(br));
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Node root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }
        if (!isAVL(root).valid) {
            System.out.println("Invalid AVL");
            return;
        }
        System.out.println("Valid");
    }

    private static Node buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node cur = q.poll();
            if (i < arr.length && arr[i] != -1) {
                cur.left = new Node(arr[i]);
                q.offer(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                cur.right = new Node(arr[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    private static boolean isBST(Node root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    static class AVLInfo {
        boolean valid;
        int height;
        AVLInfo(boolean v, int h) { valid = v; height = h; }
    }

    private static AVLInfo isAVL(Node root) {
        if (root == null) return new AVLInfo(true, 0);
        AVLInfo left = isAVL(root.left);
        AVLInfo right = isAVL(root.right);
        if (!left.valid || !right.valid) return new AVLInfo(false, 0);
        if (Math.abs(left.height - right.height) > 1) return new AVLInfo(false, 0);
        return new AVLInfo(true, Math.max(left.height, right.height) + 1);
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
