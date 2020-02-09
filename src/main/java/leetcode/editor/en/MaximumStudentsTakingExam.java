//Given a m * n matrix seats that represent seats distributions in a classroom.
//If a seat is broken, it is denoted by '#' character otherwise it is denoted by a
// '.' character. 
//
// Students can see the answers of those sitting next to the left, right, upper 
//left and upper right, but he cannot see the answers of the student sitting direc
//tly in front or behind him. Return the maximum number of students that can take 
//the exam together without any cheating being possible.. 
//
// Students must be placed in seats in good condition. 
//
// 
// Example 1: 
//
// 
//Input: seats = [["#",".","#","#",".","#"],
//                [".","#","#","#","#","."],
//                ["#",".","#","#",".","#"]]
//Output: 4
//Explanation: Teacher can place 4 students in available seats so they don't che
//at on the exam. 
// 
//
// Example 2: 
//
// 
//Input: seats = [[".","#"],
//                ["#","#"],
//                ["#","."],
//                ["#","#"],
//                [".","#"]]
//Output: 3
//Explanation: Place all students in available seats. 
//
// 
//
// Example 3: 
//
// 
//Input: seats = [["#",".",".",".","#"],
//                [".","#",".","#","."],
//                [".",".","#",".","."],
//                [".","#",".","#","."],
//                ["#",".",".",".","#"]]
//Output: 10
//Explanation: Place students in available seats in column 1, 3 and 5.
// 
//
// 
// Constraints: 
//
// 
// seats contains only characters '.' and'#'. 
// m == seats.length 
// n == seats[i].length 
// 1 <= m <= 8 
// 1 <= n <= 8 
// 
// Related Topics Dynamic Programming


package leetcode.editor.en;

import java.util.Arrays;

public class MaximumStudentsTakingExam {
    public static void main(String[] args) {
        Solution solution = new MaximumStudentsTakingExam().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] mask;
        int rows;
        int cols;
        public int maxStudents(char[][] seats) {
            rows = seats.length;
            cols = seats[0].length;
            mask = new int[rows + 1];
            for (int i = 0; i < rows; i++) {
                int bit = 0;
                for (int j = 0;j < cols; j++) {
                    if (seats[i][j] == '.') {
                        bit |= (1<<j);
                    }
                }
                mask[i] = bit;
            }

            int[][] dp = new int[2][1<<cols];

            int result = 0;
            for (int j = rows - 1; j >= 0; j--) {
                int next = j & 1;
                int prev = 1- next;
                Arrays.fill(dp[next], 0);
                for (int prevMask = 0; prevMask < (1 << cols); prevMask++) {
                    for (int currentMask = 0; currentMask < (1 << cols); currentMask++) {
                        if (valid(j, currentMask, prevMask)) {
                            dp[next][currentMask] = Math.max(dp[next][currentMask], dp[prev][prevMask] + Integer.bitCount(currentMask));
                            result = Math.max(result, dp[next][currentMask]);
                        }
                    }
                }
            }
            return result;
        }

        private boolean valid(int currentRow, int currentMask, int prevMask) {
            if ((mask[currentRow] & currentMask ) != currentMask) {
                return false;
            }

            for (int j = 0; j < cols - 1; j++) {
                if (isBitOne(currentMask, j) && isBitOne(currentMask, j + 1)) {
                    return false;
                }
            }

            for (int j = 0; j < cols; j++) {
                if (j >= 1) {
                    if (isBitOne(currentMask, j) && isBitOne(prevMask, j - 1)) {
                        return false;
                    }
                }
                if (j < cols - 1) {
                    if (isBitOne(currentMask, j) && isBitOne(prevMask, j + 1)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isBitOne(int currentMask, int j) {
            return (currentMask & (1 << j)) > 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}