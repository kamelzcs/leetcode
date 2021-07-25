package codeforces.education98;

import common.InputReader;
import java.io.PrintWriter;

public class TaskD {
    long MOD = 998244353L;
    class BIT {
        int size;
        long[] sum;
        BIT(int n) {
            size = n;
            sum = new long[n + 1];
        }

        int lowBit(int x) {
            return x & (-x);
        }

        void update(int id, long v) {
            while (id < size) {
                sum[id] += v;
                sum[id] %= MOD;

                id += lowBit(id);
            }
        }

        long query(int id) {
            long result = 0;
            while (id > 0) {
                result = (result + sum[id]) % MOD;
                id -= lowBit(id);
            }
            return result;
        }
    }
    BIT bit;
    long[] dp;
    long[] power;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        bit = new BIT(2 * n + 1);
        dp = new long[n + 1];
        power = new long[2 + 1];
        power[1] = getInverse();
        power[2] = (getInverse() * power[1]) % MOD;

//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= (i + 1) / 2; j++) {
//                dp[i] = (dp[i] + (power[2 * j - 1] * dp[i - 2 * j + 1])) % MOD;
//            }
//        }

        dp[0] = 0;
        dp[1] = power[1];
        for (int i = 1; i <= n - 1; i++) {
            dp[i + 1] = (power[1] * dp[i]) % MOD;
            dp[i + 1] = (dp[i + 1] + (power[2] * dp[i - 1])) % MOD;
        }
        out.println(dp[n]);
    }

    long w(int n, int j) {
        if (2 * j - 1 >= n) {
            return 0;
        }
        return (power[2 * j - 1] * dp[n - 2 * j + 1]) % MOD;
    }

    private long getInverse() {
        return pow(2, MOD - 2);
    }

    private long pow(int v, long exp) {
        if (exp == 0) {
            return 1;
        }

        long part = pow(v, exp / 2);
        long result = (part * part) % MOD;
        if ((exp % 2) == 1) {
            result = (result * v) % MOD;
        }
        return result;
    }

}
