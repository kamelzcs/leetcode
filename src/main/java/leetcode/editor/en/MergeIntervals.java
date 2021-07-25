//Given a collection of intervals, merge all overlapping intervals.
//
// Example 1: 
//
// 
//Input: [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// 
//
// Example 2: 
//
// 
//Input: [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping. 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature. 
// Related Topics Array Sort


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new MergeIntervals().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            final List<Pair<Integer, Integer>> segments = Arrays.stream(intervals)
                    .map(ints -> new Pair<>(ints[0], ints[1]))
                    .sorted(Comparator.comparingInt(Pair::getLeft))
                    .collect(Collectors.toList());

            final List<Pair<Integer, Integer>> existing = new ArrayList<>();
            for (final Pair<Integer, Integer> pair : segments) {
                final Integer start = pair.getLeft();
                final Integer end = pair.getRight();

                if (existing.isEmpty()) {
                    existing.add(pair);
                } else {
                    final Pair<Integer, Integer> tail = existing.get(existing.size() - 1);
                    if (tail.getRight() < start) {
                        existing.add(pair);
                    } else {
                        tail.r = Math.max(tail.r, end);
                    }
                }

            }

            return existing.stream()
                    .map(pair -> new int[]{pair.getLeft(), pair.getRight()})
                    .collect(Collectors.toList()).toArray(new int[0][]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}