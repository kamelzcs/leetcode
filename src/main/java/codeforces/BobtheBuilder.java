package codeforces;

import java.util.Arrays;

public class BobtheBuilder {
    int[] dp = new int[10000001];
    int INF = 10000000;
    int INF2 = 100000000;
    int B;
    int K;
    int H;
    int f(int x) {
        if (x > dp.length) {
            return INF;
        }
        if (x == H) {
            return 0;
        }
        if (dp[x] == INF2) {
            return INF;
        }
        dp[x] = INF2;
        dp[x] = Math.min(dp[x], f(x + K));
        return -1;
    }
    public int minimumPrice(int B, int K, int H) {
        this.B = B;
        this.K = K;
        this.H = H;
        Arrays.fill(dp, INF);
        return f(B);
    }
}
