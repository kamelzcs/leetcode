package atcoder.arc107;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD {
    long[][] dp;
    int n;
    int k;
    long MOD = 998244353;
    long INIT = -1;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        k = in.nextInt();
        dp = new long[n + 1][n + 1];
        for (long[] data : dp) {
            Arrays.fill(data, INIT);
        }
        out.println(f(n, k));
    }

    private long f(int n, int k) {
        if (n < k) {
            return 0L;
        }
        if (n == k) {
            return 1L;
        }
        if (k == 0) {
            return 0;
        }
        if (dp[n][k] != INIT) {
            return dp[n][k];
        }
        long result = 0;

        result = f(n - 1, k - 1);
        result = (result + f(n, k << 1)) % MOD;

        dp[n][k] = result;
        return result;
    }
}
