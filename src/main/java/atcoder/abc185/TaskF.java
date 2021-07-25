package atcoder.abc185;

import common.InputReader;
import java.io.PrintWriter;

public class TaskF {
    int[] data;
    class BIT {
        int n;
        int[] value;
        BIT(int n) {
            this.n = n;
            this.value = new int[n + 1];
        }

        void update(int id, int v) {
            while (id <= n) {
                value[id] ^= v;
                id += lb(id);
            }
        }

        int query(int x) {
            int result = 0;
            while (x > 0) {
                result ^= value[x];
                x -= lb(x);
            }
            return  result;
        }

        int lb(int x) {
            return x & (-x);
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        data = new int[n + 1];
        BIT bit = new BIT(n);
        for (int i = 1; i <= n; i++) {
            data[i] = in.nextInt();
            bit.update(i, data[i]);
        }

        for (int i = 0; i < q; i++) {
            int t = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            if (t == 1) {
                bit.update(x, y);
                data[x] = data[x] ^ y;
            } else {
                out.println(bit.query(y) ^ bit.query(x - 1));
            }
        }
    }
}
