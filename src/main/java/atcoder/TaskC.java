package atcoder;

import common.InputReader;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            data[i][0] = x;
            data[i][1] = y;
        }
        boolean exist = false;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (linear(data[i][0], data[i][1], data[j][0], data[j][1], data[k][0], data[k][1])) {
                        exist = true;
                    }
                }
            }
        }
        out.println(exist ? "Yes" : "No");
    }
    boolean linear(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (y1 - y2) * (x1 - x3) == (y1 - y3) * (x1 - x2);
    }
}
