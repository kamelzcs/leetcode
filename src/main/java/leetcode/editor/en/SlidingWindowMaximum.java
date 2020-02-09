//Given an array nums, there is a sliding window of size k which is moving from
//the very left of the array to the very right. You can only see the k numbers in 
//the window. Each time the sliding window moves right by one position. Return the
// max sliding window. 
//
// Example: 
//
// 
//Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
//Output: [3,3,5,5,6,7] 
//Explanation: 
//
//Window position                Max
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// Note: 
//You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty arr
//ay. 
//
// Follow up: 
//Could you solve it in linear time? Related Topics Heap Sliding Window


package leetcode.editor.en;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 0 || k == 0) {
                return new int[0];
            }
            int[] result = new int[nums.length - k + 1];
            Deque<Integer> dq = new LinkedList<>();
            for (int i = 0; i < nums.length; i++) {
                while (!dq.isEmpty() && dq.peekLast() < nums[i]) {
                    dq.removeLast();
                }
                dq.add(nums[i]);

                if (i >= k) {
                    int toDelete = nums[i - k];
                    if (dq.peekFirst() == toDelete) {
                        dq.pop();
                    }
                }

                if (i >= k - 1) {
                    result[i - k + 1] = dq.peekFirst();
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}