package atcoder.abc191;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        boolean isFirst = true;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if (v == x) {
                continue;
            }
            if (isFirst) {
                isFirst = false;
            } else {
                out.print(" ");
            }
            out.print(v);
        }
        out.println();
    }
}
