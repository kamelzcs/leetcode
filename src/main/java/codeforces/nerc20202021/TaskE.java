package codeforces.nerc20202021;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[] l = new int[4];
        for (int i = 0; i < l.length; i++) {
            l[i] = in.nextInt();
        }
        Arrays.sort(l);
        out.println(Math.min(l[0], l[1]) * Math.min(l[2], l[3]));
    }
}
