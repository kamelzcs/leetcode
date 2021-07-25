package atcoder.abc181;

import common.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
        }
        Arrays.sort(h);
        long[] diff = new long[n + 1];
        for (int i = n - 2; i >= 0; i -= 2) {
            int next = i + 1;
            int cur = h[next] - h[i];
            diff[i] = (diff[i + 2] + cur);
        }

        long[] diff2 = new long[n + 1];
        for (int i = 0; i + 1 < n; i += 2) {
            int next = i + 1;
            int cur = h[next] - h[i];
            long pre = i - 2 >= 0 ? diff2[i - 2] : 0;
            diff2[i] = (pre + cur);
        }
        int[] w = new int[m];
        for (int i = 0; i < m; i++) {
            w[i] = in.nextInt();
        }
        Arrays.sort(w);

        long result = Long.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int insertIndex = Arrays.binarySearch(h, w[i]);
            if (insertIndex < 0) {
                insertIndex = -insertIndex - 1;
            }
            if (insertIndex % 2 == 1) {
                insertIndex--;
            }
            long preDiff = insertIndex - 2 >= 0? diff2[insertIndex - 2] : 0;
            long afterDiff = insertIndex + 1 < n ? diff[insertIndex + 1] : 0;
            result = Math.min(result, preDiff + Math.abs(w[i] - h[insertIndex]) + afterDiff);
        }
        out.println(result);
    }
}
