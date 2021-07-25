package atcoder.abc186;

import common.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskF {
    class BIT {
        int n;
        int[] sum;
        BIT (int n) {
            this.n = n;
            this.sum = new int[n];
        }

        int query(int x) {
            int total = 0;
            while (x > 0) {
                total += sum[x];
                x -= lowBit(x);
            }
            return total;
        }

        void update(int x, int d) {
            while (x < n) {
                sum[x] += d;
                x += lowBit(x);
            }
        }

        int lowBit(int x) {
            return x & (-x);
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int rows = in.nextInt();
        int cols = in.nextInt();
        BIT bit = new BIT(cols + 1);
        int m = in.nextInt();
        List<Integer>[] data = new List[rows + 1];
        for (int i = 1; i <= rows; i++) {
            data[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            data[x].add(y);
        }
        for (int i = 1; i <= rows; i++) {
            data[i].sort(Comparator.naturalOrder());
        }

        int leftZero = data[1].isEmpty() ? cols + 1 : data[1].get(0);
        int upZero = -1;
        long result = 0;
        boolean[] visited = new boolean[cols + 1];
        for (int row = 1; row <= rows; row++) {
            for (int col : data[row]) {
                if (!visited[col]) {
                    bit.update(col, 1);
                    visited[col] = true;
                }
            }
            int colCount = leftZero - 1 - bit.query(leftZero - 1);
            int rowCount = 0;
            if (upZero < 0) {
                if (data[row].isEmpty()) {
                    rowCount = cols;
                } else {
                    int first = data[row].get(0);
                    rowCount =  first - 1;
                    if (first == 1) {
                        upZero = row;
                    }
                }
            }
            int both = 0;
            if (colCount > 0 && rowCount > 0) {
                int left = Math.min(data[row].isEmpty() ? cols + 1 : data[row].get(0), leftZero);
                both = left - 1 - bit.query(left - 1);
            }
            result += (rowCount + colCount - both);
        }
        out.println(result);
    }
}
