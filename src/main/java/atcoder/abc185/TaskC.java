package atcoder.abc185;

import common.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int l = in.nextInt();
        long[][] c = new long[l + 1][l + 1];
        c[0][0] = 1;
        for (int i = 1; i <= l; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    c[i][j] = 1;
                } else {
                    c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
                }
            }
        }
        out.println(c[l - 1][11]);
    }

    private long combine(long n, int m) {
        if (m == 0) {
            return 1;
        }
        if (n == m) {
            return 1;
        }
        return combine(n - 1, m) + combine(n - 1, m - 1);
    }

}
