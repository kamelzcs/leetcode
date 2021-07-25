package atcoder.arc109;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        long l = 1;
        long r = n + 1;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (mid == n) {
                r = mid;
            } else {
                if (n - mid + 1 <= 2L * mid / (n - mid)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
        }
        out.println(r);
    }
}
