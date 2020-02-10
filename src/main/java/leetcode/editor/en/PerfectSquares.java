//Given a positive integer n, find the least number of perfect square numbers (f
//or example, 1, 4, 9, 16, ...) which sum to n. 
//
// Example 1: 
//
// 
//Input: n = 12
//Output: 3 
//Explanation: 12 = 4 + 4 + 4. 
//
// Example 2: 
//
// 
//Input: n = 13
//Output: 2
//Explanation: 13 = 4 + 9. Related Topics Math Dynamic Programming Breadth-first
// Search


package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class PerfectSquares {
    public static void main(String[] args) {
        Solution solution = new PerfectSquares().new Solution();
        System.out.println("Answer is : " + solution.numSquares(12));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<Integer, Integer> cache = new HashMap<>();
        public int numSquares(int n) {
            if (cache.containsKey(n)) {
                return cache.get(n);
            }
            int result = n;
            for (int i  = 1; i * i <= n; i++) {
                result = Math.min(result, 1 + numSquares(n - i * i));
            }
            cache.put(n, result);
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}