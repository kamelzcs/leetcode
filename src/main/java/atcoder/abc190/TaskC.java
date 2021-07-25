package atcoder.abc190;

import common.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] cond = new int[m][2];
        for (int i = 0; i < m; i++) {
            cond[i][0] = in.nextInt();
            cond[i][1] = in.nextInt();
        }
        int k = in.nextInt();
        int[][] choose = new int[k][2];
        for (int i = 0; i < k; i++) {
            choose[i][0] = in.nextInt();
            choose[i][1] = in.nextInt();
        }

        int result = 0;
        for (int i = 0; i < (1<<k); i++) {
            boolean[] decided = new boolean[n + 1];
            for (int j = 0; j < k; j++) {
                if ((i & (1<<j)) > 0) {
                    decided[choose[j][0]] = true;
                } else {
                    decided[choose[j][1]] = true;
                }
            }

            int part = 0;
            for (int c = 0; c < m; c++) {
                if (decided[cond[c][0]] && decided[cond[c][1]]) {
                    part++;
                }
            }
            result = Math.max(result, part);
        }
        out.println(result);
    }
}
