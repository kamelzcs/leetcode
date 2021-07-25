package atcoder.arc107;

import common.InputReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class TaskC {
    long MOD = 998244353;
    class UF {
        int n;
        int[] parent;
        int[] size;
        UF(int n) {
            this.n = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
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
        void merge(int x,int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) {
                return;
            }

            parent[py] = px;
            size[px] += size[py];
        }
    }

    int n;
    int k;
    long[] permutation;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n  = in.nextInt();
        k = in.nextInt();
        permutation = new long[n + 1];
        permutation[0] = 1;
        for (int i = 1; i <= n; i++) {
            permutation[i] = (permutation[i - 1] * i) % MOD;
        }
        int[][] data = new int[n][n];
        int[][] dataReverse = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0 ;j < n; j++) {
                int value = in.nextInt();
                data[i][j] = value;
                dataReverse[j][i] = value;
            }
        }
        long value = (f(data) * f(dataReverse)) % MOD;
        out.println(value);
    }

    private long f(int[][] data) {
        UF uf = new UF(n);
        for (int col = 0; col < n; col++) {
            for (int nextCol = col + 1; nextCol < n; nextCol++) {
                boolean valid = true;
                for (int row = 0; row < n; row++) {
                    if (data[row][col] + data[row][nextCol] > k) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    uf.merge(col, nextCol);
                }
            }
        }
        long result = 1;
        for (int i = 0; i < uf.n; i++) {
            int parent = uf.find(i);
            if (parent == i) {
                result = (result * permutation[uf.size[parent]]) % MOD;
            }
        }
        return result;
    }
}
