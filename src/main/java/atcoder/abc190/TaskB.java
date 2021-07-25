package atcoder.abc190;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int s = in.nextInt();
        int d = in.nextInt();
        boolean valid = false;
        for (int  i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (x < s && y > d) {
                valid = true;
            }
        }
        out.println(valid ? "Yes" : "No");
    }
}
