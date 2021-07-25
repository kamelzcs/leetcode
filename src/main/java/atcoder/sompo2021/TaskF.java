package atcoder.sompo2021;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long x = in.nextLong();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        long INF = Long.MAX_VALUE / 2;
        long result = INF;
//        long[][][] dp = new long[n + 1][n + 1][n];
        long[][] dp = new long[n + 1][n];
        for (int k = 1; k <= n; k++) {
//            for (long[][] dd: dp) {
                for (long[] d : dp) {
                    Arrays.fill(d, -INF);
                }
//            }
            dp[0][0] = 0;
            for (int i = 0; i < n; i++) {
                for (int j = k - 1; j >= 0; j--) {
                    for (int c = 0; c < k; c++) {
                        if (dp[j][c] == -INF) {
                            continue;
                        }
//                        dp[i + 1][j][c] = Math.max(dp[i][j][c], dp[i + 1][j][c]);
                        int nextC = (int) ((c + a[i]) % k);
                        long nextV = dp[j][c] + a[i];
//                        if (nextV <= x) {
                            dp[j + 1][nextC] = Math.max(dp[j + 1][nextC], nextV);
//                        }
                        int remain = (int) (x % k);
                        if (dp[k][remain] != -INF) {
                            result = Math.min(result, (x - dp[k][remain]) / k);
                        }
                    }
                }
            }
        }
        out.println(result);
    }
}
