package codeforces.div2685;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD {
//    int k = 1;
//    int d = 16;
//    int[][] dp = new int[d + 1][d + 1];
//    int INF = -1;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
//        for (int[] array : dp) {
//            Arrays.fill(array, INF);
//        }
//        func(0, 0);
//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        long d = in.nextLong();
        long k = in.nextLong();
         double EPS = 1e-7;
        for (long x = d / k * k; x >= 0; x -= k) {
            if (x * x * 2 > d * d) {
                continue;
            }
            long delta = 0;
            while ((x + delta * k) * (x + delta * k) + x * x <= d * d) {
                delta++;
            }
            out.println(delta % 2 == 1 ? "Utkarsh" : "Ashish");
            return;
        }
    }

//    private int func(int x, int y) {
//        if (x * x + y * y > d * d) {
//            return 1;
//        }
//        if (dp[x][y] != INF) {
//            return dp[x][y];
//        }
//
//        dp[x][y] = 1 ^ (func(x + k, y) & func(x, y + k));
//        return dp[x][y];
//    }
}
