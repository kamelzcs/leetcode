package atcoder.arc115;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = Integer.valueOf(in.next(), 2);
        }
        int[] count = new int[2];
        for (int i = 1; i < n; i++) {
            count[Integer.bitCount(v[i] ^ v[0]) & 1]++;
        }
        out.println((long)(count[0] + 1) * count[1]);
    }
}
