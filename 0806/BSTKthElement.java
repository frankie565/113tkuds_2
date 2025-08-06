import java.util.*;

public class BSTKthElement {

    
    static class TreeNode {
        int val;
        int size;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

   
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);

        root.size = 1 + getSize(root.left) + getSize(root.right);
        return root;
    }

    
    public static TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;

        if (val < root.val) {
            root.left = delete(root.left, val);
        } else if (val > root.val) {
            root.right = delete(root.right, val);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode successor = findMin(root.right);
            root.val = successor.val;
            root.right = delete(root.right, successor.val);
        }

        root.size = 1 + getSize(root.left) + getSize(root.right);
        return root;
    }

    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    
    private static int getSize(TreeNode node) {
        return node == null ? 0 : node.size;
    }


    
    public static int kthSmallest(TreeNode root, int k) {
        if (root == null) throw new IllegalArgumentException("k超出範圍");

        int leftSize = getSize(root.left);

        if (k == leftSize + 1) return root.val;
        else if (k <= leftSize) return kthSmallest(root.left, k);
        else return kthSmallest(root.right, k - leftSize - 1);
    }

   
    public static int kthLargest(TreeNode root, int k) {
        int total = getSize(root);
        return kthSmallest(root, total - k + 1);
    }

  
    public static List<Integer> rangeKtoJ(TreeNode root, int k, int j) {
        List<Integer> result = new ArrayList<>();
        inOrderRange(root, new int[]{0}, k, j, result);
        return result;
    }

    private static void inOrderRange(TreeNode node, int[] count, int k, int j, List<Integer> result) {
        if (node == null) return;

        inOrderRange(node.left, count, k, j, result);
        count[0]++;
        if (count[0] >= k && count[0] <= j) {
            result.add(node.val);
        }
        inOrderRange(node.right, count, k, j, result);
    }

    

    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        System.out.println("1️⃣ 第 3 小元素：" + kthSmallest(root, 3)); 
        System.out.println("2️⃣ 第 2 大元素：" + kthLargest(root, 2)); 
        System.out.println("3️⃣ 第 2 小到第 5 小：" + rangeKtoJ(root, 2, 5));  

        System.out.println("4️⃣ 刪除節點 15...");
        root = delete(root, 15);
        System.out.println("   第 3 小元素（更新後）：" + kthSmallest(root, 3));  

        System.out.println("   插入 12...");
        root = insert(root, 12);
        System.out.println("   第 3 小元素（更新後）：" + kthSmallest(root, 3));  
    }
}
