import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int v) { val = v; }
}

public class LC25_ReverseKGroup_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

       
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (sc.hasNextInt()) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        ListNode head = dummy.next;

        ListNode newHead = reverseKGroup(head, k);
        printList(newHead);
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            
            ListNode kth = getKth(prevGroupEnd, k);
            if (kth == null) break;
            ListNode groupNext = kth.next;

        
            ListNode prev = groupNext;
            ListNode curr = prevGroupEnd.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

   
            ListNode tmp = prevGroupEnd.next;
            prevGroupEnd.next = kth;
            prevGroupEnd = tmp;
        }

        return dummy.next;
    }


    static ListNode getKth(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
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
 *   每個節點只被訪問和反轉一次。
 *
 * Space Complexity: O(1)
 *   僅使用常數指標。
 */
