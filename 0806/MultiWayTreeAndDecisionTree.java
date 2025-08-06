import java.util.*;

public class MultiWayTreeAndDecisionTree {

    
    static class TreeNode {
        String label;
        List<TreeNode> children;

        TreeNode(String label) {
            this.label = label;
            this.children = new ArrayList<>();
        }

        void addChild(TreeNode child) {
            children.add(child);
        }
    }

    public static void dfs(TreeNode root) {
        if (root == null) return;

        System.out.print(root.label + " ");
        for (TreeNode child : root.children) {
            dfs(child);
        }
    }

    public static void bfs(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.label + " ");
            for (TreeNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    public static int height(TreeNode node) {
        if (node == null) return 0;
        if (node.children.isEmpty()) return 1;

        int max = 0;
        for (TreeNode child : node.children) {
            max = Math.max(max, height(child));
        }
        return max + 1;
    }

   
    public static void printNodeDegrees(TreeNode root) {
        if (root == null) return;

        System.out.println("節點 [" + root.label + "] 的度數: " + root.children.size());
        for (TreeNode child : root.children) {
            printNodeDegrees(child);
        }
    }

    public static void runDecisionTree() {
        Scanner sc = new Scanner(System.in);

        TreeNode root = new TreeNode("猜的數字小於 10 嗎？");
        TreeNode lessThan10 = new TreeNode("猜的數字小於 5 嗎？");
        TreeNode between10And20 = new TreeNode("猜的數字小於 15 嗎？");

        TreeNode guess3 = new TreeNode("你猜的是 3！");
        TreeNode guess7 = new TreeNode("你猜的是 7！");
        TreeNode guess12 = new TreeNode("你猜的是 12！");
        TreeNode guess17 = new TreeNode("你猜的是 17！");

        root.addChild(lessThan10);
        root.addChild(between10And20);

        lessThan10.addChild(guess3);
        lessThan10.addChild(guess7);

        between10And20.addChild(guess12);
        between10And20.addChild(guess17);

        
        TreeNode current = root;
        while (!current.children.isEmpty()) {
            System.out.println(current.label + " (yes/no)");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                current = current.children.get(0);
            } else {
                current = current.children.get(1);
            }
        }
        System.out.println("🧠 " + current.label);
    }

    public static void main(String[] args) {
        
        TreeNode root = new TreeNode("A");
        TreeNode b = new TreeNode("B");
        TreeNode c = new TreeNode("C");
        TreeNode d = new TreeNode("D");
        TreeNode e = new TreeNode("E");
        TreeNode f = new TreeNode("F");
        TreeNode g = new TreeNode("G");

        root.addChild(b);
        root.addChild(c);
        root.addChild(d);
        b.addChild(e);
        b.addChild(f);
        d.addChild(g);

        System.out.println("1️⃣ 深度優先走訪:");
        dfs(root);
        System.out.println("\n\n2️⃣ 廣度優先走訪:");
        bfs(root);

        System.out.println("\n\n3️⃣ 樹的高度: " + height(root));
        System.out.println("4️⃣ 每個節點的度數:");
        printNodeDegrees(root);

        System.out.println("\n5️⃣ 啟動猜數字決策樹：");
        runDecisionTree();
    }
}
