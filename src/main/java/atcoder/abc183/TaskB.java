package atcoder.abc183;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long sx = in.nextLong();
        long sy = in.nextLong();
        long gx = in.nextLong();
        long gy = in.nextLong();
        out.println(1.0 * (gy * sx + sy * gx) / (sy + gy));
    }
}
