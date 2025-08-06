import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    
    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> invalid = new ArrayList<>();
        inOrderCheck(root, new TreeNode[]{null}, invalid);
        return invalid;
    }

    private static void inOrderCheck(TreeNode node, TreeNode[] prev, List<TreeNode> invalid) {
        if (node == null) return;

        inOrderCheck(node.left, prev, invalid);
        if (prev[0] != null && node.val <= prev[0].val) {
            invalid.add(prev[0]);
            invalid.add(node);
        }
        prev[0] = node;
        inOrderCheck(node.right, prev, invalid);
    }

    
    public static void recoverBST(TreeNode root) {
        TreeNode[] nodes = new TreeNode[2];  
        TreeNode[] prev = new TreeNode[1];  
        findSwappedNodes(root, prev, nodes);

        if (nodes[0] != null && nodes[1] != null) {
            int temp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = temp;
        }
    }

    private static void findSwappedNodes(TreeNode node, TreeNode[] prev, TreeNode[] nodes) {
        if (node == null) return;

        findSwappedNodes(node.left, prev, nodes);
        if (prev[0] != null && node.val < prev[0].val) {
            if (nodes[0] == null) {
                nodes[0] = prev[0];
                nodes[1] = node;
            } else {
                nodes[1] = node;
            }
        }
        prev[0] = node;
        findSwappedNodes(node.right, prev, nodes);
    }

   
    public static int minRemovalsToValidBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);

     
        List<Integer> lis = new ArrayList<>();
        for (int val : inorder) {
            int idx = Collections.binarySearch(lis, val);
            if (idx < 0) idx = -idx - 1;
            if (idx == lis.size()) lis.add(val);
            else lis.set(idx, val);
        }

        return inorder.size() - lis.size();  
    }

    private static void getInorder(TreeNode node, List<Integer> inorder) {
        if (node == null) return;
        getInorder(node.left, inorder);
        inorder.add(node.val);
        getInorder(node.right, inorder);
    }

    
    public static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }

    
    public static void main(String[] args) {
     

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2); 

        System.out.println("1️⃣ 是否為合法 BST: " + isValidBST(root)); 

        System.out.println("2️⃣ 錯誤節點：");
        for (TreeNode n : findInvalidNodes(root)) {
            System.out.print(n.val + " ");
        }
        System.out.println();

        System.out.print("   修復前中序：");
        printInorder(root);
        System.out.println();

        recoverBST(root);

        System.out.print("3️⃣ 修復後中序：");
        printInorder(root);
        System.out.println();
        System.out.println("   修復後為合法 BST？" + isValidBST(root));

        
        TreeNode bad = new TreeNode(10);
        bad.left = new TreeNode(5);
        bad.right = new TreeNode(8);  

        System.out.println("\n4️⃣ 最少需要移除的節點數：" + minRemovalsToValidBST(bad)); 
    }
}
