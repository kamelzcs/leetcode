package atcoder.abc185;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int t = in.nextInt();

        int time = 0;
        int battery = n;
        for (int i = 0; i < m; i++) {
            int from  = in.nextInt();
            int to = in.nextInt();

            int delta = from - time;
            if (battery <= delta) {
                out.println("No");
                return;
            }
            battery -= delta;
            battery = Math.min(battery + to - from, n);
            time = to;
        }

        if (battery <= t - time) {
            out.println("No");
        } else {
            out.println("Yes");
        }
    }
}
