package atcoder.disco;

import common.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m = in.nextInt();
        long digits = 0;
        long sum = 0;
        long total = 0;
        for (int i = 0; i < m; i++) {
            long d = in.nextLong();
            long c = in.nextLong();

            total += d * c;
            digits += c;
        }
        out.println((total - 1) / 9 + digits - 1);
    }
}
