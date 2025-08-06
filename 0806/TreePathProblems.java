import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

 
    public static List<List<Integer>> allPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPaths(root, new ArrayList<>(), result);
        return result;
    }

    private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);

        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            dfsPaths(node.left, path, result);
            dfsPaths(node.right, path, result);
        }

        path.remove(path.size() - 1); 
    }


    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) return false;

        if (root.left == null && root.right == null)
            return root.val == target;

        return hasPathSum(root.left, target - root.val) ||
               hasPathSum(root.right, target - root.val);
    }

 
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;

        if (root.left == null && root.right == null)
            return root.val;

        int left = maxRootToLeafSum(root.left);
        int right = maxRootToLeafSum(root.right);

        return root.val + Math.max(left, right);
    }

   
    static class MaxPathSum {
        int max = Integer.MIN_VALUE;
    }

    public static int maxPathSum(TreeNode root) {
        MaxPathSum result = new MaxPathSum();
        maxPathSumHelper(root, result);
        return result.max;
    }

    private static int maxPathSumHelper(TreeNode node, MaxPathSum result) {
        if (node == null) return 0;

        int left = Math.max(0, maxPathSumHelper(node.left, result));
        int right = Math.max(0, maxPathSumHelper(node.right, result));

        result.max = Math.max(result.max, node.val + left + right);

        return node.val + Math.max(left, right); 
    }

    
    public static void main(String[] args) {
    

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        
        System.out.println("1️⃣ 根到葉路徑：");
        List<List<Integer>> paths = allPaths(root);
        for (List<Integer> path : paths) {
            System.out.println(path);
        }

     
        System.out.println("\n2️⃣ 是否存在總和為 22 的根到葉路徑？" + hasPathSum(root, 22));

       
        System.out.println("3️⃣ 最大根到葉路徑總和：" + maxRootToLeafSum(root));

        
        System.out.println("4️⃣ 樹的最大路徑總和：" + maxPathSum(root));
    }
}
