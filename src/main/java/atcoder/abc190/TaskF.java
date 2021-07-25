package atcoder.abc190;

import common.InputReader;
import java.io.PrintWriter;

public class TaskF {
    class BIT {
        int[] sum;
        int n;
        BIT(int n) {
            this.n = n;
            this.sum = new int[n];
        }
        int query(int x) {
            int result = 0;
            while (x > 0) {
                result += sum[x];
                x -= lsb(x);
            }
            return result;
        }

        void update(int v, int d) {
            while (v < n) {
                sum[v] += d;
                v += lsb(v);
            }
        }

        int lsb(int x) {
            return x & (-x);
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] v = new int[n];
        BIT bit = new BIT(n + 1);
        long reversed = 0;
        for (int i = 0; i < n; i++) {
            v[i] = in.nextInt();
            int convert = n - v[i];
            reversed += bit.query(convert);
            bit.update(convert, 1);
        }
        out.println(reversed);
        for (int i = 0; i < n - 1; i++) {
            reversed += (n - 1 - 2L * v[i]);
            out.println(reversed);
        }
    }
}
