package atcoder.abc184;

import common.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long[] data = new long[4];
        for (int i = 0; i < data.length; i++) {
            data[i] = in.nextLong();
        }
        out.println(data[0] * data[3] - data[1] * data[2]);
    }
}
