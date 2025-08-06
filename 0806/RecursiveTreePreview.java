import java.util.*;

public class RecursiveTreePreview {

  
    static class FileNode {
        String name;
        boolean isFile;
        List<FileNode> children;

        FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            this.children = new ArrayList<>();
        }

        void addChild(FileNode node) {
            children.add(node);
        }
    }

    public static int countFiles(FileNode node) {
        if (node.isFile) return 1;
        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    static class MenuItem {
        String title;
        List<MenuItem> children = new ArrayList<>();

        MenuItem(String title) {
            this.title = title;
        }

        void addChild(MenuItem item) {
            children.add(item);
        }
    }

    public static void printMenu(MenuItem item, int level) {
        System.out.println("  ".repeat(level) + "- " + item.title);
        for (MenuItem child : item.children) {
            printMenu(child, level + 1);
        }
    }

    
    public static List<Integer> flatten(Object[] nested) {
        List<Integer> result = new ArrayList<>();
        for (Object obj : nested) {
            if (obj instanceof Integer) {
                result.add((Integer) obj);
            } else if (obj instanceof Object[]) {
                result.addAll(flatten((Object[]) obj));
            }
        }
        return result;
    }

    public static int maxDepth(Object[] nested) {
        int max = 1;
        for (Object obj : nested) {
            if (obj instanceof Object[]) {
                max = Math.max(max, 1 + maxDepth((Object[]) obj));
            }
        }
        return max;
    }

    
    public static void main(String[] args) {

        FileNode root = new FileNode("root", false);
        FileNode folder1 = new FileNode("docs", false);
        FileNode file1 = new FileNode("a.txt", true);
        FileNode file2 = new FileNode("b.txt", true);
        FileNode subfolder = new FileNode("images", false);
        FileNode file3 = new FileNode("cat.jpg", true);

        root.addChild(folder1);
        folder1.addChild(file1);
        folder1.addChild(file2);
        folder1.addChild(subfolder);
        subfolder.addChild(file3);

        System.out.println("1️⃣ 總檔案數: " + countFiles(root));  

        
        MenuItem menu = new MenuItem("主選單");
        MenuItem submenu1 = new MenuItem("檔案");
        submenu1.addChild(new MenuItem("開啟"));
        submenu1.addChild(new MenuItem("儲存"));
        menu.addChild(submenu1);

        MenuItem submenu2 = new MenuItem("編輯");
        submenu2.addChild(new MenuItem("複製"));
        submenu2.addChild(new MenuItem("貼上"));
        menu.addChild(submenu2);

        System.out.println("\n2️⃣ 多層選單：");
        printMenu(menu, 0);

        Object[] nestedArray = new Object[]{1, new Object[]{2, new Object[]{3, 4}}, 5};
        System.out.println("\n3️⃣ 展平陣列：" + flatten(nestedArray));  // ➤ [1, 2, 3, 4, 5]

        System.out.println("4️⃣ 巢狀最大深度：" + maxDepth(nestedArray));  // ➤ 3
    }
}
