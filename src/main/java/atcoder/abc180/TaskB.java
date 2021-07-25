package atcoder.abc180;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] data = new long[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextLong();
        }
        out.println(man(data));
        out.println(euci(data));
        out.println(che(data));
    }

    private long che(long[] data) {
        return Arrays.stream(data)
                .map(Math::abs)
                .max().getAsLong();
    }

    private double euci(long[] data) {
        return Math.sqrt(
                Arrays.stream(data)
                .map(x -> x * x)
                .sum() * 1.0
        );
    }

    private long man(long[] data) {
        return Arrays.stream(data)
                .map(Math::abs)
                .sum();
    }
}
