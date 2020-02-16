//Given two equal-size strings s and t. In one step you can choose any character
// of t and replace it with another character. 
//
// Return the minimum number of steps to make t an anagram of s. 
//
// An Anagram of a string is a string that contains the same characters with a d
//ifferent (or the same) ordering. 
//
// 
// Example 1: 
//
// 
//Input: s = "bab", t = "aba"
//Output: 1
//Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of 
//s.
// 
//
// Example 2: 
//
// 
//Input: s = "leetcode", t = "practice"
//Output: 5
//Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters 
//to make t anagram of s.
// 
//
// Example 3: 
//
// 
//Input: s = "anagram", t = "mangaar"
//Output: 0
//Explanation: "anagram" and "mangaar" are anagrams. 
// 
//
// Example 4: 
//
// 
//Input: s = "xxyyzz", t = "xxyyzz"
//Output: 0
// 
//
// Example 5: 
//
// 
//Input: s = "friend", t = "family"
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 50000 
// s.length == t.length 
// s and t contain lower-case English letters only. 
// 
// Related Topics String


package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumNumberOfStepsToMakeTwoStringsAnagram {
    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfStepsToMakeTwoStringsAnagram().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSteps(String s, String t) {
            return diff(compute(s), compute(t)) / 2;
        }

        private int diff(Map<Character, Integer> compute, Map<Character, Integer> compute1) {
            int result = 0;
            for (char c = 'a'; c <= 'z'; c++) {
                result += Math.abs(compute.getOrDefault(c, 0) - compute1.getOrDefault(c, 0));
            }
            return result;
        }

        private Map<Character, Integer> compute(String s) {
            Map<Character, Integer> result = new HashMap<>();
            for (char c : s.toCharArray()) {
                result.put(c, result.getOrDefault(c, 0) + 1);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}