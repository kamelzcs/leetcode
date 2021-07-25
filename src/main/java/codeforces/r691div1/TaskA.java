package codeforces.r691div1;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[] a = new long[n];
        long[] b = new long[m];
//        Set<Long> numbers = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
//        int id = 0;
//        for (long v : numbers) {
//            a[id++] = v;
//        }
        for (int i = 0; i < m; i++) {
            b[i] = in.nextLong();
        }

//        n = numbers.size();
        if (n == 1) {
            for (int j = 0; j < m; j++) {
                out.print(a[0] + b[j] + " ");
            }
            out.println();
        } else {
            long g = 0;
            for (int i = 1; i < n; i++) {
                g = gcd(g, Math.abs(a[i] - a[0]));
            }

            for (int j = 0; j < m; j++) {
                out.print(gcd(g, a[0] + b[j]) + " ");
            }
        }
    }

    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        return gcd(b, a % b);
    }
}
