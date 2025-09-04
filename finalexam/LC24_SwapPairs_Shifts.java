import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC24_SwapPairs_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (sc.hasNextInt()) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        ListNode head = dummy.next;

        ListNode newHead = swapPairs(head);
        printList(newHead);
    }

    static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

           
            a.next = b.next;
            b.next = a;
            prev.next = b;

          
            prev = a;
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
}

/*
 * Time Complexity: O(n)
 *   每個節點訪問一次並重接指標。
 *
 * Space Complexity: O(1)
 *   僅使用常數額外變數。
 */
