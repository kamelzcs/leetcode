package codeforces.nerc20202021;

import common.InputReader;
import java.io.PrintWriter;

public class TaskN {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[] c = new int[3];
        int[] a = new int[5];
        for (int i = 0; i < c.length; i++) {
            c[i] = in.nextInt();
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }

        boolean valid = true;
        for (int i = 0; i < c.length; i++) {
            c[i] -= a[i];
            if (c[i] < 0) {
                valid = false;
                out.println("NO");
                break;
            }
        }

        a[3] -= Math.min(a[3], c[0]);
        a[4] -= Math.min(a[4], c[1]);

        if (valid) {
            if (a[3] + a[4] <= c[2]) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
