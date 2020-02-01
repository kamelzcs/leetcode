//Given an encoded string, return its decoded string.
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the 
//square brackets is being repeated exactly k times. Note that k is guaranteed to 
//be a positive integer. 
//
// You may assume that the input string is always valid; No extra white spaces, 
//square brackets are well-formed, etc. 
//
// Furthermore, you may assume that the original data does not contain any digit
//s and that digits are only for those repeat numbers, k. For example, there won't
// be input like 3a or 2[4]. 
//
// Examples: 
//
// 
//s = "3[a]2[bc]", return "aaabcbc".
//s = "3[a2[c]]", return "accaccacc".
//s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
// 
//
// 
// Related Topics Stack Depth-first Search


package leetcode.editor.en;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecodeString {
    public static void main(String[] args) {
        Solution solution = new DecodeString().new Solution();
//        System.out.println(solution.decodeString("3[a]2[bc]"));
        System.out.println(solution.decodeString("2[abc]3[cd]ef"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int index;
        String s;
        Matcher NUMBER;
        Matcher WORD;
        public String decodeString(String s) {
            this.s = s;
            NUMBER = Pattern.compile("^\\d+").matcher(s);
            WORD = Pattern.compile("^[a-zA-Z]+").matcher(s);
            return decode();
        }

        private String decode() {
            StringBuilder stringBuilder = new StringBuilder();
            while (index < s.length()) {
                Optional<String> parsed = parseEncoded();
                if (!parsed.isPresent()) {
                    break;
                }
                stringBuilder.append(parsed.get());
            }
            return stringBuilder.toString();
        }

        private Optional<String> parseEncoded() {
            if (NUMBER.region(index, NUMBER.regionEnd()).find()) {
                int count = Integer.parseInt(s.substring(index, NUMBER.end()));
                index = NUMBER.end();
                assert s.charAt(index) == '[';
                index++;
                String decoded = decode();
                assert s.charAt(index) == ']';
                index++;
                return Optional.of(occurs(decoded, count));
            } else if (WORD.region(index, WORD.regionEnd()).find()) {
                String candidate = s.substring(index, WORD.end());
                index = WORD.end();
                return Optional.of(candidate);
            } else {
                return Optional.empty();
            }
        }

        private String occurs(String decoded, int count) {
            return String.join("", Collections.nCopies(count, decoded));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}