package atcoder.abc185;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE {
    int n;
    int m;
    int[] a;
    int[] b;
    int[][] dp;
    int INF = Integer.MAX_VALUE / 10;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        a = new int[n + 1];
        b = new int[m + 1];
        dp = new int[n + 1][m + 1];
        for(int[] d : dp) {
            Arrays.fill(d, INF);
        }
        for (int i = 0; i < n; i++) {
            a[i + 1] = in.nextInt();
        }

        for (int i = 0; i < m; i++) {
            b[i + 1] = in.nextInt();
        }
        out.println(func(n, m));
    }

    private int func(int n, int m) {
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }
        int result = dp[n][m];
        if (result != INF) {
            return result;
        }

        result = n + m;
        if (a[n] == b[m]) {
            result = func(n - 1, m - 1);
        }
        result = Math.min(result, func(n - 1, m) + 1);
        result = Math.min(result, func(n, m - 1) + 1);
        result = Math.min(result, func(n - 1, m - 1) + 1);
        dp[n][m] =result;
        return result;
    }
}
