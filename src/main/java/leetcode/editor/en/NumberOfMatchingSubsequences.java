//Given string S and a dictionary of words words, find the number of words[i] th
//at is a subsequence of S. 
//
// 
//Example :
//Input: 
//S = "abcde"
//words = ["a", "bb", "acd", "ace"]
//Output: 3
//Explanation: There are three words in words that are a subsequence of S: "a", 
//"acd", "ace".
// 
//
// Note: 
//
// 
// All words in words and S will only consists of lowercase letters. 
// The length of S will be in the range of [1, 50000]. 
// The length of words will be in the range of [1, 5000]. 
// The length of words[i] will be in the range of [1, 50]. 
// 
// Related Topics Array


package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberOfMatchingSubsequences {
    public static void main(String[] args) {
        Solution solution = new NumberOfMatchingSubsequences().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<Character, List<Integer>> posMap = new HashMap<>();

        public int numMatchingSubseq(String S, String[] words) {
            for (int i = 0; i < S.length(); i++) {
                posMap.computeIfAbsent(S.charAt(i), x -> new ArrayList<>()).add(i);
            }

            return (int) Arrays.stream(words).filter(word -> match(word)).count();
        }

        private boolean match(String word) {
            Map<Character, Iterator<Integer>> posIterator = posMap.entrySet()
                    .stream().collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue().iterator()));

            int currentIndex = -1;
            for (char c : word.toCharArray()) {
                boolean found = false;
                if (!posIterator.containsKey(c) || !posIterator.get(c).hasNext()) {
                    return false;
                }

                Iterator<Integer> pos = posIterator.get(c);
                while (pos.hasNext()) {
                    int candidate = pos.next();
                    if (candidate > currentIndex) {
                        currentIndex = candidate;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}