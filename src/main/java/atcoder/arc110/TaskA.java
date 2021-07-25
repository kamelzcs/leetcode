package atcoder.arc110;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long x = 1;
        for (int i =2; i <= n; i++) {
            x = lcm(x, i);
        }

        x++;
//        for (int i =2; i <= n; i++) {
//            System.out.println(i + " " + x % i + " ");
//        }
        out.println(x);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
