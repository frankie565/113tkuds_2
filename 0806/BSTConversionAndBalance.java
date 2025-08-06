public class BSTConversionAndBalance {

 
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

  
    static class DLLWrapper {
        TreeNode head = null;
        TreeNode prev = null;
    }

    public static TreeNode bstToSortedDoublyList(TreeNode root) {
        DLLWrapper wrapper = new DLLWrapper();
        bstToDLLInOrder(root, wrapper);
        return wrapper.head;
    }

    private static void bstToDLLInOrder(TreeNode node, DLLWrapper wrapper) {
        if (node == null) return;

        bstToDLLInOrder(node.left, wrapper);

        if (wrapper.prev == null) {
            wrapper.head = node;
        } else {
            wrapper.prev.right = node;
            node.left = wrapper.prev;
        }
        wrapper.prev = node;

        bstToDLLInOrder(node.right, wrapper);
    }

    
    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBalanced(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBalanced(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBalanced(nums, left, mid - 1);
        node.right = buildBalanced(nums, mid + 1, right);

        return node;
    }

    
    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private static int checkBalance(TreeNode node) {
        if (node == null) return 0;

        int left = checkBalance(node.left);
        int right = checkBalance(node.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }

   
    public static int getBalanceFactor(TreeNode node) {
        return height(node.left) - height(node.right);
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    
    public static void convertToGreaterTree(TreeNode root) {
        convertReverseInorder(root, new int[]{0});
    }

    private static void convertReverseInorder(TreeNode node, int[] sum) {
        if (node == null) return;
        convertReverseInorder(node.right, sum);
        sum[0] += node.val;
        node.val = sum[0];
        convertReverseInorder(node.left, sum);
    }

   
    public static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }

   
    public static void printDoublyList(TreeNode head) {
        TreeNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
   
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        System.out.println("1️⃣ BST 轉排序雙向串列:");
        TreeNode dllHead = bstToSortedDoublyList(root);
        printDoublyList(dllHead);

        System.out.println("2️⃣ 排序陣列轉平衡 BST:");
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balanced = sortedArrayToBST(sorted);
        printInorder(balanced); System.out.println();

        System.out.println("3️⃣ 是否平衡？" + isBalanced(balanced));
        System.out.println("   根的平衡因子：" + getBalanceFactor(balanced));

        System.out.println("4️⃣ Greater Tree 轉換（原 BST）:");
        convertToGreaterTree(root);
        printInorder(root);  // 預期每個節點值是其自身 + 所有大於它的值
        System.out.println();
    }
}
