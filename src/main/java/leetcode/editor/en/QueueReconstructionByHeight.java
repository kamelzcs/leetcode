//Suppose you have a random list of people standing in a queue. Each person is d
//escribed by a pair of integers (h, k), where h is the height of the person and k
// is the number of people in front of this person who have a height greater than 
//or equal to h. Write an algorithm to reconstruct the queue. 
//
// Note: 
//The number of people is less than 1,100. 
// 
//
// Example 
//
// 
//Input:
//[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
//Output:
//[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
// 
//
// 
// Related Topics Greedy


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QueueReconstructionByHeight {
    public static void main(String[] args) {
        Solution solution = new QueueReconstructionByHeight().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Pair {
        int value;
        int largerCount;
        Pair(int value, int largerCount) {
            this.value = value;
            this.largerCount = largerCount;
        }
    }
    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(b[0], a[0]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            });
            List<int[]> result = new LinkedList<>();
            for (int[] pair : people) {
                if (pair[1] >= result.size()) {
                    result.add(pair);
                } else {
                    result.add(pair[1], pair);
                }
            }
            return result.stream().toArray(int[][]::new);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}