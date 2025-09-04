import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC23_MergeKLists_Hospitals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            lists[i] = buildList(sc);
        }

        ListNode merged = mergeKLists(lists);
        printList(merged);
    }

    static ListNode buildList(Scanner sc) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (true) {
            int x = sc.nextInt();
            if (x == -1) break;
            curr.next = new ListNode(x);
            curr = curr.next;
        }
        return dummy.next;
    }

    static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
    }

    static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            tail.next = min;
            tail = tail.next;
            if (min.next != null) pq.offer(min.next);
        }

        return dummy.next;
    }
}

/*
 * Time Complexity: O(N log k)
 *   N = 總節點數；每次 push/pop 堆 O(log k)。
 *
 * Space Complexity: O(k)
 *   儲存 k 條串列的當前節點於最小堆。
 */
