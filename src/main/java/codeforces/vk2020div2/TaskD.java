package codeforces.vk2020div2;

import common.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] data = new int[n];
        int[] delta = new int[n + 2];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        if (n <= 2) {
            out.println("Yes");
            return;
        }
        delta[0] = data[0];
        for (int i = 1; i < n; i++) {
            delta[i] = data[i] - data[i - 1];
        }

        delta[n] = -data[n - 1];

        boolean valid = true;
        int toAdd = delta[0];
        for (int i = 1; i < n; i++) {
            if (delta[i] < 0) {
                toAdd -= Math.abs(delta[i]);
                if (toAdd < 0) {
                    valid = false;
                    break;
                }
            }
        }
        out.println(valid ? "YES" : "NO");
    }
}
