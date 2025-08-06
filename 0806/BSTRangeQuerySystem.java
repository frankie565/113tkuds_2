import java.util.*;

public class BSTRangeQuerySystem {


    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        return root;
    }

   
    public static void rangeQuery(TreeNode root, int min, int max, List<Integer> result) {
        if (root == null) return;

        if (root.val > min)
            rangeQuery(root.left, min, max, result);

        if (root.val >= min && root.val <= max)
            result.add(root.val);

        if (root.val < max)
            rangeQuery(root.right, min, max, result);
    }


    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;

        if (root.val < min) return rangeCount(root.right, min, max);
        if (root.val > max) return rangeCount(root.left, min, max);

        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;

        if (root.val < min) return rangeSum(root.right, min, max);
        if (root.val > max) return rangeSum(root.left, min, max);

        return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

  
    public static int closestValue(TreeNode root, int target) {
        int closest = root.val;
        TreeNode current = root;

        while (current != null) {
            if (Math.abs(current.val - target) < Math.abs(closest - target)) {
                closest = current.val;
            }

            if (target < current.val) current = current.left;
            else if (target > current.val) current = current.right;
            else break;
        }

        return closest;
    }


    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        int min = 10, max = 30;
        List<Integer> inRange = new ArrayList<>();
        rangeQuery(root, min, max, inRange);
        System.out.println("1️⃣ 範圍查詢 [" + min + ", " + max + "]： " + inRange);

        System.out.println("2️⃣ 範圍計數 [" + min + ", " + max + "]： " + rangeCount(root, min, max));

        System.out.println("3️⃣ 範圍總和 [" + min + ", " + max + "]： " + rangeSum(root, min, max));

        int target = 28;
        System.out.println("4️⃣ 最接近 " + target + " 的節點值： " + closestValue(root, target));
    }
}
