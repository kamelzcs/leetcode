package codeforces.div2687;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        long m = in.nextLong();
        long r = in.nextLong();
        long c = in.nextLong();
        out.println(Math.max(r - 1, n - r) + Math.max(c - 1, m - c));
    }
}
