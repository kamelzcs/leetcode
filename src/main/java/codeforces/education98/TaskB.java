package codeforces.education98;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long maxV = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            sum += v;
            maxV = Math.max(maxV, v);
        }
        out.println(Math.max(maxV * (n - 1), (sum + (n - 2)) / (n - 1) * (n - 1)) - sum);
    }
}
