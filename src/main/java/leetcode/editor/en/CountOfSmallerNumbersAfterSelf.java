//You are given an integer array nums and you have to return a new counts array.
// The counts array has the property where counts[i] is the number of smaller elem
//ents to the right of nums[i]. 
//
// Example: 
//
// 
//Input: [5,2,6,1]
//Output: [2,1,1,0] 
//Explanation:
//To the right of 5 there are 2 smaller elements (2 and 1).
//To the right of 2 there is only 1 smaller element (1).
//To the right of 6 there is 1 smaller element (1).
//To the right of 1 there is 0 smaller element.
// Related Topics Binary Search Divide and Conquer Sort Binary Indexed Tree Segm
//ent Tree


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        Solution solution = new CountOfSmallerNumbersAfterSelf().new Solution();
        solution.countSmaller(new int[]{5, 2, 6, 1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<Integer, Integer> mapped;
        int[] count;
        int size;
        public List<Integer> countSmaller(int[] nums) {
            List<Integer> result = new ArrayList<>();
            mapped = redax(nums);
            size = mapped.size();
            count = new int[size + 1];
            for (int i = nums.length - 1; i >= 0; i--) {
                int smaller = count(mapped.get(nums[i]) - 1);
                result.add(0, smaller);
                update(mapped.get(nums[i]), 1);
            }
            return result;
        }

        private void update(Integer index, int v) {
            while (index <= size) {
                count[index] += v;
                index += getLastBit(index);
            }
        }

        private int count(int index) {
            int result = 0;
            while (index > 0) {
                result += count[index];
                index -= getLastBit(index);
            }
            return result;
        }

        private int getLastBit(int index) {
            return index & (-index);
        }

        private Map<Integer, Integer> redax(int[] nums) {
            Map<Integer, Integer> mapped = new HashMap<>();
            int[] copied = Arrays.copyOf(nums, nums.length);
            Arrays.sort(copied);

            for (int i = 0; i < copied.length; i++) {
                if (i > 0 && copied[i] == copied[i - 1]) {
                    continue;
                }
                mapped.put(copied[i], mapped.size() + 1);
            }
            return mapped;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}