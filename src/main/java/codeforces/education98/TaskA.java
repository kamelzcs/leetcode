package codeforces.education98;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int x = in.nextInt();
        int y = in.nextInt();
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        out.println(Math.max(2 * x - 1, 2 * y));
    }
}
