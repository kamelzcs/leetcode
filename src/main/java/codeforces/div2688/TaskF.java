package codeforces.div2688;

import common.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }

        int[][] dp = new int[n + 1][n + 1];
        int[][] cost = new int[n + 1][n + 1];
        int[][] rangeMin = new int[n + 1][n + 1];
        int INF = n + 1;
        for (int[] d : dp) {
            Arrays.fill(d, INF);
        }
        for (int[] d : rangeMin) {
            Arrays.fill(d, INF);
        }
        for (int i = 0; i < n; i++) {
            int toDelete = 0;
            for (int j = i - 1; j >= 0; j--) {
                cost[j][i] = toDelete;
                if (data[j] + j >= i) {
                    toDelete++;
                }
            }
        }

        for (int j = 1; j <= data[0]; j++) {
            dp[0][j] = cost[0][j];
            rangeMin[j][data[0]] = Math.min(rangeMin[j][data[0]], dp[0][j]);
        }
        for (int j = 1; j < n; j++) {
            int min = INF;
            for (int k = j; k < Math.min(n, data[j] + j + 1); k++) {
                dp[j][k] = min + cost[j][k];
                min = Math.min(min, rangeMin[j][k]);
                rangeMin[k][data[j] + j] = Math.min(rangeMin[k][data[j] + j], dp[j][k]);
            }
        }

//            for (int i = 0; i < n; i++) {
//                for (int j = i + 1; j < n; j++) {
//                    if (dp[i][j] == INF) {
//                        continue;
//                    }
//                    if (data[j] + j < data[i] + i) {
//                        continue;
//                    }
//
//                    for (int k = Math.max(j + 1, data[i] + i + 1); k <= data[j] + j; k++) {
//                        dp[j][k] = Math.min(dp[j][k], dp[i][j] + cost[j][k]);
//                    }
//                }
//            }

        int result = INF;
        for (int i = 0; i < n - 1; i++) {
//                for (int j = i + 1; j < n - 1; j++) {
//                    result = Math.min(result, dp[i][j] + cost[j][n - 1]);
//                }
            result = Math.min(result, dp[i][n - 1]);
        }
        out.println(result);
    }
}
