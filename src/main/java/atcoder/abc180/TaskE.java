package atcoder.abc180;

import common.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[][] data = new int[n][3];
        for (int i = 0; i < n; i++) {
            data[i][0] = in.nextInt();
            data[i][1] = in.nextInt();
            data[i][2] = in.nextInt();
        }
        int[][] dp = new int[(1<<n)][n];
        int INF = Integer.MAX_VALUE / 10;
        for (int[] d : dp) {
            Arrays.fill(d, INF);
        }
        dp[1][0] = 0;
        for (int state = 1; state < (1<<n); state++) {
            for (int pos = 0; pos < n; pos++) {
                if (dp[state][pos] == INF) {
                    continue;
                }

                for (int nextPos = 0; nextPos <n; nextPos++) {
                    if ((state & (1<<nextPos)) > 0) {
                        continue;
                    }

                    int cost = Math.abs(data[pos][0] - data[nextPos][0]) + Math.abs(data[pos][1] - data[nextPos][1]) + Math.max(0, data[nextPos][2] - data[pos][2]);
                    int nextState = state | (1<<nextPos);
                    dp[nextState][nextPos] = Math.min(dp[nextState][nextPos], dp[state][pos] + cost);
                }
            }
        }
        int result = INF;
        int state = (1<<n) - 1;
            for (int pos = 0; pos < n; pos++) {
                if (dp[state][pos] < INF) {
                    if (state == 1) {
                        continue;
                    }
                    int cost = Math.abs(data[pos][0] - data[0][0]) + Math.abs(data[pos][1] - data[0][1]) + Math.max(0,  - data[pos][2] + data[0][2]);
                    result = Math.min(result, dp[state][pos] + cost);
                }
            }
        out.println(result);
    }
}
