package atcoder.agc038;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt();
        }
        int result = 0;
        boolean different = false;
        int max = -1;
        for (int i = 0; i < k; i++) {
            if (i != p[i]) {
                different = true;
            }
            max = Math.max(max, p[i]);
        }

        for (int i = k; i < n; i++) {
        }
        out.println(result);
    }
}
