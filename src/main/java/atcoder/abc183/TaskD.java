package atcoder.abc183;

import common.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long w = in.nextLong();
        long[] sum = new long[200_010];
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            int t = in.nextInt();
            long p = in.nextLong();
            sum[s] += p;
            sum[t] -= p;
        }
        for (int i = 0; i < sum.length; i++) {
            sum[i] = (i > 0 ? sum[i - 1] : 0) + sum[i];
            if (sum[i] > w) {
                out.println("No");
                return;
            }
        }
        out.println("Yes");
    }
}
