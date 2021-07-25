package atcoder;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long total = 0;
        for (int i = 0; i < n; i++) {
            long a = in.nextLong();
            long b = in.nextLong();
            total += (a + b) * (b - a + 1) / 2;
        }
        out.println(total);
    }
}
