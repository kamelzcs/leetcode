package atcoder.abc187;

import common.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dy = y[j] - y[i];
                int dx = x[j] - x[i];

                if (dx >= 0) {
                    if (dy >= -dx && dy <= dx) {
                        result++;
                    }
                } else {
                    if (dy >= dx && dy <= -dx) {
                        result++;
                    }
                }
            }
        }
        out.println(result);
    }
}
