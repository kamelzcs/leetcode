package atcoder.abc187;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        out.println(Math.max(f(a), f(b)));
    }

    private int f(int v) {
        int result = 0;
        while (v > 0) {
            result += (v % 10);
            v /= 10;
        }
        return result;
    }
}
