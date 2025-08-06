import java.util.*;

public class LevelOrderTraversalVariations {


    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

  
    public static List<List<Integer>> levelOrderByLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(level);
        }
        return result;
    }

     public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        boolean leftToRight = true;

        while (!dq.isEmpty()) {
            int size = dq.size();
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = dq.poll();

                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);

                if (node.left != null) dq.offer(node.left);
                if (node.right != null) dq.offer(node.right);
            }

            result.add(level);
            leftToRight = !leftToRight;
        }

        return result;
    }

    public static List<Integer> rightmostPerLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode last = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                last = node;

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            if (last != null) result.add(last.val);
        }

        return result;
    }

  
    static class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, List<Integer>> colMap = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            TreeNode node = p.node;
            int col = p.col;

            colMap.putIfAbsent(col, new ArrayList<>());
            colMap.get(col).add(node.val);

            if (node.left != null) q.offer(new Pair(node.left, col - 1));
            if (node.right != null) q.offer(new Pair(node.right, col + 1));
        }

        result.addAll(colMap.values());
        return result;
    }


    public static void main(String[] args) {
      
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("1️⃣ 每層 List: " + levelOrderByLevel(root));
        System.out.println("2️⃣ 之字形層序: " + zigzagLevelOrder(root));
        System.out.println("3️⃣ 每層最後一個節點: " + rightmostPerLevel(root));
        System.out.println("4️⃣ 垂直層序: " + verticalOrder(root));
    }
}
