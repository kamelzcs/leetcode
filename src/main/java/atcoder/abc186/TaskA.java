package atcoder.abc186;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    class Data {
        long a;
        long b;
        long g;

        public Data(long a, long b, long g) {
            this.a = a;
            this.b = b;
            this.g = g;
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int s = in.nextInt();
        int k = in.nextInt();

        Data data = gcd(k, n);
        if ((s % data.g) > 0) {
            out.println(-1);
        } else {
            long nDivide = n / data.g;
            out.println((((data.a * -1L *  (s/ data.g)) % nDivide) + nDivide) % nDivide);
        }
    }

    private Data gcd(long x, long y) {
        if (y == 0) {
            return new Data(1, 0, x);
        } else {
            Data part = gcd(y, x % y);
            return new Data(part.b, part.a - part.b * (x / y), part.g);
        }
    }
}
