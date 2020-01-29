//Given an array A of positive integers, call a (contiguous, not necessarily dis
//tinct) subarray of A good if the number of different integers in that subarray i
//s exactly K. 
//
// (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.) 
//
// Return the number of good subarrays of A. 
//
// 
//
// Example 1: 
//
// 
//Input: A = [1,2,1,2,3], K = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1],
// [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
// 
//
// Example 2: 
//
// 
//Input: A = [1,2,1,3,4], K = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2
//,1,3], [1,3,4].
// 
//
// 
//
// Note: 
//
// 
// 1 <= A.length <= 20000 
// 1 <= A[i] <= A.length 
// 1 <= K <= A.length 
// Related Topics Hash Table Two Pointers Sliding Window


package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithKDifferentIntegers {
    public static void main(String[] args) {
        Solution solution = new SubarraysWithKDifferentIntegers().new Solution();
        System.out.println(solution.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Window {
        Map<Integer, Integer> map = new HashMap<>();
        int size() {
            return map.size();
        }
        void add(int value) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        void remove(int value) {
            int occurs = map.get(value);
            if (occurs == 1) {
                map.remove(value);
            } else {
                map.put(value, occurs - 1);
            }
        }
    }
    class Solution {
        public int subarraysWithKDistinct(int[] A, int K) {
            int leftEnd = 0;
            int rightEnd = 0;
            Window leftWindow = new Window();
            Window rightWindow = new Window();
            int result = 0;
            for (int i = 0; i < A.length; i++) {
                int value = A[i];
                rightWindow.add(value);
                while (rightWindow.size() == K) {
                    int rightValue = A[rightEnd];
                    rightWindow.remove(rightValue);
                    rightEnd++;
                }

                leftWindow.add(value);
                while (leftWindow.size() == K + 1) {
                    int leftValue = A[leftEnd];
                    leftWindow.remove(leftValue);
                    leftEnd++;
                }
                result += (rightEnd - leftEnd);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
    }