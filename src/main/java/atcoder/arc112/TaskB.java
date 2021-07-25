package atcoder.arc112;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long b = in.nextLong();
        long c = in.nextLong();
        if (c == 1) {
            if (b == 0) {
                out.println(1);
            } else {
                out.println(2);
            }
            return;
        }

        long x = b + (c - 2) / 2;
        long y = b - c / 2;

        long p = -b + (c - 1) / 2;
        long q = -b - (c - 1) / 2;

//        System.out.println(String.format("%d %d %d %d", x, y, p, q));
            if (p < y || q > x) {
                out.println(x - y + 1 + p - q + 1);
            } else {
                out.println(Math.max(p, x) - Math.min(q, y) + 1);
            }
    }
}
