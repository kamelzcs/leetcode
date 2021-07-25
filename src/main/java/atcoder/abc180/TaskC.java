package atcoder.abc180;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        Deque<Long> after = new ArrayDeque<>();
        for (long i = 1; i * i <= n; i++) {
            if ((n % i == 0)) {
                out.println(i);
                if (n / i != i) {
                    after.addFirst(n / i);
                }
            }
        }
        for (long d : after) {
            out.println(d);
        }
    }
}
