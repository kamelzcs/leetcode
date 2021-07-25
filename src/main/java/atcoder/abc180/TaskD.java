package atcoder.abc180;

import common.InputReader;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long x = in.nextLong();
        long y = in.nextLong();
        long a = in.nextLong();
        long b = in.nextLong();

        long exp = 0;
        while (true) {
             long nextT = nextTimes(x, a);
             long nextP = nextPlus(x, b);
             if (nextT <= nextP) {
                 if (nextT >= y) {
                     break;
                 }
                 x = nextT;
                 exp++;
             } else {
                 exp += (y - 1 - x) / b;
                 break;
             }
        }
        out.println(exp);
    }

    private long nextTimes(long x, long a) {
        long currentMin = Long.MAX_VALUE;
        if (currentMin / a >= x) {
            currentMin = a * x;
        }
        return currentMin;
    }
    private long nextPlus(long x, long b) {
        return x + b;
    }
}
