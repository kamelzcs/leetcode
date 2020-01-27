//There is a one-dimensional garden on the x-axis. The garden starts at the poin
//t 0 and ends at the point n. (i.e The length of the garden is n). 
//
// There are n + 1 taps located at points [0, 1, ..., n] in the garden. 
//
// Given an integer n and an integer array ranges of length n + 1 where ranges[i
//] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i
//]] if it was open. 
//
// Return the minimum number of taps that should be open to water the whole gard
//en, If the garden cannot be watered return -1. 
//
// 
// Example 1: 
//
// 
//Input: n = 5, ranges = [3,4,1,1,0,0]
//Output: 1
//Explanation: The tap at point 0 can cover the interval [-3,3]
//The tap at point 1 can cover the interval [-3,5]
//The tap at point 2 can cover the interval [1,3]
//The tap at point 3 can cover the interval [2,4]
//The tap at point 4 can cover the interval [4,4]
//The tap at point 5 can cover the interval [5,5]
//Opening Only the second tap will water the whole garden [0,5]
// 
//
// Example 2: 
//
// 
//Input: n = 3, ranges = [0,0,0,0]
//Output: -1
//Explanation: Even if you activate all the four taps you cannot water the whole
// garden.
// 
//
// Example 3: 
//
// 
//Input: n = 7, ranges = [1,2,1,0,2,1,0,1]
//Output: 3
// 
//
// Example 4: 
//
// 
//Input: n = 8, ranges = [4,0,0,0,0,0,0,0,4]
//Output: 2
// 
//
// Example 5: 
//
// 
//Input: n = 8, ranges = [4,0,0,0,4,0,0,0,4]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 10^4 
// ranges.length == n + 1 
// 0 <= ranges[i] <= 100 
// Related Topics Dynamic Programming Greedy


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

public class MinimumNumberOfTapsToOpenToWaterAGarden {
    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfTapsToOpenToWaterAGarden().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class Pair {
            int left;
            int right;
            Pair(int left, int right) {
                this.left = left;
                this.right = right;
            }
        }

        public int minTaps(int n, int[] ranges) {
            final List<Pair> segments = new ArrayList<>(ranges.length);
            for (int i = 0; i < ranges.length; i++) {
                segments.add(new Pair(i - ranges[i], i + ranges[i]));
            }
            segments.sort((a, b) -> {
                if (a.left == b.left) {
                    return Integer.compare(b.right, a.right);
                } else {
                    return Integer.compare(a.left, b.left);
                }
            });

            int nextCoverPoint = 0;
            int lastIndex = 0;
            int result = 0;
            while (nextCoverPoint <= n) {
                int coveredRightMost = -1;
                result++;
                while (lastIndex < segments.size() && segments.get(lastIndex).left <= nextCoverPoint) {
                    coveredRightMost = Math.max(coveredRightMost, segments.get(lastIndex).right);
                    lastIndex++;
                }
                nextCoverPoint = coveredRightMost + 1;
            }
            return nextCoverPoint > n ? result : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}