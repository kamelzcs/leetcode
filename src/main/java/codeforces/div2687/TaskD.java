package codeforces.div2687;

import common.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] data = new int[n];
        if (n >= 70) {
            out.println(1);
        } else {
            for (int i = 0; i < n; i++) {
                data[i] = in.nextInt();
            }
//            int[] xor = new int[n];
//            for (int i = 0; i < n; i++) {
//                xor[i] = (i == 0) ? data[i] : xor[i - 1] ^ data[i];
//            }
            int result = n + 1;
            for (int l = 0; l < n - 1; l++) {
                int r = l + 1;
                int lValue = 0;
                for (int i = l; i >= 0; i--) {
                    lValue ^= data[i];
                    int rValue = 0;
                    for (int j = r; j < n; j++) {
                        rValue ^= data[j];
                        if (rValue < lValue) {
                            result = Math.min(result, j - r + l - i);
                        }
                    }
                }
            }
            out.println(result > n ? -1 : result);
        }
    }
}
