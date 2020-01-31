//Given a set of non-overlapping intervals, insert a new interval into the inter
//vals (merge if necessary). 
//
// You may assume that the intervals were initially sorted according to their st
//art times. 
//
// Example 1: 
//
// 
//Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
//Output: [[1,5],[6,9]]
// 
//
// Example 2: 
//
// 
//Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//Output: [[1,2],[3,10],[12,16]]
//Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10]. 
//
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

public class InsertInterval {
    public static void main(String[] args) {
        Solution solution = new InsertInterval().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            final List<Pair<Integer, Integer>> segments = Arrays.stream(intervals)
                    .map(ints -> new Pair<>(ints[0], ints[1]))
                    .sorted(Comparator.comparingInt(Pair::getLeft))
                    .collect(Collectors.toList());

            final Pair<Integer, Integer> newSegment = new Pair<>(newInterval[0], newInterval[1]);
            List<Pair<Integer, Integer>> result = new ArrayList<>();
            int index = 0;
            while (index < segments.size()) {
                Pair<Integer, Integer> top = segments.get(index);
                if (top.getRight() >= newSegment.getLeft()) {
                    break;
                }
                result.add(top);
                index++;
            }

            Integer left = newSegment.getLeft();
            Integer right = newSegment.getRight();

            while (index < segments.size()) {
                Pair<Integer, Integer> top = segments.get(index);
                if (top.getLeft() > right) {
                    break;
                }

                left = Math.min(left, top.getLeft());
                right = Math.max(right, top.getRight());
                index++;
            }

            result.add(new Pair<>(left, right));

            if (index < segments.size()) {
                result.addAll(segments.subList(index, segments.size()));
            }

            return result.stream()
                    .map(pair -> new int[]{pair.getLeft(), pair.getRight()})
                    .toArray(int[][]::new);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}