//Given a string which contains only lowercase letters, remove duplicate letters
// so that every letter appears once and only once. You must make sure your result
// is the smallest in lexicographical order among all possible results. 
//
// Example 1: 
//
// 
//Input: "bcabc"
//Output: "abc"
// 
//
// Example 2: 
//
// 
//Input: "cbacdcbc"
//Output: "acdb"
// 
// Related Topics Stack Greedy


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicateLetters().new Solution();
        System.out.println(solution.removeDuplicateLetters("bbcaac"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicateLetters(String s) {
            Map<Character, List<Integer>> posMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                posMap.computeIfAbsent(s.charAt(i), c -> new ArrayList<>()).add(i);
            }

            StringBuilder result = new StringBuilder();
            while (!posMap.isEmpty()) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (!posMap.containsKey(c)) {
                        continue;
                    }

                    List<Integer> posList = posMap.get(c);
                    int top = posList.get(0);
                    boolean valid = true;

                    for (Map.Entry<Character, List<Integer>> others : posMap.entrySet()) {
                        if (others.getKey() == c) {
                            continue;
                        }

                        List<Integer> othersPos = others.getValue();
                        if (othersPos.get(othersPos.size() - 1) < top) {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        result.append(c);
                        posMap.remove(c);
                        for (Map.Entry<Character, List<Integer>> others : posMap.entrySet()) {
                            List<Integer> othersPos = others.getValue();
                            while (!othersPos.isEmpty() && othersPos.get(0) < top) {
                                othersPos.remove(0);
                            }
                        }
                        break;
                    }
                }
            }
            return result.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}