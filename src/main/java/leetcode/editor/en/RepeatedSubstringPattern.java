//Given a non-empty string check if it can be constructed by taking a substring
//of it and appending multiple copies of the substring together. You may assume th
//e given string consists of lowercase English letters only and its length will no
//t exceed 10000. 
//
// 
//
// Example 1: 
//
// 
//Input: "abab"
//Output: True
//Explanation: It's the substring "ab" twice.
// 
//
// Example 2: 
//
// 
//Input: "aba"
//Output: False
// 
//
// Example 3: 
//
// 
//Input: "abcabcabcabc"
//Output: True
//Explanation: It's the substring "abc" four times. (And the substring "abcabc" 
//twice.)
// 
// Related Topics String


package leetcode.editor.en;

import java.util.Arrays;

public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        Solution solution = new RepeatedSubstringPattern().new Solution();
        solution.repeatedSubstringPattern("aaaaa");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] dp;
        public boolean repeatedSubstringPattern(String s) {
            dp = new int[s.length()];
            dp[0] = 0;
            int lastLen = 0;
            for (int i = 1; i < s.length(); i++) {
                while (lastLen > 0 && s.charAt(i) != s.charAt(lastLen)) {
                    lastLen = dp[lastLen - 1];
                }
                if (s.charAt(i) == s.charAt(lastLen)) {
                    lastLen++;
                    dp[i] = lastLen;
                }
            }
            System.out.println(Arrays.toString(dp));
            return dp[s.length() - 1] > 0 && (s.length() % (s.length() - dp[s.length() - 1]) == 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}