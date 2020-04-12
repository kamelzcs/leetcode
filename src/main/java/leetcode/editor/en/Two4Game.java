//
//You have 4 cards each containing a number from 1 to 9. You need to judge wheth
//er they could operated through *, /, +, -, (, ) to get the value of 24.
// 
//
// Example 1: 
// 
//Input: [4, 1, 8, 7]
//Output: True
//Explanation: (8-4) * (7-1) = 24
// 
// 
//
// Example 2: 
// 
//Input: [1, 2, 1, 2]
//Output: False
// 
// 
//
// Note: 
// 
// The division operator / represents real division, not integer division. For e
//xample, 4 / (1 - 2/3) = 12. 
// Every operation done is between two numbers. In particular, we cannot use - a
//s a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 -
// 1 - 1 - 1 is not allowed. 
// You cannot concatenate numbers together. For example, if the input is [1, 2, 
//1, 2], we cannot write this as 12 + 12. 
// 
// 
// Related Topics Depth-first Search


package leetcode.editor.en;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Two4Game {
    public static void main(String[] args) {
        Solution solution = new Two4Game().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] nums;
        public boolean judgePoint24(int[] nums) {
            this.nums = nums;
            return dfs(0, 4).contains(24L);
        }

        private Set<Long> dfs(int start, int end) {
            if (end - start == 1) {
                return new HashSet<>(Arrays.asList((long) nums[start]));
            }

            for (int i = start + 1; i < end; i++) {
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}