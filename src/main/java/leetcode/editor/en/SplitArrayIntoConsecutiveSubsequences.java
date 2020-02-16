//Given an array nums sorted in ascending order, return true if and only if you
//can split it into 1 or more subsequences such that each subsequence consists of 
//consecutive integers and has length at least 3. 
//
// 
//
// Example 1: 
//
// 
//Input: [1,2,3,3,4,5]
//Output: True
//Explanation:
//You can split them into two consecutive subsequences : 
//1, 2, 3
//3, 4, 5
//
// 
//
// Example 2: 
//
// 
//Input: [1,2,3,3,4,4,5,5]
//Output: True
//Explanation:
//You can split them into two consecutive subsequences : 
//1, 2, 3, 4, 5
//3, 4, 5
//
// 
//
// Example 3: 
//
// 
//Input: [1,2,3,4,4,5]
//Output: False
// 
//
// 
//
// Constraints: 
//
// 
// 1 <= nums.length <= 10000 
// 
//
// 
// Related Topics Heap Greedy


package leetcode.editor.en;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.PriorityQueue;

public class SplitArrayIntoConsecutiveSubsequences {
    public static void main(String[] args) {
        Solution solution = new SplitArrayIntoConsecutiveSubsequences().new Solution();
        System.out.println(solution.isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
        System.out.println(solution.isPossible(new int[]{1, 2, 3, 3, 4, 5}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPossible(int[] nums) {
            if (nums.length <= 2) {
                return false;
            }
            PriorityQueue<SimpleEntry<Integer, Integer>> pq = new PriorityQueue<>(nums.length / 2, (a, b) -> {
                if (a.getKey() != b.getKey()) {
                    return Integer.compare(a.getKey(), b.getKey());
                } else {
                    return Integer.compare(a.getValue(), b.getValue());
                }
            });
            for (int num : nums) {
                if (pq.isEmpty()) {
                    pq.add(new SimpleEntry<>(num, 1));
                } else {
                    SimpleEntry<Integer, Integer> top = pq.poll();
                    int value = top.getKey();
                    int len = top.getValue();
                    while (value < num - 1) {
                        if (len < 3) {
                            return false;
                        }
                        if(pq.isEmpty()) {
                            break;
                        }
                        top = pq.poll();
                        value = top.getKey();
                        len = top.getValue();
                    }

                    if (value == num - 1) {
                        pq.add(new SimpleEntry<>(num, len + 1));
                    } else {
                        pq.add(top);
                        pq.add(new SimpleEntry<>(num, 1));
                    }
                }
            }

            while (!pq.isEmpty()) {
                if (pq.poll().getValue() < 3) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}