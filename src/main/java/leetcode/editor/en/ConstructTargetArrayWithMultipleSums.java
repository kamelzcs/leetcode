//Given an array of integers target. From a starting array, A consisting of all
//1's, you may perform the following procedure : 
//
// 
// let x be the sum of all elements currently in your array. 
// choose index i, such that 0 <= i < target.size and set the value of A at inde
//x i to x. 
// You may repeat this procedure as many times as needed. 
// 
//
// Return True if it is possible to construct the target array from A otherwise 
//return False. 
//
// 
// Example 1: 
//
// 
//Input: target = [9,3,5]
//Output: true
//Explanation: Start with [1, 1, 1] 
//[1, 1, 1], sum = 3 choose index 1
//[1, 3, 1], sum = 5 choose index 2
//[1, 3, 5], sum = 9 choose index 0
//[9, 3, 5] Done
// 
//
// Example 2: 
//
// 
//Input: target = [1,1,1,2]
//Output: false
//Explanation: Impossible to create target array from [1,1,1,1].
// 
//
// Example 3: 
//
// 
//Input: target = [8,5]
//Output: true
// 
//
// 
// Constraints: 
//
// 
// N == target.length 
// 1 <= target.length <= 5 * 10^4 
// 1 <= target[i] <= 10^9 
// 
// Related Topics Greedy


package leetcode.editor.en;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ConstructTargetArrayWithMultipleSums {
    public static void main(String[] args) {
        Solution solution = new ConstructTargetArrayWithMultipleSums().new Solution();
        System.out.println(solution.isPossible(new int[]{9,3,5}));
        System.out.println(solution.isPossible(new int[]{1, 1, 1, 2}));
        System.out.println(solution.isPossible(new int[]{8, 5}));
        System.out.println(solution.isPossible(new int[]{1, Integer.MAX_VALUE}));
        System.out.println(solution.isPossible(new int[]{1, 1, Integer.MAX_VALUE}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPossible(int[] target) {
            PriorityQueue<Long> pq = new PriorityQueue<Long>(target.length, (a, b) -> Long.compare(b, a));
            long sum = 0;
            for (int i = 0; i < target.length; i++) {
                sum += target[i];
                pq.add((long)target[i]);
            }

            while (!pq.isEmpty()) {
                long top = pq.poll();
                if (top == 1) {
                    return true;
                }

                if (pq.isEmpty()) {
                    return false;
                }

                long next = pq.peek();
                long k = (top - next + sum - top - 1) / (sum - top);
                if (k <= 0) {
                    return false;
                }

                long toAdd = top + k * (top - sum);
                if (toAdd < 1) {
                    return false;
                }
                sum = toAdd - (top - sum);
                pq.add(toAdd);
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}