package atcoder.arc107;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] count = new long[2 * n + 1];
        for (int i = 2; i <=n; i++) {
            count[i] = i - 1;
        }
        for (int i = n + 1; i <= 2 * n; i++) {
            count[i] = 2 * n + 1 - i;
        }

        long total = 0;
        for (int i = 2; i <= 2 * n; i++) {
            int other = i - k;
            if (other < 0 || other > 2 * n) {
                continue;
            }
            total += count[i] * count[other];
        }
        out.println(total);
    }
}
