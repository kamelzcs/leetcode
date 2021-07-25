package atcoder.abc190;

import common.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        int result = 0;
        for (long i = 1; i * i <= n; i++) {
            if ((n % i) == 0) {
                if (i % 2 == 1) {
                    result++;
                }
                long other = n / i;
                if (other == i) {
                    continue;
                }
                if ((other % 2) == 1) {
                    result++;
                }
            }
        }
        out.println(result * 2);
    }
}
