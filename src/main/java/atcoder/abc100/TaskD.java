package atcoder.abc100;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] data = new long[n][3];
        for (int i = 0; i < n; i++) {
            data[i][0] = in.nextLong();
            data[i][1] = in.nextLong();
            data[i][2] = in.nextLong();
        }

        long result = 0;
        for (int mask = 0; mask < 1<<3 ; mask++) {
            List<Long> part = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                long value = 0;
                for (int i = 0; i < 3; i++) {
                    if ((mask & (1<<i)) > 0) {
                        value += data[j][i];
                    } else {
                        value -= data[j][i];
                    }
                }
                part.add(value);
            }
            part.sort(Comparator.reverseOrder());
            long candidate = 0;
            for (int i = 0; i < m; i++) {
                candidate += part.get(i);
            }
            result = Math.max(result, candidate);
        }
        out.println(result);
    }
}
