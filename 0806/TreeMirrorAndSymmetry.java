public class TreeMirrorAndSymmetry {

 
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }


    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.val != b.val) return false;
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

  
    public static void mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    
    public static boolean areMirrors(TreeNode a, TreeNode b) {
        return isMirror(a, b);
    }

    public static boolean isSubtree(TreeNode main, TreeNode sub) {
        if (main == null) return false;
        if (isSameTree(main, sub)) return true;
        return isSubtree(main.left, sub) || isSubtree(main.right, sub);
    }

    private static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.val != b.val) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    
    public static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
    
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);

        System.out.println("1️⃣ 是否為對稱樹: " + isSymmetric(root));  // true

        System.out.print("\n2️⃣ 鏡像前 (Preorder): ");
        printPreorder(root);
        mirror(root);
        System.out.print("\n   鏡像後 (Preorder): ");
        printPreorder(root);

        TreeNode A = new TreeNode(1);
        A.left = new TreeNode(2);
        A.right = new TreeNode(3);

        TreeNode B = new TreeNode(1);
        B.left = new TreeNode(3);
        B.right = new TreeNode(2);

        System.out.println("\n\n3️⃣ A 和 B 是否互為鏡像: " + areMirrors(A, B));  // true

       
        TreeNode main = new TreeNode(4);
        main.left = new TreeNode(5);
        main.right = new TreeNode(6);
        main.right.left = new TreeNode(1);
        main.right.right = new TreeNode(2);

        TreeNode sub = new TreeNode(6);
        sub.left = new TreeNode(1);
        sub.right = new TreeNode(2);

        System.out.println("4️⃣ 是否為子樹: " + isSubtree(main, sub));  // true
    }
}
