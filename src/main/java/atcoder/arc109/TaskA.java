package atcoder.arc109;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int ans = x;
        if (a == b) {
            out.println(ans);
        } else if (a > b) {
            out.println(ans + (a - b - 1) * Math.min(2 * x, y));
        } else {
            out.println(ans + (b - a) * Math.min(2 * x, y));
        }
    }
}
