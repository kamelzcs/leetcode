//Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we sp
//lit the array into some number of "chunks" (partitions), and individually sort e
//ach chunk. After concatenating them, the result equals the sorted array. 
//
// What is the most number of chunks we could have made? 
//
// Example 1: 
//
// 
//Input: arr = [4,3,2,1,0]
//Output: 1
//Explanation:
//Splitting into two or more chunks will not return the required result.
//For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], 
//which isn't sorted.
// 
//
// Example 2: 
//
// 
//Input: arr = [1,0,2,3,4]
//Output: 4
//Explanation:
//We can split into two chunks, such as [1, 0], [2, 3, 4].
//However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks 
//possible.
// 
//
// Note: 
//
// 
// arr will have length in range [1, 10]. 
// arr[i] will be a permutation of [0, 1, ..., arr.length - 1]. 
// 
//
// 
// Related Topics Array


package leetcode.editor.en;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.PriorityQueue;

public class MaxChunksToMakeSorted {
    public static void main(String[] args) {
        Solution solution = new MaxChunksToMakeSorted().new Solution();
        System.out.println(solution.maxChunksToSorted(new int[]{1,0,2,3,4}));
        System.out.println(solution.maxChunksToSorted(new int[]{0, 2, 1}));
        System.out.println(solution.maxChunksToSorted(new int[]{2, 0, 1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxChunksToSorted(int[] arr) {
            int result = 0;
            int currentMax = Integer.MIN_VALUE;
            for (int i = 0; i < arr.length; i++) {
                currentMax = Math.max(currentMax, arr[i]);
                if (currentMax == i) {
                    result++;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}