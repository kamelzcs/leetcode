//Given an array of events where events[i] = [startDayi, endDayi]. Every event i
// starts at startDayi and ends at endDayi. 
//
// You can attend an event i at any day d where startTimei <= d <= endTimei. Not
//ice that you can only attend one event at any time d. 
//
// Return the maximum number of events you can attend. 
//
// 
// Example 1: 
//
// 
//Input: events = [[1,2],[2,3],[3,4]]
//Output: 3
//Explanation: You can attend all the three events.
//One way to attend them all is as shown.
//Attend the first event on day 1.
//Attend the second event on day 2.
//Attend the third event on day 3.
// 
//
// Example 2: 
//
// 
//Input: events= [[1,2],[2,3],[3,4],[1,2]]
//Output: 4
// 
//
// Example 3: 
//
// 
//Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
//Output: 4
// 
//
// Example 4: 
//
// 
//Input: events = [[1,100000]]
//Output: 1
// 
//
// Example 5: 
//
// 
//Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
//Output: 7
// 
//
// 
// Constraints: 
//
// 
// 1 <= events.length <= 10^5 
// events[i].length == 2 
// 1 <= events[i][0] <= events[i][1] <= 10^5 
// Related Topics Greedy Sort Segment Tree


package leetcode.editor.en;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class MaximumNumberOfEventsThatCanBeAttended {
    public static void main(String[] args) {
        Solution solution = new MaximumNumberOfEventsThatCanBeAttended().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxEvents(int[][] events) {
            int result = 0;
            int toCoverIndex = 0;
            List<SimpleEntry<Integer, Integer>> sortedEvents = Arrays.stream(events)
                    .map(x -> new SimpleEntry<>(x[0], x[1]))
                    .sorted(Comparator.comparingInt(SimpleEntry::getKey))
                    .collect(Collectors.toList());
            PriorityQueue<SimpleEntry<Integer, Integer>> queue = new PriorityQueue<>(1000, Comparator.comparingInt(SimpleEntry::getValue));
            for (int i = 1; i <= 100000; i++) {
                while (toCoverIndex < sortedEvents.size() && sortedEvents.get(toCoverIndex).getKey() == i) {
                    queue.add(sortedEvents.get(toCoverIndex));
                    toCoverIndex++;
                }

                while (!queue.isEmpty()) {
                    SimpleEntry<Integer, Integer> top = queue.peek();
                    queue.poll();
                    if (top.getValue() >= i) {
                        result++;
                        break;
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}