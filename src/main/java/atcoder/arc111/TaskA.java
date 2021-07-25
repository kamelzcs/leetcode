package atcoder.arc111;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        long m = in.nextLong();
        if (m == 1) {
            out.println(0);
        } else {
            out.println(f(n, m)[0]);
        }
    }

    private long[] f(long n, long m) {
        if (n == 1) {
            return new long[]{(10 / m) % m, 10 % m};
        } else {
            long[] part = f(n / 2, m);
            long b = part[0];
            long c = part[1];
            long mul = 1;
            if ((n % 2) == 1) {
                mul *= 10;
            }
            return new long[]{(mul * 2 * b * c + mul * c * c / m) % m, (c * c) * mul % m};
        }
    }
}
