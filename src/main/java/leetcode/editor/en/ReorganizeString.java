//Given a string S, check if the letters can be rearranged so that two character
//s that are adjacent to each other are not the same. 
//
// If possible, output any possible result. If not possible, return the empty st
//ring. 
//
// Example 1: 
//
// 
//Input: S = "aab"
//Output: "aba"
// 
//
// Example 2: 
//
// 
//Input: S = "aaab"
//Output: ""
// 
//
// Note: 
//
// 
// S will consist of lowercase letters and have length in range [1, 500]. 
// 
//
// 
// Related Topics String Heap Greedy Sort


package leetcode.editor.en;

import java.util.*;
import java.util.stream.Stream;

public class ReorganizeString {
    public static void main(String[] args) {
        Solution solution = new ReorganizeString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reorganizeString(String S) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : S.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            PriorityQueue<Character> queue = new PriorityQueue<Character>(26, Comparator.comparingInt(l -> -map.get(l)));
            queue.addAll(map.keySet());

            Character last = null;
            String result = "";
            while (!queue.isEmpty()) {
                char c = queue.poll();
                if (c == last) {
                    if (queue.isEmpty()) {
                        return "";
                    }
                    char c2 = queue.poll();
                    result += c2;
                    if (map.get(c2) > 1) {
                        map.put(c2, map.get(c2) - 1);
                    }
                } else {
                    result += c;
                    if (map.get(c) > 1) {
                        map.put(c, map.get(c) - 1);
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}