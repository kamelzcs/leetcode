package atcoder.abc126;

import common.InputReader;
import java.io.PrintWriter;

public class TaskE {
    class UF {
        int n;
        int total;
        int[] parent;
        UF(int n) {
            this.n = n;
            this.total = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            int p = parent[x];
            if (p == x) {
                return p;
            }
            parent[x] = find(p);
            return parent[x];
        }

        void merge(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return;
            }
            total--;
            parent[py] = px;
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        UF uf = new UF(n);
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();

            x--;
            y--;
            uf.merge(x, y);
        }
        out.println(uf.total);
    }
}
