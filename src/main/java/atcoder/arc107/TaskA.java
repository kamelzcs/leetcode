package atcoder.arc107;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    long MOD = 998244353;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long value = (((f(a) * f(b)) % MOD) * f(c)) % MOD;
        out.println(value);
    }

    private long f(long a) {
        return (((1 + a) * a) / 2) % MOD;
    }
}
