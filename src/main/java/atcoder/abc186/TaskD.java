package atcoder.abc186;

import common.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] data = new long[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextLong();
        }

        Arrays.sort(data);
        long result = 0;
        long[] sumLeft = new long[n];
        long[] sumRight = new long[n];
        for (int i = 0; i < n; i++) {
            sumLeft[i] = (i == 0 ? 0 : sumLeft[i - 1]) + data[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            sumRight[i] = (i == n - 1 ? 0 : sumRight[i + 1]) + data[i];
        }
        for (int i = 0; i < n; i++) {
            long left = i;
            long right = n - 1 - i;
            result += left * data[i] -  (i == 0 ? 0 : sumLeft[i - 1]);
            result += (i == n - 1 ? 0 : sumRight[i + 1]) - right * data[i];
        }
        out.println(result / 2);
    }
}
