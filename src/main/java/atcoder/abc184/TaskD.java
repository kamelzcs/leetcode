package atcoder.abc184;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD {
    double[][][] dp = new double[100][100][100];
    double INF = -1;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        for (double[][] twoD : dp) {
            for (double[] d : twoD) {
                Arrays.fill(d, INF);
            }
        }
        out.println(func(a, b, c));
    }

    private double func(int a, int b, int c) {
        if (a >= 100 || b >= 100 || c >= 100) {
            return 0.0;
        }
        if (dp[a][b][c] != INF) {
            return dp[a][b][c];
        }
        dp[a][b][c] = 1;
        double sum = a + b + c;
        dp[a][b][c] += (a / sum) * func(a + 1, b, c);
        dp[a][b][c] += (b / sum) * func(a, b + 1, c);
        dp[a][b][c] += (c / sum) * func(a, b, c + 1);
        return dp[a][b][c];
    }
}
