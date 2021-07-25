package atcoder.abc182;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        out.println(Math.max(0, 2 * a + 100 - b));
    }
}
