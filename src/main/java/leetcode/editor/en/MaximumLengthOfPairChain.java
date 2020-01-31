//
//You are given n pairs of numbers. In every pair, the first number is always sm
//aller than the second number.
// 
//
// 
//Now, we define a pair (c, d) can follow another pair (a, b) if and only if b <
// c. Chain of pairs can be formed in this fashion. 
// 
//
// 
//Given a set of pairs, find the length longest chain which can be formed. You n
//eedn't use up all the given pairs. You can select pairs in any order.
// 
//
//
// Example 1: 
// 
//Input: [[1,2], [2,3], [3,4]]
//Output: 2
//Explanation: The longest chain is [1,2] -> [3,4]
// 
// 
//
// Note: 
// 
// The number of given pairs will be in the range [1, 1000]. 
// 
// Related Topics Dynamic Programming


package leetcode.editor.en;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MaximumLengthOfPairChain {
    public static void main(String[] args) {
        Solution solution = new MaximumLengthOfPairChain().new Solution();
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
        TreeSet<Integer> set = new TreeSet<>();
        public int findLongestChain(int[][] pairs) {
            List<Pair> list = Arrays.stream(pairs)
                    .map(pair -> new Pair(pair[0], pair[1]))
                    .sorted(Comparator.comparingInt(x -> x.left))
                    .collect(Collectors.toList());
            for (Pair pair: list) {
                if (set.isEmpty()) {
                    set.add(pair.right);
                } else {
                    Integer smaller = set.floor(pair.left - 1);
                    Integer last = set.last();
                    if (smaller == last) {
                        set.add(pair.right);
                    } else {
                        set.remove(last);
                        set.add(Math.min(last, pair.right));
                    }
                }
            }
            return set.size();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
