import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;

        TreeNode root = new TreeNode(pre[ps]);
        int inRoot = inMap.get(root.val);
        int leftSize = inRoot - is;

        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, inRoot - 1, inMap);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, inRoot + 1, ie, inMap);

        return root;
    }

    public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
        if (ps > pe || is > ie) return null;

        TreeNode root = new TreeNode(post[pe]);
        int inRoot = inMap.get(root.val);
        int leftSize = inRoot - is;

        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, inRoot - 1, inMap);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, inRoot + 1, ie, inMap);

        return root;
    }

 
    public static TreeNode buildFromLevelOrder(Integer[] levelOrder) {
        if (levelOrder.length == 0 || levelOrder[0] == null) return null;

        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (i < levelOrder.length) {
            TreeNode node = queue.poll();

            if (i < levelOrder.length && levelOrder[i] != null) {
                node.left = new TreeNode(levelOrder[i]);
                queue.offer(node.left);
            }
            i++;

            if (i < levelOrder.length && levelOrder[i] != null) {
                node.right = new TreeNode(levelOrder[i]);
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }

   
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    
    public static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

   
    public static void printPostorder(TreeNode root) {
        if (root == null) return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.val + " ");
    }


    public static void main(String[] args) {
     
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode tree1 = buildFromPreIn(preorder, inorder);
        System.out.println("1️⃣ 前序+中序 建樹 Inorder:");
        printInorder(tree1); System.out.println();

        
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode tree2 = buildFromPostIn(postorder, inorder);
        System.out.println("2️⃣ 後序+中序 建樹 Preorder:");
        printPreorder(tree2); System.out.println();

      
        Integer[] levelOrder = {1, 2, 3, 4, 5, 6, 7};
        TreeNode tree3 = buildFromLevelOrder(levelOrder);
        System.out.println("3️⃣ 層序建完全樹 Postorder:");
        printPostorder(tree3); System.out.println();
    }
}
