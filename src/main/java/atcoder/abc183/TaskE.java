package atcoder.abc183;

import common.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collector;

public class TaskE {
    class Node {
        long[] sum;
        long value;

        public Node(long[] sum, long value) {
            this.sum = sum;
            this.value = value;
        }
    }

    long MOD = 1_000_000_007;
    Node[][] dp;
    int h;
    int w;
    long INF = MOD;
    String[] grid;
    int[][] dir = {{0, 1}, {1, 0}, {1, 1}};

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        h = in.nextInt();
        w = in.nextInt();
        grid = new String[h];
        for (int i = 0; i < h; i++) {
            grid[i] = in.next();
        }
        dp = new Node[h][w];
        dp[h - 1][w - 1] = new Node(new long[]{1, 1, 1}, 1);
        long result = 0;
        result = (result + func(0, 0).value) % MOD;
        out.println(result);
    }


    private Node func(int x, int y) {
        if (dp[x][y] != null) {
            return dp[x][y];
        }
        dp[x][y] = new Node(new long[]{0, 0, 0}, 0);
        if (grid[x].charAt(y) == '#') {
            return dp[x][y];
        }
        for (int i = 0; i < dir.length; i++) {
            int[] d = dir[i];
            int nextX = x + d[0];
            int nextY = y + d[1];
            if (nextX >= h || nextY >= w || grid[nextX].charAt(nextY) == '#') {
                continue;
            }
            dp[x][y].value = (dp[x][y].value + func(nextX, nextY).sum[i]) % MOD;
        }
        for (int i = 0; i < dir.length; i++) {
            int[] d = dir[i];
            int nextX = x + d[0];
            int nextY = y + d[1];
            if (nextX >= h || nextY >= w || grid[nextX].charAt(nextY) == '#') {
                dp[x][y].sum[i] = dp[x][y].value;
            } else {
                dp[x][y].sum[i] = (dp[x][y].value + func(nextX, nextY).sum[i]) % MOD;
            }
        }
        return dp[x][y];
    }
}
