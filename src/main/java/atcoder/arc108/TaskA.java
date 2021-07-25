package atcoder.arc108;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long s = in.nextLong();
        long p = in.nextLong();
        if (4L * p / s > s) {
            out.println("No");
            return;
        } else {
            long l = 1;
            long r = s / 2;
            while (l <= r) {
                long mid = l + (r - l) / 2;
                if (mid > p / (s - mid)) {
                    r = mid - 1;
                } else if (mid < p / (s - mid)) {
                    l = mid + 1;
                } else {
                    long part = mid * (s - mid);
                    if (part == p) {
                        out.println("Yes");
                        return;
                    }
                    if (part > p) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
            }
            out.println("No");
        }
    }
}
