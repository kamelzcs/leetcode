package codeforces.around680div2;

import common.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long p = in.nextLong();
        long q = in.nextLong();
        if ((p % q) != 0) {
            out.println(p);
            return;
        }
        long result = Long.MIN_VALUE;
        long current = q;
        for (long i = 2; i * i <= q; i++) {
            if ((current % i) == 0) {
                result = Math.max(large(p, q, i), result);
                while ((current % i) == 0) {
                    current /= i;
                }
            }
        }
        if (current > 1) {
            result = Math.max(large(p, q, current), result);
        }
        out.println(result);
    }
    long large(long p, long q, long i) {
        int powerQ = 0;
        long numberQ = 1;
        while ((q % i) == 0) {
            powerQ++;
            q /= i;
            numberQ *= i;
        }

        long temp = p;
        while ((temp % i) == 0) {
            temp /= i;
        }
        return temp * numberQ / i;
    }
}
