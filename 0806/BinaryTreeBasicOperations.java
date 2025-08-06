import java.util.*;

public class BinaryTreeBasicOperations {


    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }


    static class SumResult {
        int sum = 0;
        int count = 0;
    }

    public static void calculateSumAndAverage(TreeNode root, SumResult result) {
        if (root == null) return;
        result.sum += root.val;
        result.count++;
        calculateSumAndAverage(root.left, result);
        calculateSumAndAverage(root.right, result);
    }

 
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

  
    public static int maxWidth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return maxWidth;
    }

    
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean shouldBeLeaf = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                if (shouldBeLeaf) return false;
                queue.offer(node.left);
            } else {
                shouldBeLeaf = true;
            }

            if (node.right != null) {
                if (shouldBeLeaf) return false;
                queue.offer(node.right);
            } else {
                shouldBeLeaf = true;
            }
        }

        return true;
    }

    
    public static void main(String[] args) {
   
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        
        SumResult result = new SumResult();
        calculateSumAndAverage(root, result);
        double average = (result.count == 0) ? 0 : (double) result.sum / result.count;
        System.out.println("1️⃣ 節點總和: " + result.sum);
        System.out.println("   平均值: " + average);

      
        System.out.println("2️⃣ 最大值節點: " + findMax(root));
        System.out.println("   最小值節點: " + findMin(root));

     
        System.out.println("3️⃣ 樹的最大寬度: " + maxWidth(root));

       
        System.out.println("4️⃣ 是否為完全二元樹: " + isCompleteBinaryTree(root));
    }
}
