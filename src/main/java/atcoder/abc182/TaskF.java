package atcoder.abc182;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long x = in.nextLong();
        long[] data = new long[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextLong();
        }
        long[] carry = new long[n];
        for (int i = 0; i < n - 1; i++) {
            carry[i] = data[i + 1] / data[i];
        }
        carry[n - 1] = (long) 1e18;
        long[] num = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            num[i] = x / data[i];
            x %= data[i];
        }
        long[][] dp = new long[n + 1][2];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int c = 0; c < 2; c++) {
                for (int nc = 0; nc < 2; nc++) {
                    // y = 0
                    // num[i] + c + y = z + nc * carry[i]
                    // z = num[i] + c  + 0 - nc * carry[i]
                    long z = num[i] + c + 0 - nc * carry[i];
                    if (z >= 0 && z < carry[i]) {
                        dp[i + 1][nc] += dp[i][c];
                    }

                    // z = 0
                    // y = z + nc * carry[i] - c - num[i]
                    long y = 0 + nc * carry[i] - c - num[i];
                    if (y > 0 && y < carry[i]) {
                        dp[i + 1][nc] += dp[i][c];
                    }
                }
            }
        }
        out.println(dp[n][0]);
    }
}
