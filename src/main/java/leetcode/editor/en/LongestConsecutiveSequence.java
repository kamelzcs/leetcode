//Given an unsorted array of integers, find the length of the longest consecutiv
//e elements sequence. 
//
// Your algorithm should run in O(n) complexity. 
//
// Example: 
//
// 
//Input: [100, 4, 200, 1, 3, 2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Theref
//ore its length is 4.
// 
// Related Topics Array Union Find


package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        Solution solution = new LongestConsecutiveSequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Pair {
        int left;
        int right;
        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    class Solution {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Pair> map = new HashMap<>();
        public int longestConsecutive(int[] nums) {
            int result = 0;
            for (int num: nums) {
                parent.put(num, num);
                map.put(num, new Pair(num, num));
            }

            for (int num : nums) {
                int rightMost = num;
                int leftMost = num;

                int rightCandidate = num + 1;
                if (parent.containsKey(rightCandidate)) {
                    int rightParent = getParent(rightCandidate);
                    rightMost = map.get(rightParent).right;

                    merge(rightParent, num);
                }

                int leftCandidate = num - 1;
                if (parent.containsKey(leftCandidate)) {
                    int leftParent = getParent(leftCandidate);
                    leftMost = map.get(leftParent).left;

                    merge(leftParent, num);
                }

                map.put(getParent(num), new Pair(leftMost, rightMost));
                result = Math.max(result, rightMost - leftMost + 1);
            }
            return result;
        }

        private void merge(int left, int right) {
            int leftParent = getParent(left);
            int rightParent = getParent(right);
            if (leftParent != rightParent) {
                parent.put(leftParent, rightParent);
            }
        }

        private int getParent(int rightCandidate) {
            if (parent.get(rightCandidate) == rightCandidate) {
                return rightCandidate;
            }
            parent.put(rightCandidate, getParent(parent.get(rightCandidate)));
            return parent.get(rightCandidate);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}