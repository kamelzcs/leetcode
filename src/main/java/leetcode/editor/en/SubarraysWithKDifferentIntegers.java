//Given an array A of positive integers, call a (contiguous, not necessarily dis
//tinct) subarray of A good if the number of different integers in that subarray i
//s exactly K. 
//
// (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.) 
//
// Return the number of good subarrays of A. 
//
// 
//
// Example 1: 
//
// 
//Input: A = [1,2,1,2,3], K = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1],
// [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
// 
//
// Example 2: 
//
// 
//Input: A = [1,2,1,3,4], K = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2
//,1,3], [1,3,4].
// 
//
// 
//
// Note: 
//
// 
// 1 <= A.length <= 20000 
// 1 <= A[i] <= A.length 
// 1 <= K <= A.length 
// Related Topics Hash Table Two Pointers Sliding Window


package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithKDifferentIntegers {
    public static void main(String[] args) {
        Solution solution = new SubarraysWithKDifferentIntegers().new Solution();
        System.out.println(solution.subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Pair {
        int value;
        int index;
        Pair(int l, int r) {
            this.value = l;
            this.index = r;
        }
    }

    class Node {
        Pair pair;
        Node next;
        Node prev;
        Node (Pair pair) {
            this.pair = pair;
        }

        void addAfter(Node node) {
            Node after = this.next;
            this.next = node;
            node.prev = this;

            node.next = after;
            after.prev = node;
        }

        void delete() {
            Node prev = this.prev;
            Node next = this.next;

            prev.next = next;
            next.prev = prev;
        }
    }

    class DoubleList {
        Node root;

        DoubleList() {
            root = new Node(new Pair(-1, -1));
            root.prev = root;
            root.next = root;
        }

        void addToTail(Node node) {
            Node tail = root.prev;
            tail.addAfter(node);
        }

        Node head() {
            return root.next;
        }

        void remove(Node node) {
            node.delete();
        }
    }

    class Solution {
        Map<Integer, Node> map = new HashMap<>();
        DoubleList list = new DoubleList();
        public int subarraysWithKDistinct(int[] A, int K) {
            int result = 0;
            for (int i = 0; i < A.length; i++) {
                int v = A[i];

                Node node = new Node(new Pair(v, i));
                if (!map.containsKey(v)) {
                    list.addToTail(node);
                } else {
                    Node existing = map.get(v);
                    list.remove(existing);
                    list.addToTail(node);
                }
                map.put(v, node);

                if (map.size() == K) {
                    result += list.head().pair.index + 1;
                } else if (map.size() == K + 1) {
                    result += list.head().next.pair.index - list.head().pair.index;
                } else if (map.size() == K + 2) {
                    map.remove(list.head().pair.value);
                    list.remove(list.head());
                    result += list.head().next.pair.index - list.head().pair.index;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}