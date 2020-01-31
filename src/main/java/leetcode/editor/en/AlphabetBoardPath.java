//On an alphabet board, we start at position (0, 0), corresponding to character
//board[0][0]. 
//
// Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in
// the diagram below. 
//
// 
//
// We may make the following moves: 
//
// 
// 'U' moves our position up one row, if the position exists on the board; 
// 'D' moves our position down one row, if the position exists on the board; 
// 'L' moves our position left one column, if the position exists on the board; 
//
// 'R' moves our position right one column, if the position exists on the board;
// 
// '!' adds the character board[r][c] at our current position (r, c) to the answ
//er. 
// 
//
// (Here, the only positions that exist on the board are positions with letters 
//on them.) 
//
// Return a sequence of moves that makes our answer equal to target in the minim
//um number of moves. You may return any path that does so. 
//
// 
// Example 1: 
// Input: target = "leet"
//Output: "DDR!UURRR!!DDD!"
// Example 2: 
// Input: target = "code"
//Output: "RR!DDRR!UUL!R!"
// 
// 
// Constraints: 
//
// 
// 1 <= target.length <= 100 
// target consists only of English lowercase letters. 
// Related Topics Hash Table String


package leetcode.editor.en;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class AlphabetBoardPath {
    public static void main(String[] args) {
        Solution solution = new AlphabetBoardPath().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class Pair {
            int left;
            int right;
            Pair(int left, int right) {
                this.left = left;
                this.right = right;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Pair)) {
                    return false;
                }
                Pair compare = (Pair) obj;
                return left == compare.left && right == compare.right;
            }
        }

        Pair getPos(char c) {
            int index = (c - 'a');
            int right = index / 5;
            int left = index % 5;
            return new Pair(left, right);
        }

        String getStr(Pair from, Pair to) {
            if (from.equals(to)) {
                return "";
            }
            Pair z = new Pair(0, 5);
            Pair nextZ = new Pair(0, 4);
            if (z.equals(from)) {
                return "U" + getStr(nextZ, to);
            } else if (z.equals(to)) {
                return getStr(from, nextZ) + "D";
            }
            String result = "";
            if (to.left > from.left) {
                result = generateStr("R", to.left - from.left) + result;
            } else if (to.left < from.left) {
                result = generateStr("L", from.left - to.left) + result;
            }

            if (to.right > from.right) {
                result = generateStr("D", to.right - from.right) + result;
            } else if (to.right < from.right) {
                result = generateStr("U", from.right - to.right) + result;
            }
            return result;
        }

        private String generateStr(String str, int occurs) {
            if (occurs == 0) {
                return str;
            }

            return String.join("", Collections.nCopies(occurs, str));
        }

        public String alphabetBoardPath(String target) {
            Pair currentPos = new Pair(0, 0);
            String result = "";
            for (char c : target.toCharArray()) {
                Pair nextPos = getPos(c);
                result += getStr(currentPos, nextPos) + "!";
                currentPos = nextPos;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}