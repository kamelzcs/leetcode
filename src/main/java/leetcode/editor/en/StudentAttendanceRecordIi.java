//Given a positive integer n, return the number of all possible attendance recor
//ds with length n, which will be regarded as rewardable. The answer may be very l
//arge, return it after mod 109 + 7. 
//
// A student attendance record is a string that only contains the following thre
//e characters: 
//
// 
// 
// 'A' : Absent. 
// 'L' : Late. 
// 'P' : Present. 
// 
// 
//
// 
//A record is regarded as rewardable if it doesn't contain more than one 'A' (ab
//sent) or more than two continuous 'L' (late). 
//
// Example 1: 
// 
//Input: n = 2
//Output: 8 
//Explanation:
//There are 8 records with length 2 will be regarded as rewardable:
//"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//Only "AA" won't be regarded as rewardable owing to more than one absent times.
// 
// 
// 
//
// Note:
//The value of n won't exceed 100,000.
// 
//
//
// Related Topics Dynamic Programming


package leetcode.editor.en;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.Arrays;

public class StudentAttendanceRecordIi {
    public static void main(String[] args) {
        Solution solution = new StudentAttendanceRecordIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int MOD =  1000000000 + 7;
        public int checkRecord(int n) {
            int[][][] dp = new int[2][2][3];
            dp[1][0][0] = 1;
            dp[1][1][0] = 1;
            dp[1][0][1] = 1;

            for (int i = 2; i <= n; i++) {
                int current = i % 2;
                int prev = 1 - current;
                clear(dp[current]);

                for (int m = 0; m <= 1; m++) {
                    for (int c = 0; c <= 2; c++) {
                        if (dp[prev][m][c] == 0) {
                            continue;
                        }
                        dp[current][m][0] = calc(dp[current][m][0], dp[prev][m][c]);
                        if (m == 0) {
                            dp[current][1][0] = calc(dp[current][1][0], dp[prev][m][c]);
                        }

                        if (c < 2) {
                            dp[current][m][c + 1] = calc(dp[current][m][c + 1], dp[prev][m][c]);
                        }
                    }
                }
            }

            int result = 0;
            int index = n % 2;
            for (int[] arr: dp[index]) {
                result = calc(result, Arrays.stream(arr).reduce((x, y) -> ((x + y) % MOD)).orElse(0));
            }
            return result;
        }

        private int calc(int i, int i1) {
            return (i + i1) % MOD;
        }

        private void clear(int[][] ints) {
            for (int[] arr : ints) {
                Arrays.fill(arr, 0);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}