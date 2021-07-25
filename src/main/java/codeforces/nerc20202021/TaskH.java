package codeforces.nerc20202021;

import common.InputReader;
import java.io.PrintWriter;

public class TaskH {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        int[] data = new int[m];
        for (int i = 0; i < m; i++) {
            data[i] = in.nextInt();
        }
        int s = (k - 1) / 2;
        int toDelete = n - m;
        if ((toDelete % (2 * s)) != 0) {
            out.println("NO");
        } else {
            boolean valid = false;
            for (int i = 0; i < m; i++) {
                int left = data[i] - 1 - i;
                int right = n - data[i] - (m - 1 - i);
//                System.out.println(i + " " + left + " " + right);
                if (left >= s && right >= s) {
                    valid = true;
                    out.println("YES");
                    break;
                }
            }
            if (!valid) {
                out.println("NO");
            }
        }
    }
}
