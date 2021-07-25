package codeforces.r691div1;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] capacity = new int[n];
        int[] amount = new int[n];
        int totalAmount = 0;
        int maxCapacity = 0;
        for (int i = 0; i < n; i++) {
            capacity[i] = in.nextInt();
            amount[i] = in.nextInt();
            maxCapacity = Math.max(maxCapacity, capacity[i]);
            totalAmount += amount[i];
        }
        int sum = maxCapacity * n;
        int[][] dp = new int[n + 1][sum + 1];
        int INIT = Integer.MIN_VALUE / 10;
        for(int[] d : dp) {
            Arrays.fill(d, INIT);
        }
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                for (int s = sum - capacity[i]; s >= 0; s--) {
                    if (dp[j][s] == INIT) {
                        continue;
                    }
                    int nextS = s + capacity[i];
                    int nextAmount = dp[j][s] + amount[i];
                    dp[j + 1][nextS] = Math.max(dp[j + 1][nextS], nextAmount);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int min = Integer.MIN_VALUE;
            for (int j = 1; j <= sum; j++) {
                min = Math.max(Math.min(totalAmount + dp[i][j], 2 * j), min);
            }
            out.print(String.format("%f ", min * 1.0 / 2));
        }
        out.println();
    }
}
