package atcoder.agc017;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long d = in.nextLong();
        out.println(f(n, a, b, c, d) ? "YES" : "NO");
    }

    private boolean f(long n, long a, long b, long c, long d) {
        long diff = Math.abs(a - b);
        for (long i = 0; i <= n - 1; i++) {
            long positive = i;
            long negative = n - 1 - i;
            long l0 = c * positive;
            long r0 = d * positive;

            long l1 = c * negative;
            long r1 = d * negative;

            if (intersect(l0 - diff, r0 - diff, l1, r1)) {
                return true;
            }
        }
        return false;
    }

    private boolean intersect(long l0, long r0, long l1, long r1) {
        long left = Math.max(l0, l1);
        long right = Math.min(r0, r1);
        return left <= right;
    }
}
