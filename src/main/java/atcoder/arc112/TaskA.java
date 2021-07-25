package atcoder.arc112;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    static long[] sum = new long[1000_002];
    static {
        for (int i = 0; i < sum.length; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + (i + 1);
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int l = in.nextInt();
        int r = in.nextInt();
        if (r < 2 * l) {
            out.println(0);
        } else {
            out.println(sum[r - 2 * l]);
        }
    }
}
