//You have a list of words and a pattern, and you want to know which words in wo
//rds matches the pattern. 
//
// A word matches the pattern if there exists a permutation of letters p so that
// after replacing every letter x in the pattern with p(x), we get the desired wor
//d. 
//
// (Recall that a permutation of letters is a bijection from letters to letters:
// every letter maps to another letter, and no two letters map to the same letter.
//) 
//
// Return a list of the words in words that match the given pattern. 
//
// You may return the answer in any order. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
//Output: ["mee","aqq"]
//Explanation: "mee" matches the pattern because there is a permutation {a -> m,
// b -> e, ...}. 
//"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permut
//ation,
//since a and b map to the same letter. 
//
// 
//
// Note: 
//
// 
// 1 <= words.length <= 50 
// 1 <= pattern.length = words[i].length <= 20 
// 
// 
// Related Topics String


package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindAndReplacePattern {
    public static void main(String[] args) {
        Solution solution = new FindAndReplacePattern().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        String pattern;
        public List<String> findAndReplacePattern(String[] words, String pattern) {
            this.pattern = pattern;
            return Arrays.stream(words).filter(this::couldAdd).collect(Collectors.toList());
        }

        private boolean couldAdd(String word) {
            Map<Character, Character> map = new HashMap<>();
            for (int i = 0; i < word.length(); i++) {
                char from = word.charAt(i);
                char to = pattern.charAt(i);

                if (map.containsKey(from)) {
                    if (map.get(from) != to) {
                        return false;
                    }
                } else {
                    if (map.containsValue(to)) {
                        return false;
                    } else {
                        map.put(from, to);
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}