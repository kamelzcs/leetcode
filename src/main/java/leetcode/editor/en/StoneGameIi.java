//Alex and Lee continue their games with piles of stones. There are a number of
//piles arranged in a row, and each pile has a positive integer number of stones p
//iles[i]. The objective of the game is to end with the most stones. 
//
// Alex and Lee take turns, with Alex starting first. Initially, M = 1. 
//
// On each player's turn, that player can take all the stones in the first X rem
//aining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). 
//
// The game continues until all the stones have been taken. 
//
// Assuming Alex and Lee play optimally, return the maximum number of stones Ale
//x can get. 
//
// 
// Example 1: 
//
// 
//Input: piles = [2,7,9,4,4]
//Output: 10
//Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, th
//en Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex
// takes two piles at the beginning, then Lee can take all three piles left. In th
//is case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 
// 
//
// 
// Constraints: 
//
// 
// 1 <= piles.length <= 100 
// 1 <= piles[i] <= 10 ^ 4 
// Related Topics Dynamic Programming


package leetcode.editor.en;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

public class StoneGameIi {
    public static void main(String[] args) {
        Solution solution = new StoneGameIi().new Solution();
//        System.out.println(solution.stoneGameII(new int[]{2, 7, 9, 4, 4}));
        System.out.println(solution.stoneGameII(new int[]{8270,7145,575,5156,5126,2905,8793,7817,5532,5726,7071,7730,5200,5369,5763,7148,8287,9449,7567,4850,1385,2135,1737,9511,8065,7063,8023,7729,7084,8407}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] sum;
        int len;
        Map<SimpleEntry<Integer, Integer>, Integer> cache = new HashMap<>();

        int dp(int index, int m) {
            SimpleEntry<Integer, Integer> entry = new SimpleEntry<>(index, m);
            if (cache.containsKey(entry)) {
                return cache.get(entry);
            }

            int result = 0;
            for (int l = 1; l <= Math.min(2 * m, len - index); l++) {
                int nextStart = index + l;
                result = Math.max(result, sum[index] - dp(nextStart, Math.max(l, m)));
            }
            cache.put(entry, result);
            return result;
        }

        public int stoneGameII(int[] piles) {
            len = piles.length;
            sum = new int[len + 1];
            for (int i = len - 1; i >= 0; i--) {
                sum[i] = sum[i + 1] + piles[i];
            }

            return dp(0, 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}