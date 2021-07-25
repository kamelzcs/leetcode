package atcoder.abc185;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        int m = in.nextInt();
        if (m == 0) {
            out.println(1);
            return;
        }
        long[] data = new long[m];
        for (int i = 0; i < m ; i++) {
            data[i] = in.nextLong();
        }
        Arrays.sort(data);
        List<Long> pieces = new ArrayList<>();
        long min = Long.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            if (i == 0) {
                if (data[i] > 1) {
                    min = data[i] - 1;
                    pieces.add(min);
                }
            } else {
                long d = data[i] - data[i - 1] - 1;
                if (d > 0) {
                    min = Math.min(min, d);
                    pieces.add(d);
                }
            }
        }
        if (data[m - 1] < n) {
            min = Math.min(min, n - data[m - 1]);
            pieces.add(n - data[m - 1]);
        }

        long finalMin = min;
        out.println(pieces.stream().map(x -> (x + finalMin - 1) / finalMin).reduce(Long::sum).orElse(0L));
    }

    private long func(long a, long b) {
//        if (b == 0) {
//            return a;
//        }
//        return func(b, a % b);
        return Math.min(a, b);
    }
}
