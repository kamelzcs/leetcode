//Given a string s. Return all the words vertically in the same order in which t
//hey appear in s. 
//Words are returned as a list of strings, complete with spaces when is necessar
//y. (Trailing spaces are not allowed). 
//Each word would be put on only one column and that in one column there will be
// only one word. 
//
// 
// Example 1: 
//
// 
//Input: s = "HOW ARE YOU"
//Output: ["HAY","ORO","WEU"]
//Explanation: Each word is printed vertically. 
// "HAY"
// "ORO"
// "WEU"
// 
//
// Example 2: 
//
// 
//Input: s = "TO BE OR NOT TO BE"
//Output: ["TBONTB","OEROOE","   T"]
//Explanation: Trailing spaces is not allowed. 
//"TBONTB"
//"OEROOE"
//"   T"
// 
//
// Example 3: 
//
// 
//Input: s = "CONTEST IS COMING"
//Output: ["CIC","OSO","N M","T I","E N","S G","T"]
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 200 
// s contains only upper case English letters. 
// It's guaranteed that there is only one space between 2 words. 
// Related Topics String


package leetcode.editor.en;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrintWordsVertically {
    public static void main(String[] args) {
        Solution solution = new PrintWordsVertically().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> printVertically(String s) {
            final List<String> pieces = Arrays.asList(s.split(" "));
            if (pieces.isEmpty()) {
                return Collections.emptyList();
            }
            int largestLength = pieces.stream().map(str -> str.length()).max(Integer::compareTo).get();
            return IntStream.range(0, largestLength).mapToObj(index -> getString(pieces, index)).collect(Collectors.toList());
        }

        private String getString(List<String> pieces, int index) {
            return pieces.stream().map(piece -> {
                if (piece.length() > index) {
                    return piece.charAt(index) + "";
                } else {
                    return " ";
                }
            }).collect(Collectors.joining()).replaceAll("\\s+$", "");
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}