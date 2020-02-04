//Given an array of integers nums and a positive integer k, find whether it's po
//ssible to divide this array into sets of k consecutive numbers 
//Return True if its possible otherwise return False. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,3,4,4,5,6], k = 4
//Output: true
//Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
//Output: true
//Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,1
//1].
// 
//
// Example 3: 
//
// 
//Input: nums = [3,3,2,2,1,1], k = 3
//Output: true
// 
//
// Example 4: 
//
// 
//Input: nums = [1,2,3,4], k = 3
//Output: false
//Explanation: Each array should be divided in subarrays of size 3.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10^5 
// 1 <= nums[i] <= 10^9 
// 1 <= k <= nums.length 
// Related Topics Array Greedy


package leetcode.editor.en;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DivideArrayInSetsOfKConsecutiveNumbers {
    public static void main(String[] args) {
        Solution solution = new DivideArrayInSetsOfKConsecutiveNumbers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPossibleDivide(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            List<Map.Entry<Integer, Integer>> list = map.entrySet()
                    .stream()
                    .sorted(Comparator.comparingInt(Map.Entry::getKey))
                    .collect(Collectors.toList());

            for (int i = 0; i <= list.size(); i++) {
                Map.Entry<Integer, Integer> current = list.get(i);
                if (current.getValue() == 0) {
                    continue;
                }
                if (i + k > nums.length) {
                    return false;
                }
                for (int delta = 1; delta <= k - 1; delta++) {
                    Map.Entry<Integer, Integer> next = list.get(i + delta);
                    if (next.getKey() != current.getKey() + delta) {
                        return false;
                    }

                    if (next.getValue() < current.getValue()) {
                        return false;
                    }

                    next.setValue(next.getValue() - current.getValue());
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}