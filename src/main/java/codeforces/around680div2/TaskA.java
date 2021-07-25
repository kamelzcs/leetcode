package codeforces.around680div2;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = 0; i < n; i++) {
            int pair = n - 1 - i;
            if (a[i] + b[pair] > x) {
                out.println("No");
                return;
            }
        }
        out.println("Yes");
    }
}
