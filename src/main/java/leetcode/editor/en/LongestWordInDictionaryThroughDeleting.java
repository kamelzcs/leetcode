//
//Given a string and a string dictionary, find the longest string in the diction
//ary that can be formed by deleting some characters of the given string. If there
// are more than one possible results, return the longest word with the smallest l
//exicographical order. If there is no possible result, return the empty string.
// 
// Example 1: 
// 
//Input:
//s = "abpcplea", d = ["ale","apple","monkey","plea"]
//
//Output: 
//"apple"
// 
// 
//
// 
// Example 2: 
// 
//Input:
//s = "abpcplea", d = ["a","b","c"]
//
//Output: 
//"a"
// 
// 
//
// Note: 
// 
// All the strings in the input will only contain lower-case letters. 
// The size of the dictionary won't exceed 1,000. 
// The length of all the strings in the input won't exceed 1,000. 
// 
// Related Topics Two Pointers Sort


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LongestWordInDictionaryThroughDeleting {
    public static void main(String[] args) {
        Solution solution = new LongestWordInDictionaryThroughDeleting().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<Character, List<Integer>> posMap = new HashMap<>();

        public String findLongestWord(String s, List<String> d) {
            for (int i = 0; i < s.length(); i++) {
                posMap.computeIfAbsent(s.charAt(i), p -> new ArrayList<>()).add(i);
            }

            return d.stream()
                    .filter(this::match)
                    .max((a, b) -> {
                        if (a.length() != b.length()) {
                            return Integer.compare(b.length(), a.length());
                        } else {
                            return a.compareTo(b);
                        }
                    }).orElse("");
        }

        private boolean match(String s) {
            Map<Character, Iterator<Integer>> posIter =
                    posMap.entrySet().stream()
                    .collect(
                            Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().iterator())
                    );
            int currentPos = -1;
            for (char c : s.toCharArray()) {
                Iterator<Integer> pos = posIter.get(c);
                if (pos == null) {
                    return false;
                }
                while (true) {
                    if (!pos.hasNext()) {
                        return false;
                    }
                    int next = pos.next();
                    if (next > currentPos) {
                        currentPos = next;
                        break;
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}