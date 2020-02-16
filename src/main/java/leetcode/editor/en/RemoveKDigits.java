//Given a non-negative integer num represented as a string, remove k digits from
// the number so that the new number is the smallest possible.
// 
//
// Note: 
// 
// The length of num is less than 10002 and will be ≥ k. 
// The given num does not contain any leading zero. 
// 
//
// 
//
// Example 1:
// 
//Input: num = "1432219", k = 3
//Output: "1219"
//Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 w
//hich is the smallest.
// 
// 
//
// Example 2:
// 
//Input: num = "10200", k = 1
//Output: "200"
//Explanation: Remove the leading 1 and the number is 200. Note that the output 
//must not contain leading zeroes.
// 
// 
//
// Example 3:
// 
//Input: num = "10", k = 2
//Output: "0"
//Explanation: Remove all the digits from the number and it is left with nothing
// which is 0.
// 
// Related Topics Stack Greedy


package leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class RemoveKDigits {
    public static void main(String[] args) {
        Solution solution = new RemoveKDigits().new Solution();
        System.out.println(solution.removeKdigits("1432219", 3));
        System.out.println(solution.removeKdigits("10200", 1));
        System.out.println(solution.removeKdigits("10", 2));
        System.out.println(solution.removeKdigits("112", 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeKdigits(String num, int k) {
            if (k >= num.length()) {
                return "0";
            }
            Map<Character, Queue<Integer>> charMap = new HashMap<>();
            for (int i = 0; i < num.length(); i++) {
                charMap.computeIfAbsent(num.charAt(i), x -> new LinkedList<Integer>()).add(i);
            }

            Set<Integer> used = new HashSet<>();

            StringBuilder result = new StringBuilder();
            boolean picked = false;
            int lastUsed = -1;
            while(!picked) {
                for (char c = '0'; c <= '9'; c++) {
                    if (!charMap.containsKey(c)) {
                        continue;
                    }

                    Queue<Integer> pos = charMap.get(c);
                    int top = pos.peek();
                    int toDelete = top - used.size();
                    if (toDelete > k) {
                        continue;
                    } else {
                        result.append(c);
                        used.add(top);
                        pos.poll();
                        if (pos.size() == 0) {
                            charMap.remove(c);
                        }
                        if (toDelete == k || used.size() == num.length() - k) {
                            picked = true;
                            lastUsed = top;
                        }
                        break;
                    }
                }
            }

            if (used.size() == num.length() - k) {
                return result.toString().replaceFirst("^0+(?!$)", "");
            }
            for (int i = lastUsed; i < num.length(); i++) {
                if (!used.contains(i)) {
                    result.append(num.charAt(i));
                    used.add(i);
                }
            }
            return result.toString().replaceFirst("^0+(?!$)", "");
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}