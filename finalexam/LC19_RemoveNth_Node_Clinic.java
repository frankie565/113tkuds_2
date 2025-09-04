import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC19_RemoveNth_Node_Clinic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        int k = sc.nextInt();

        ListNode newHead = removeNthFromEnd(dummy.next, k);

       
        curr = newHead;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
    }

    static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

    
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        
        slow.next = slow.next.next;

        return dummy.next;
    }
}

/*
 * Time Complexity: O(n)
 *   fast 和 slow 各走一次，總長度掃描一遍。
 *
 * Space Complexity: O(1)
 *   只用常數額外指標。
 */
