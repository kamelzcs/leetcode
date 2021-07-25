package atcoder.abc182;

import common.InputReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long largest = 0;
        long sum = 0;
        long pos = 0;
        long largestPartial = 0;
        for (int i = 0; i < n; i++) {
            long value = in.nextLong();
            sum += value;
            largestPartial = Math.max(largestPartial, sum);
            largest = Math.max(largest, pos + largestPartial);
            pos += sum;
        }
        out.println(largest);
    }
}
