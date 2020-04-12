//Merge k sorted linked lists and return it as one sorted list. Analyze and desc
//ribe its complexity. 
//
// Example: 
//
// 
//Input:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//Output: 1->1->2->3->4->4->5->6
// 
// Related Topics Linked List Divide and Conquer Heap


package leetcode.editor.en;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeKSortedLists().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
//        public ListNode mergeKLists(ListNode[] lists) {
//            ListNode root = new ListNode(-1);
//            ListNode current = root;
//            PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length + 1, Comparator.comparingInt((ListNode x) -> x.val));
//            for (ListNode listNode : lists) {
//                if (listNode != null) {
//                    pq.add(listNode);
//                }
//            }
//            while (!pq.isEmpty()) {
//                ListNode top = pq.poll();
//                current.next = top;
//                current = current.next;
//                if (top.next != null) {
//                    pq.add(top.next);
//                }
//            }
//            return root.next;
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}